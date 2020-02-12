package com.example.zarurat1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zarurat1.Helper.SelectImageHelper;
import com.example.zarurat1.Pojo.UserInfoPojo;
import com.example.zarurat1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText RegeditTextName, RegeditTextEmail, RegeditTextMobile, RegeditTextPwd, RegeditTextCPwd,RegeditTextDob;
    Button btnSignUp;
    ImageView RegImageview;
    TextView textViewClickReg;

    int flag=0;

    SelectImageHelper selectImageHelper;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseStorage storage;
    StorageReference storageReference;

    static final Pattern s1 = Pattern.compile("^[A-Za-z\\s]+$");
    static final Pattern s3 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    static final Pattern s4 = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?"        // +<digits><sdd>*
            + "(\\([0-9]+\\)[\\- \\.]*)?"   // (<digits>)<sdd>*
            + "([0-9][0-9\\- \\.]+[0-9])");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        btnSignUp = findViewById(R.id.buttonsignup);
        textViewClickReg = findViewById(R.id.textClickReg);

        String modeType=getIntent().getStringExtra("mode");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(modeType);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        RegImageview = findViewById(R.id.profile_image);
        RegeditTextName = findViewById(R.id.name);
        RegeditTextEmail = findViewById(R.id.Email);
        RegeditTextMobile = findViewById(R.id.mobile);
        RegeditTextPwd = findViewById(R.id.pwd);
        RegeditTextCPwd = findViewById(R.id.conpwd);
        btnSignUp = findViewById(R.id.buttonsignup);
        textViewClickReg = findViewById(R.id.textClickReg);
        RegeditTextDob=findViewById(R.id.dob);

        selectImageHelper = new SelectImageHelper(this, RegImageview);

        RegImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageHelper.selectImageOption();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSignUp();
            }
        });

        textViewClickReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        selectImageHelper.handleResult(requestCode, resultCode, result);  // call this helper class method
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        selectImageHelper.handleGrantedPermisson(requestCode, grantResults);   // call this helper class method
    }

    public void getSignUp() {
        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final String id = databaseReference.push().getKey();
        final String name = RegeditTextName.getText().toString();
        final String mobile = RegeditTextMobile.getText().toString();
        final String email = RegeditTextEmail.getText().toString();
        final String dob=RegeditTextDob.getText().toString();
        final String password = RegeditTextPwd.getText().toString();
        final String conPassword = RegeditTextCPwd.getText().toString();
        final Uri uri = selectImageHelper.getURI_FOR_SELECTED_IMAGE();


        if (name.length() == 0) {
            RegeditTextName.setError("please enter name");
            RegeditTextName.requestFocus();
            progressDialog.cancel();
        }else if(dob.length()==0){
            RegeditTextDob.setError("please enter Date of Birth");
            RegeditTextDob.requestFocus();
            progressDialog.cancel();
        }else if (!s1.matcher(name).matches()) {
            RegeditTextName.setError("Please enter valid name");
            progressDialog.cancel();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            RegeditTextEmail.setError("please enter valid email address");
            progressDialog.cancel();
            RegeditTextEmail.requestFocus();
        } else if (mobile.length() < 10) {
            RegeditTextMobile.setError("please enter 10 digit mobile number");
            progressDialog.cancel();
            RegeditTextMobile.requestFocus();
        } else if (!s4.matcher(mobile).matches()) {
            RegeditTextMobile.setError("Please enter valid phone number");
            progressDialog.cancel();
        } else if (password.length() < 6) {
            RegeditTextPwd.setError("password can not be less than 6 character");
            progressDialog.cancel();
        } else if (!s3.matcher(password).matches()) {
            RegeditTextPwd.setError("enter valid password");
            progressDialog.cancel();
        } else if (RegeditTextCPwd.length() < 6 || !password.equals(conPassword)) {
            RegeditTextCPwd.setError("password does not match");
            RegeditTextCPwd.requestFocus();
            progressDialog.cancel();
        } else if (!s3.matcher(conPassword).matches()) {
            RegeditTextCPwd.setError("enter valid password");
            progressDialog.cancel();
        } else if (uri == null) {
            Toast.makeText(this, "Plsese Select Profile Image", Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
        } else if (uri != null) {
            Query emailQuery=databaseReference.orderByChild("email").equalTo(email);
            Log.d("12345", "getSignUp:email query enter ");
            emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getChildrenCount()>0){
                        RegeditTextEmail.setError("Email Already Exist!");
                        progressDialog.cancel();
                        RegeditTextEmail.requestFocus();
                    }
                    else{
                        final StorageReference reference = storageReference.child("/Image/" + email);
                        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(final Uri uri) {
                                        Log.d("1234", "onSuccess: ");
                                        UserInfoPojo userPojo1 = new UserInfoPojo();
                                        userPojo1.setEmail(email);
                                        userPojo1.setName(name);
                                        userPojo1.setMoble_no(mobile);
                                        userPojo1.setPwd(password);
                                        userPojo1.setConPwd(conPassword);
                                        userPojo1.setId(id);
                                        userPojo1.setDob(dob);
                                        userPojo1.setImg(uri.toString());
                                        databaseReference.child(id).setValue(userPojo1);
                                        progressDialog.cancel();
                                        Toast.makeText(RegistrationActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistrationActivity.this, " Network Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

}

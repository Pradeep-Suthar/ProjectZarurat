package com.example.zarurat1.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zarurat1.Fragment.HomeFragment;
import com.example.zarurat1.Fragment.JobFragment;
import com.example.zarurat1.Fragment.KhataFragment;
import com.example.zarurat1.Fragment.NewsFragment;
import com.example.zarurat1.Fragment.ServicesFragment;
import com.example.zarurat1.Pojo.UserInfoPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Pattern;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    static final Pattern s1 = Pattern.compile("^[A-Za-z\\s]+$");
    static final Pattern s3 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    static final Pattern s4 = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?"        // +<digits><sdd>*
            + "(\\([0-9]+\\)[\\- \\.]*)?"   // (<digits>)<sdd>*
            + "([0-9][0-9\\- \\.]+[0-9])");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    Fragment selectedFragment=null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment=new HomeFragment();
                    getSupportActionBar().setTitle("Home");
                    break;
                case R.id.navigation_services:
                    selectedFragment=new ServicesFragment();
                    getSupportActionBar().setTitle("Services");
                    break;
                case R.id.navigation_jobs:
                    selectedFragment=new JobFragment();
                    getSupportActionBar().setTitle("Jobs");
                    break;
                case R.id.navigation_khata:
                    selectedFragment=new KhataFragment();
                    getSupportActionBar().setTitle("Khata");
                    break;
                case R.id.navigation_news:
                    selectedFragment=new NewsFragment();
                    getSupportActionBar().setTitle("News");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.viewprofile, null, false);
            alertDialogBuilder.setView(view);
            AlertDialog dialog=alertDialogBuilder.create();

            ImageView VpImageView=view.findViewById(R.id.Vprofileimageview1);
            TextView VpTextname = view.findViewById(R.id.VprofileTextViewName);
            TextView VpTextemail = view.findViewById(R.id.VprofileTextViewEmail);
            TextView VpTextMobile = view.findViewById(R.id.VprofileTextViewMobile);

            SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
            String ImgUrl=sharedPreferences.getString("imgUrl",null);
            String name=sharedPreferences.getString("name",null);
            String Email=sharedPreferences.getString("email",null);
            String Mobile=sharedPreferences.getString("mobile",null);

            Glide.with(MainActivity.this).load(ImgUrl).into(VpImageView);
            VpTextname.setText(name);
            VpTextemail.setText(Email);
            VpTextMobile.setText(Mobile);
            dialog.show();
            // Handle the camera action
        } else if (id == R.id.nav_pwd) {
            final AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater layoutInflater2 = getLayoutInflater();
            View view2 = layoutInflater2.inflate(R.layout.updatepassword, null, false);

            alertDialogBuilder2.setView(view2);
            final AlertDialog dialog2 = alertDialogBuilder2.create();

            final EditText editTextOld = view2.findViewById(R.id.UeditTextOld);
            final EditText editTextNew = view2.findViewById(R.id.UeditTextNew);
            final EditText editTextCon = view2.findViewById(R.id.UeditTextCon);
            Button buttonUpwd = view2.findViewById(R.id.UpwdButton);
            buttonUpwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences=getSharedPreferences("myprf",MODE_PRIVATE);
                    final String id1=sharedPreferences.getString("id",null);
                    String Pwd=sharedPreferences.getString("pwd",null);
                    String OldPwd=editTextOld.getText().toString();
                    String NewPwd=editTextNew.getText().toString();
                    String ConPwd=editTextCon.getText().toString();

                    if (!NewPwd.equals(ConPwd)) {
                        editTextCon.setError("Password does not match");
                        editTextCon.requestFocus();
                    }else if(!Pwd.equals(OldPwd)){
                        editTextOld.setError("Password does not match");
                        editTextOld.requestFocus();
                    }else if (!s3.matcher(NewPwd).matches()) {
                        editTextNew.setError("enter valid password");
                    }else if (!s3.matcher(ConPwd).matches()) {
                        editTextCon.setError("enter valid password");
                    }else{
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("pwd", NewPwd);
                        map.put("conPwd", ConPwd);
                        databaseReference.child(id1).updateChildren(map);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    UserInfoPojo userPojo = dataSnapshot1.getValue(UserInfoPojo.class);
                                    String id=userPojo.getId();
                                    if(id.equals(id1)) {
                                        SharedPreferences sharedPreferences1 = getSharedPreferences("data", MODE_PRIVATE);
                                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                                        editor1.putString("pwd", userPojo.getPwd());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        dialog2.dismiss();
                    }
                }
            });
            dialog2.show();


        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_about) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.about_dialog, null, false);
            alertDialogBuilder.setView(view);
            AlertDialog dialog=alertDialogBuilder.create();

            dialog.show();

        } else if (id == R.id.nav_help) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.help_layout, null, false);
            alertDialogBuilder.setView(view);
            AlertDialog dialog=alertDialogBuilder.create();
            ImageView imagecall,imageemail;
            imageemail=findViewById(R.id.imageViewMail);
            imagecall=findViewById(R.id.imageViewCall);
          /*  imageemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int d = Log.d("12345", "onClick: enter in send mail");
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    LayoutInflater layoutInflater = getLayoutInflater();
                    View view = layoutInflater.inflate(R.layout.mail_content_dialog, null, false);
                    alertDialogBuilder.setView(view);
                    final AlertDialog dialog=alertDialogBuilder.create();

                    final EditText editTextSubject = view.findViewById(R.id.editTextMailSubect);
                    final EditText editTextBody = view.findViewById(R.id.editTextMailBody);
                    Button SendMailBtn=view.findViewById(R.id.SendMailBtn);

                    SendMailBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String sub=editTextSubject.getText().toString();
                            String body=editTextBody.getText().toString();

                            Log.d("12345", "onClick: enter in send mail action");
                            Intent intent=new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"pradeepsuthar800@gmail.com"});
                            intent.putExtra(Intent.EXTRA_SUBJECT,sub);
                            intent.putExtra(Intent.EXTRA_TEXT,body);

                            try{
                                startActivity(Intent.createChooser(intent,"Send Email..."));
                            }catch (android.content.ActivityNotFoundException ex){
                               // Toast.makeText(getActivity(), "There are no email client installed", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
            imagecall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("12345", "onClick: enter for call");
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:8005562033"));
                    startActivity(intent);
                }
            });

            dialog.show();
*/
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

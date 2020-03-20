package com.example.zarurat1.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zarurat1.Activity.KhataDetailActivity;
import com.example.zarurat1.Pojo.KhataPojo;
import com.example.zarurat1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class KataPersonCustomAdapter extends RecyclerView.Adapter<KataPersonCustomAdapter.ViewHolder>{

    private ArrayList<KhataPojo> values;
    private Context context;
    private View v;
    String Amtmode="";
    FirebaseDatabase database;
    DatabaseReference reference;
    int give=0,take=0;
    int sumAmount = 0;

    static final Pattern s1 = Pattern.compile("^[A-Za-z\\s]+$");
    static final Pattern s3 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    static final Pattern s4 = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?"        // +<digits><sdd>*
            + "(\\([0-9]+\\)[\\- \\.]*)?"   // (<digits>)<sdd>*
            + "([0-9][0-9\\- \\.]+[0-9])");

    public KataPersonCustomAdapter(Context context, ArrayList<KhataPojo> myDataset) {
        values = myDataset;
        this.context = context;
    }


    @Override
    public KataPersonCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.khatadetail_layout, parent, false);
        KataPersonCustomAdapter.ViewHolder vh = new KataPersonCustomAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(KataPersonCustomAdapter.ViewHolder holder, final int position) {
        final KhataPojo myPojo = values.get(position);
        holder.name.setText(myPojo.getName());

        holder.btnRecieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.khataamtrecieve_layout, null, false);
                alertDialogBuilder.setView(view);
                final AlertDialog dialog=alertDialogBuilder.create();

                final EditText Ramt=view.findViewById(R.id.edRecievedAmt);
                final Spinner Rmode=view.findViewById(R.id.spinner_Recieved_mode);
                final EditText Rdesc=view.findViewById(R.id.edRecievedDescription);
                Button btnRsave=view.findViewById(R.id.btnSaveRecieved);

                btnRsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        give=1;
                        save(Ramt,Rmode,Rdesc,myPojo);
                        findSum(myPojo);
                        dialog.dismiss();
                    }
                });



                dialog.show();


            }
        });

        holder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.khataamtsend_layout, null, false);
                alertDialogBuilder.setView(view);
                final AlertDialog dialog=alertDialogBuilder.create();

                final EditText Samt=view.findViewById(R.id.edSendedAmt);
                final Spinner Smode=view.findViewById(R.id.spinner_Sended_mode);
                final EditText Sdesc=view.findViewById(R.id.edSendedDescription);
                Button btnSsave=view.findViewById(R.id.btnSaveSended);

                btnSsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        take=1;
                        save(Samt,Smode,Sdesc,myPojo);
                        findSum(myPojo);

                        //SmsManager smsManager=SmsManager.getDefault();
                      //  smsManager.sendTextMessage(myPojo.getMoble_no().toString(),null,"Zarurat App: Khata Amount Add Amount:"+Samt.getText().toString()+"Description :"+Samt.getText().toString()+"Final Amount:"+myPojo.getFAmount().toString(),null,null);
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, KhataDetailActivity.class);
                intent.putExtra("description",myPojo.getDescription());
                intent.putExtra("date",myPojo.getDate());
                intent.putExtra("name",myPojo.getName());
                intent.putExtra("time",myPojo.getTime());
                intent.putExtra("famount",myPojo.getFAmount());
                intent.putExtra("bamount",myPojo.getBAmount());
                intent.putExtra("email",myPojo.getEmail());
                intent.putExtra("mobileNo",myPojo.getMoble_no());
                context.startActivity(intent);
            }
        });

    }

    private void save(final EditText samt, Spinner smode, final EditText sdesc, KhataPojo myPojo) {
        samt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (samt.getText().toString().length() < 1) {
                    samt.setError("please enter Base Amount");
                    samt.requestFocus();
                } else if (!s4.matcher(samt.getText().toString()).matches()) {
                    samt.setError("Please enter valid Amount");
                    samt.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sdesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (sdesc.getText().toString().length() < 1) {
                    sdesc.setError("please enter Base Amount");
                    sdesc.requestFocus();
                } else if (!s4.matcher(samt.getText().toString()).matches()) {
                    sdesc.setError("Please enter valid Amount");
                    sdesc.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        int position = smode.getSelectedItemPosition();
        if (position == 0) {
            Toast.makeText(context, "Please select valid Mode", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            Amtmode = smode.getSelectedItem().toString();
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);

            final String time = hr + ":" + min;
            final String date = day + "/" + (month + 1) + "/" + year;

            String dateTime=day+""+month+""+hr+""+min;

            final String amount = samt.getText().toString();
            final String description = sdesc.getText().toString();


            if (amount.length() == 0) {
                samt.setError("Please Enter Minimum Base Amount");
                progressDialog.cancel();
                samt.requestFocus();

            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("myprf", MODE_PRIVATE);
                String nameUser=sharedPreferences.getString("name",null);
                String mobileUser=sharedPreferences.getString("mobile",null);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("khataInfo").child(nameUser+mobileUser);

                String mobileNo = myPojo.getMoble_no();

                KhataPojo khataPojo = new KhataPojo();
                khataPojo.setDescription(description);
                khataPojo.setTime(time);
                khataPojo.setDate(date);
                if (take == 1) {
                    khataPojo.setFAmount("+" + amount);
                }
                if (give == 1) {
                    khataPojo.setFAmount("-" + amount);
                }
                khataPojo.setDate(date);
                khataPojo.setTime(time);
                khataPojo.setDescription(description);
                reference.child(mobileNo).child("amountList").child(dateTime).setValue(khataPojo);
                progressDialog.cancel();
                Toast.makeText(context, "Save Successfully", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

        }
        }

    }

    public void findSum(final KhataPojo myPojo) {
        String mobileNo = myPojo.getMoble_no();
        String bamt=myPojo.getBAmount();
        if(bamt.charAt(0) == '-'){
            sumAmount=-Integer.parseInt(bamt.substring(1));
            Log.d("1234", "1-.findSum: "+sumAmount);

        }else if(bamt.charAt(0)== '+'){
            sumAmount=Integer.parseInt(bamt.substring(1));
            Log.d("1234", "2+.findSum: "+sumAmount);
        }

        reference.child(mobileNo).child("amountList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren() ){
                    KhataPojo khataPojo = dataSnapshot1.getValue(KhataPojo.class);
                    String amt = khataPojo.getFAmount();
                    Log.d("1234", "data: "+amt);
                    if(amt.charAt(0) == '-'){
                        sumAmount=sumAmount-Integer.parseInt(amt.substring(1));
                        Log.d("1234", "3-.for loop: "+sumAmount);

                    }else if(amt.charAt(0)== '+'){
                        sumAmount=sumAmount+Integer.parseInt(amt.substring(1));
                        Log.d("1234", "4+.for loop: "+sumAmount);
                    }
                }
                Log.d("1234", "5+.for loop: "+sumAmount);
                updateFAmount(sumAmount,myPojo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public void updateFAmount(int x, KhataPojo myPojo) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(x<0){
            map.put("famount", ""+x);

        }else{
            map.put("famount", "+"+x);
        }
        String mobileNo = myPojo.getMoble_no();
        reference.child(mobileNo).updateChildren(map);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public Button  btnSend;
        public Button btnRecieve;
        public Button btnDetail;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.textkhataName);
            btnSend = v.findViewById(R.id.btnSendAmt);
            btnRecieve=v.findViewById(R.id.btnRecieve);
            btnDetail = v.findViewById(R.id.btnDetails);

        }
    }



}
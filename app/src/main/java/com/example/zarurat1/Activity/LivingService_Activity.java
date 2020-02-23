package com.example.zarurat1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zarurat1.R;

public class LivingService_Activity extends AppCompatActivity {
    Spinner spinnercategory;
    ImageView imageView;
    EditText editTextprice;
    Button buttonaddlocation;
    RadioGroup radioGroupmesscheck;
    RadioButton radioButton;
    String arrcategory[]={"Boys PG","Girls PG","Appartment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_service_);
     spinnercategory=findViewById(R.id.spinnertype);
     imageView=findViewById(R.id.livingplaceimage);
     editTextprice=findViewById(R.id.baseprice);
     buttonaddlocation=findViewById(R.id.buttonaddlocation);
     radioGroupmesscheck=findViewById(R.id.radiogroupmesscheck);


        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrcategory);

        spinnercategory.setAdapter(arrayAdapter);
        spinnercategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroupmesscheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int selectedId=radioGroupmesscheck.getCheckedRadioButtonId();
                    radioButton=findViewById(selectedId);
                    if(selectedId==-1)
                    {
                        Toast.makeText(LivingService_Activity.this,"Nothing Selected in Radio Button",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LivingService_Activity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}

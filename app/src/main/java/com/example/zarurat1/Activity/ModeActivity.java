package com.example.zarurat1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.zarurat1.R;

public class ModeActivity extends AppCompatActivity {
    ImageView imageViewUser,imageViewServiceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);


        imageViewUser=findViewById(R.id.imageviewUser);
        imageViewServiceProvider=findViewById(R.id.imageviewService);


        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeActivity.this,RegistrationActivity.class);
                intent.putExtra("mode","userInfo");
                startActivity(intent);
                finish();
            }
        });

        imageViewServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeActivity.this,RegistrationActivity.class);
                intent.putExtra("mode","serviceProviderInfo");
                startActivity(intent);
                finish();
            }
        });
    }
}

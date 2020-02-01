package com.example.zarurat1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.zarurat1.R;

public class ModeActivity extends AppCompatActivity {
    ImageView imageViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);


        imageViewUser=findViewById(R.id.imageviewUser);

        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

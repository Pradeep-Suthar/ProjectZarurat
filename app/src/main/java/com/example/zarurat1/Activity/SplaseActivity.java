package com.example.zarurat1.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.zarurat1.R;

public class SplaseActivity extends AppCompatActivity {

    ImageView imageViewLogo,loading;
    Drawable imageViewLogo1;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splase);

        imageViewLogo = findViewById(R.id.imagelogo1);
        loading = findViewById(R.id.loading);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytrans);
        Animation animationrotate= AnimationUtils.loadAnimation(this,R.anim.myrotate);
       /* animationDrawable= (AnimationDrawable) imageViewLogo.getBackground();
        animationDrawable.setEnterFadeDuration(20);
        animationDrawable.setExitFadeDuration(20);
        animationDrawable.start();*/

        imageViewLogo.setAnimation(animation);
        loading.startAnimation(animationrotate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplaseActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}

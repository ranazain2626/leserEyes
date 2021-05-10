package com.example.lasereyes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lasereyes.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread td = new Thread(){
            public void run(){
                try {
                    sleep(3000);

                }catch(Exception exception)
                {
                    exception.printStackTrace();

                }finally {
                    Intent It=new Intent(SplashActivity.this, ImageActivity.class);
                    startActivity(It);
                }


            }
        };td.start();
    }
}
package com.example.bagpackers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView p;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p=(ImageView) findViewById(R.id.imageView2);
        final Animation a= AnimationUtils.loadAnimation(this,R.anim.fadein);
        p.startAnimation(a);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {



            }

        },2800);
    }
}


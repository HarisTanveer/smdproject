package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
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
//        Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//        fadeIn.setDuration(1000);
//
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//        fadeOut.setStartOffset(1000);
//        fadeOut.setDuration(1000);
//
//        AnimationSet animation = new AnimationSet(false); //change to false
//        animation.addAnimation(fadeIn);
//        animation.addAnimation(fadeOut);
//        MainActivity.setAnimation(animation);
       timer=new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               Intent intent =new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
               finish();
           }

       },2800);

    }
}

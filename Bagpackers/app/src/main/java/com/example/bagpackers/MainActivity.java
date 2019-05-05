package com.example.bagpackers;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.arch.persistence.room.Room;
import android.widget.Toast;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Location;
import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.Classes.User;

//import com.example.bagpackers.RoomDB.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.bagpackers.RoomDB.AppDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Location gilgit;
    ImageView p;
    Timer timer;
    static User u;
    ArrayList<Place> places=new ArrayList<>();
    ArrayList<Hotels> hotels=new ArrayList<>();
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        gilgit = new Location("Gilgit-Baltistan");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        //actionbar.hide();
        p = (ImageView) findViewById(R.id.imageView2);
        final Animation a = AnimationUtils.loadAnimation(this, R.anim.fadein);
        p = (ImageView) findViewById(R.id.imageView2);
        p.startAnimation(a);



        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
            }

        }, 2800);




    }

}



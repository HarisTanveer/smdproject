package com.example.bagpackers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.arch.persistence.room.Room;
import android.widget.Toast;

import com.example.bagpackers.Classes.User;
import com.example.bagpackers.RoomDB.AppDatabase;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView p;
    Timer timer;
    static User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p = (ImageView) findViewById(R.id.imageView2);
        final Animation a = AnimationUtils.loadAnimation(this, R.anim.fadein);
        p.startAnimation(a);

        u=new User();
        u.email="haristanveer.ht@gmail.com";
        u.name="Haris Tanveer";
        u.password="12345";
        u.number="03468578686";


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                new DataTask().execute();

            }

        }, 2800);
    }


    public class DataTask extends AsyncTask<Void,Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            String message = "deleted";
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
        }
    }
    }
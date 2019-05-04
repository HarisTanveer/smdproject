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

import java.util.List;

import com.example.bagpackers.RoomDB.AppDatabase;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Location gilgit;
    ImageView p;
    Timer timer;
    static User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

          gilgit=new Location("Gilgit-Baltistan");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar=getSupportActionBar();
        actionbar.hide();
        p=(ImageView) findViewById(R.id.imageView2);
        final Animation a= AnimationUtils.loadAnimation(this,R.anim.fadein);
        p = (ImageView) findViewById(R.id.imageView2);
        p.startAnimation(a);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent =new Intent(MainActivity.this,login.class);
                startActivity(intent);
//                new DataTask().execute();
               // new DataTask().execute();


            }

        }, 2800);




    }





    public class DataTask extends AsyncTask<Void,Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
           db.userDao().insert(u);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            String message = "deleted";
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
        }
    }

    public void popluate()
    {



       // public Place(String name,String location,String description ,String picture)

        String d="Khaplu (Urdu and Balti:خپلو), also spelt Khapalu, is a town that serves as the administrative capital of the Ghanche District of Gilgit-Baltistan, in northern Pakistan. Lying 103 km (64 mi) east of the town of Skardu, it was the second-largest kingdom in old Baltistan of the Yabgo dynasty. It guarded the trade route to Ladakh along the Shyok River.";
        Place khaplu=new Place("Khaplu"," Ghanche District",d,"android.resource://com.example.bagpackers/drawable/khaplu");
        int id=khaplu.getid();

        // public Hotels(String nam,Integer plac,String numbe,Float pric,Float rat)
        Hotels a=new Hotels("khaplu restort",id,"0300-9123456",4000.0,4.5);
        Hotels b=new Hotels("khaplu inn",id,"0320-2123756",5000.0,4.2);
        Hotels e=new Hotels("Moon restort",id,"0302-7166456",4500.0,3.9);
        Hotels c=new Hotels("lulusar restort",id,"0305-6199406",2700.0,2.5);

        khaplu.hotels.add(a);
        khaplu.hotels.add(b);
        khaplu.hotels.add(c);
        khaplu.hotels.add(e);



        d="Upper Kachura Lake is of clear water and has a depth of 70 metres (230 ft). In summer it has a temperature of 15 °C (59 °F). In winter the surface is frozen solid. The upper Indus River flows nearby at a lower elevation.";
        Place kachura=new Place("Kachura Lake"," Skardu District",d,"android.resource://com.example.bagpackers/drawable/kachura");

        id=kachura.getid();


         a=new Hotels("haris restort",id,"0300-9123456",6000.0,4.3);
         b=new Hotels("Bismillah inn",id,"0320-2123756",5600.0,4.1);
         e=new Hotels("Moon restort",id,"0302-7166456",4500.0,3.9);
         c=new Hotels("kachura restort",id,"0305-6199406",2700.0,2.5);

        kachura.hotels.add(a);
        kachura.hotels.add(b);
        kachura.hotels.add(c);
        kachura.hotels.add(e);






        d="Fairy Meadows (Urdu: فیری میڈوز\u200E), named by German climbers (German Märchenwiese, “fairy tale meadows”) and locally known as Joot, is a grassland near one of the base camp sites of the Nanga Parbat, located in Diamer District, Gilgit-Baltistan, Pakistan. At an altitude of about 3,300 metres (10,800 ft) above the sea level, it serves as the launching point for trekkers summiting on the Rakhiot face of the Nanga Parbat. In 1995, the Government of Pakistan declared Fairy Meadows a National Park.";
        Place fairy=new Place("Fairy Meadows"," Daimer District",d,"android.resource://com.example.bagpackers/drawable/fairy");

        id=fairy.getid();


        a=new Hotels("Fairy land",id,"0310-0155456",6000.0,4.3);
        b=new Hotels("abdul grandHotel",id,"0320-2123756",2600.0,3.1);
        e=new Hotels("Moon restort",id,"0302-7166456",3500.0,3.9);
        c=new Hotels("Dream restort",id,"0305-6199406",4700.0,4.8);

        fairy.hotels.add(a);
        fairy.hotels.add(b);
        fairy.hotels.add(c);
        fairy.hotels.add(e);


        d="The Deosai National Park (Urdu: دیوسائی نیشنل پارک\u200E) is a high-altitude alpine plain and national park in northern Pakistan. It is located largely within Astore District in Gilgit Baltistan. Deosai Plains are situated at an average elevation of 4,114 metres (13,497 ft) above sea level.";
        Place deosai=new Place("Fairy Meadows"," Astore District",d,"android.resource://com.example.bagpackers/drawable/deosai");

        id=deosai.getid();


        a=new Hotels("deosai houses",id,"0313-0178786",4000.0,4.5);
        b=new Hotels("astore Hotel",id,"0321-2100756",3600.0,3.9);
        e=new Hotels("deosai restort",id,"0312-9966456",3500.0,3.9);
        c=new Hotels("Rama restort",id,"0305-7199406",2500.0,3.8);

        deosai.hotels.add(a);
        deosai.hotels.add(b);
        deosai.hotels.add(c);
        deosai.hotels.add(e);





        gilgit.places.add(khaplu);
        gilgit.places.add(kachura);
        gilgit.places.add(fairy);
        gilgit.places.add(deosai);


    }





    }
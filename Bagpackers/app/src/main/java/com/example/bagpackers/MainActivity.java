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

                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }

        }, 1800);




    }
    public class DataTask extends AsyncTask<Void,Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

//            String d = "Khaplu (Urdu and Balti:خپلو), also spelt Khapalu, is a town that serves as the administrative capital of the Ghanche District of Gilgit-Baltistan, in northern Pakistan. Lying 103 km (64 mi) east of the town of Skardu, it was the second-largest kingdom in old Baltistan of the Yabgo dynasty. It guarded the trade route to Ladakh along the Shyok River.";
//
//            Place khaplu = new Place("Khaplu", "Gilgit-Baltistan", d, "android.resource://com.example.bagpackers/drawable/khaplu");
//            String id = khaplu.name;
//
//            // public Hotels(String nam,Integer plac,String numbe,Float pric,Float rat)
//            Hotels a = new Hotels("khaplu restort", id, "0300-9123456", 4000.0, 4.5);
//            Hotels b = new Hotels("khaplu inn", id, "0320-2123756", 5000.0, 4.2);
//            Hotels e = new Hotels("Moon restort", id, "0302-7166456", 4500.0, 3.9);
//            Hotels c = new Hotels("lulusar restort", id, "0305-6199406", 2700.0, 2.5);
//
//
//            hotels.add(a);
//            hotels.add(b);
//            hotels.add(c);
//            hotels.add(e);
//
//            String d1 = "Upper Kachura Lake is of clear water and has a depth of 70 metres (230 ft). In summer it has a temperature of 15 °C (59 °F). In winter the surface is frozen solid. The upper Indus River flows nearby at a lower elevation.";
//            Place kachura = new Place("Kachura Lake", "Gilgit-Baltistan", d1, "android.resource://com.example.bagpackers/drawable/kachura");
//
//            id = kachura.name;
//
//
//            Hotels a1 = new Hotels("haris restort", id, "0300-9123456", 6000.0, 4.3);
//            Hotels b1 = new Hotels("Bismillah inn", id, "0320-2123756", 5600.0, 4.1);
//            Hotels e1 = new Hotels("Moon restort", id, "0302-7166456", 4500.0, 3.9);
//            Hotels c1 = new Hotels("kachura restort", id, "0305-6199406", 2700.0, 2.5);
//
//
//            hotels.add(a1);
//            hotels.add(b1);
//            hotels.add(c1);
//            hotels.add(e1);
//
//            String d2 = "Fairy Meadows (Urdu: فیری میڈوز\u200E), named by German climbers (German Märchenwiese, “fairy tale meadows”) and locally known as Joot, is a grassland near one of the base camp sites of the Nanga Parbat, located in Diamer District, Gilgit-Baltistan, Pakistan. At an altitude of about 3,300 metres (10,800 ft) above the sea level, it serves as the launching point for trekkers summiting on the Rakhiot face of the Nanga Parbat. In 1995, the Government of Pakistan declared Fairy Meadows a National Park.";
//            Place fairy = new Place("Fairy Meadows", "Gilgit-Baltistan", d2, "android.resource://com.example.bagpackers/drawable/fairy");
//
//            id = fairy.name;
//
//
//            Hotels a2 = new Hotels("Fairy land", id, "0310-0155456", 6000.0, 4.3);
//            Hotels b2 = new Hotels("abdul grandHotel", id, "0320-2123756", 2600.0, 3.1);
//            Hotels e2 = new Hotels("Moon restort", id, "0302-7166456", 3500.0, 3.9);
//            Hotels c2 = new Hotels("Dream restort", id, "0305-6199406", 4700.0, 4.8);
//
//            hotels.add(a2);
//            hotels.add(b2);
//            hotels.add(c2);
//            hotels.add(e2);
//
//            String d3 = "The Deosai National Park (Urdu: دیوسائی نیشنل پارک\u200E) is a high-altitude alpine plain and national park in northern Pakistan. It is located largely within Astore District in Gilgit Baltistan. Deosai Plains are situated at an average elevation of 4,114 metres (13,497 ft) above sea level.";
//            Place deosai = new Place("Astore", "Gilgit-Baltistan", d3, "android.resource://com.example.bagpackers/drawable/deosai");
//
//            id = deosai.name;
//
//
//            Hotels a3 = new Hotels("deosai houses", id, "0313-0178786", 4000.0, 4.5);
//            Hotels b3 = new Hotels("astore Hotel", id, "0321-2100756", 3600.0, 3.9);
//            Hotels e3 = new Hotels("deosai restort", id, "0312-9966456", 3500.0, 3.9);
//            Hotels c3 = new Hotels("Rama restort", id, "0305-7199406", 2500.0, 3.8);
//
//
//            hotels.add(a3);
//            hotels.add(b3);
//            hotels.add(c3);
//            hotels.add(e3);
//            places.add(khaplu);
//            places.add(kachura);
//            places.add(deosai);
//            places.add(fairy);
//
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//            for (int i2=0;i2<places.size();i2++)
//            {   db.collection("place").add(places.get(i2))
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
//                    {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference)
//                        {
//                            Log.d("dasd", "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    }).addOnFailureListener(new OnFailureListener()
//                    {
//                        @Override
//                        public void onFailure(@NonNull Exception e)
//                        {
//                            Log.w("asd", "Error adding document", e);
//                        }
//                    });
//
//            }
//            db.collection("location").add(gilgit)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
//                    {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference)
//                        {
//                            Log.d("dasd", "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    }).addOnFailureListener(new OnFailureListener()
//            {
//                @Override
//                public void onFailure(@NonNull Exception e)
//                {
//                    Log.w("asd", "Error adding document", e);
//                }
//            });
//

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
            db.userDao().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            String message = "Insert";
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    }

}



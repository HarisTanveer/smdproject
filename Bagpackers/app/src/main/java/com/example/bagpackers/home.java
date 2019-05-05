package com.example.bagpackers;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.DashPathEffect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NetworkChangeReceiver receiver;
    ArrayList<Hotels> hotels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent aa= getIntent();
        new DataTask().execute();

        Boolean a;
        a=aa.getBooleanExtra("night",false);



        if(a==false)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
//        if (InitApplication.getInstance().isNightModeEnabled())
//        {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else
//            {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupBroadcast();
      //  setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void location(View v)
    {
        Intent intent =new Intent(this,note_list_item.class);
        if(v.getId()==R.id.imageView5)
        {
            intent.putExtra("Province","Gilgit-Baltistan");
        }
        else if(v.getId()==R.id.imageView7)
        {
            intent.putExtra("Province","Balochistan");
        }
        else if(v.getId()==R.id.imageView8)
        {
            intent.putExtra("Province","Punjab");
        }
        else if(v.getId()==R.id.imageView9)
        {
            intent.putExtra("Province","Kashmir");
        }
        else if(v.getId()==R.id.imageView10)
        {
            intent.putExtra("Province","Sindh");
        }
        startActivity(intent);
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }

        return super.onOptionsItemSelected(item);
    }
public void profilewala(View view)
{
    Intent intent =new Intent(this,profile.class);
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    startActivity(intent);
}
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


         if (id == R.id.nav_slideshow) {
            Intent intent =new Intent(this,home.class);
            intent.putExtra("night",false);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {



//            InitApplication.getInstance().setIsNightModeEnabled(true);
//            Intent intent = getIntent();

            Intent intent =new Intent(this,home.class);
            intent.putExtra("night",true);
            startActivity(intent);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            finish();
//            startActivity(intent);



        }


         else if (id == R.id.nav_share)
        {
            shareIt();


        } else if (id == R.id.nav_send) {
            Intent intent =new Intent(this,Aboutus.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void shareIt() {
//sharing implementation here
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Download Bagpackers app from Play store now ! " +
                "Here is the link: https://play.google.com/store/apps/details?id=com.BagPackers.mobileapp";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    public void setupBroadcast()
    {
        IntentFilter intent1 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver =new NetworkChangeReceiver();
        registerReceiver(receiver,intent1);

    }
    public class DataTask extends AsyncTask<Void,Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            String d = "Khaplu (Urdu and Balti:خپلو), also spelt Khapalu, is a town that serves as the administrative capital of the Ghanche District of Gilgit-Baltistan, in northern Pakistan. Lying 103 km (64 mi) east of the town of Skardu, it was the second-largest kingdom in old Baltistan of the Yabgo dynasty. It guarded the trade route to Ladakh along the Shyok River.";

            Place khaplu = new Place("Khaplu", "Gilgit-Baltistan", d, "android.resource://com.example.bagpackers/drawable/khaplu");
            String id = khaplu.name;

            // public Hotels(String nam,Integer plac,String numbe,Float pric,Float rat)
            Hotels a = new Hotels("khaplu restort", id, "0300-9123456", 4000.0, 4.5);
            Hotels b = new Hotels("khaplu inn", id, "0320-2123756", 5000.0, 4.2);
            Hotels e = new Hotels("Moon restort", id, "0302-7166456", 4500.0, 3.9);
            Hotels c = new Hotels("lulusar restort", id, "0305-6199406", 2700.0, 2.5);


            hotels.add(a);
            hotels.add(b);
            hotels.add(c);
            hotels.add(e);

            String d1 = "Upper Kachura Lake is of clear water and has a depth of 70 metres (230 ft). In summer it has a temperature of 15 °C (59 °F). In winter the surface is frozen solid. The upper Indus River flows nearby at a lower elevation.";
            Place kachura = new Place("Kachura Lake", "Gilgit-Baltistan", d1, "android.resource://com.example.bagpackers/drawable/kachura");

            id = kachura.name;


            Hotels a1 = new Hotels("haris restort", id, "0300-9123456", 6000.0, 4.3);
            Hotels b1 = new Hotels("Bismillah inn", id, "0320-2123756", 5600.0, 4.1);
            Hotels e1 = new Hotels("Moon restort", id, "0302-7166456", 4500.0, 3.9);
            Hotels c1 = new Hotels("kachura restort", id, "0305-6199406", 2700.0, 2.5);


            hotels.add(a1);
            hotels.add(b1);
            hotels.add(c1);
            hotels.add(e1);

            String d2 = "Fairy Meadows (Urdu: فیری میڈوز\u200E), named by German climbers (German Märchenwiese, “fairy tale meadows”) and locally known as Joot, is a grassland near one of the base camp sites of the Nanga Parbat, located in Diamer District, Gilgit-Baltistan, Pakistan. At an altitude of about 3,300 metres (10,800 ft) above the sea level, it serves as the launching point for trekkers summiting on the Rakhiot face of the Nanga Parbat. In 1995, the Government of Pakistan declared Fairy Meadows a National Park.";
            Place fairy = new Place("Fairy Meadows", "Gilgit-Baltistan", d2, "android.resource://com.example.bagpackers/drawable/fairy");

            id = fairy.name;


            Hotels a2 = new Hotels("Fairy land", id, "0310-0155456", 6000.0, 4.3);
            Hotels b2 = new Hotels("abdul grandHotel", id, "0320-2123756", 2600.0, 3.1);
            Hotels e2 = new Hotels("Moon restort", id, "0302-7166456", 3500.0, 3.9);
            Hotels c2 = new Hotels("Dream restort", id, "0305-6199406", 4700.0, 4.8);

            hotels.add(a2);
            hotels.add(b2);
            hotels.add(c2);
            hotels.add(e2);

            String d3 = "The Deosai National Park (Urdu: دیوسائی نیشنل پارک\u200E) is a high-altitude alpine plain and national park in northern Pakistan. It is located largely within Astore District in Gilgit Baltistan. Deosai Plains are situated at an average elevation of 4,114 metres (13,497 ft) above sea level.";
            Place deosai = new Place("Astore", "Gilgit-Baltistan", d3, "android.resource://com.example.bagpackers/drawable/deosai");

            id = deosai.name;


           Hotels a3 = new Hotels("deosai houses", id, "0313-0178786", 4000.0, 4.5);
            Hotels b3 = new Hotels("astore Hotel", id, "0321-2100756", 3600.0, 3.9);
            Hotels e3 = new Hotels("deosai restort", id, "0312-9966456", 3500.0, 3.9);
            Hotels c3 = new Hotels("Rama restort", id, "0305-7199406", 2500.0, 3.8);


            hotels.add(a3);
            hotels.add(b3);
            hotels.add(c3);
            hotels.add(e3);



            CollectionReference db = FirebaseFirestore.getInstance().collection("rooms");
            for (int i2=0;i2<hotels.size();i2++)
            {   db.add(hotels.get(i2))
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                    {
                        @Override
                        public void onSuccess(DocumentReference documentReference)
                        {
                            Log.d("dasd", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Log.w("asd", "Error adding document", e);
                        }
                    });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            String message = "Insert";
            Toast.makeText(home.this, message, Toast.LENGTH_LONG).show();
        }
    }

}

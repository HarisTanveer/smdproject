package com.example.bagpackers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.DashPathEffect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NetworkChangeReceiver receiver;
    MyFirebaseService myService;
    ServiceConnection connection;
    boolean bound = false;
    private FirebaseAuth mAuth;
    String currentEmail;
    ImageView r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            Intent intent=new Intent(this,login.class);
            startActivity(intent);

        }



        connection = new ServiceConnection() {

            public void onServiceConnected(ComponentName className, IBinder binder) {
                myService = ((MyFirebaseService.LocalBinder) binder).getService();
                bound = true;
                myService.getStatus();

            }
            public void onServiceDisconnected(ComponentName className) {
                bound = false;
            }
        };



        Intent aa= getIntent();
        //new DataTask().execute();

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





        startDataSyncService();
    }


    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this,MyFirebaseService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
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


    private void startDataSyncService(){
        SharedPreferences preferences = getSharedPreferences("service",Context.MODE_PRIVATE);
        boolean started = preferences.getBoolean("started",false);
        SharedPreferences.Editor editor = preferences.edit();

        Intent serviceIntent = new Intent(this,MyFirebaseService.class);
        startService(serviceIntent);

        editor.putBoolean("started",true);
        editor.commit();

    }

}

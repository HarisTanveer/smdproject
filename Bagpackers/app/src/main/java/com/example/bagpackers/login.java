package com.example.bagpackers;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.NonNull;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bagpackers.Classes.User;
import com.example.bagpackers.RoomDB.AppDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class login extends AppCompatActivity {
    private AdView mAdView;
    private TextView email;
    private TextView password;
    private ProgressBar loader;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String session = "session";
    boolean darkTheme=false;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        sharedpreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        darkTheme  =sharedpreferences.getBoolean(session,false);
//        if(darkTheme==true) {
//            Intent intent = new Intent(login.this, home.class);
//            startActivity(intent);
//
//        }




        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {
            Intent intent = new Intent(login.this, home.class);
            startActivity(intent);
        }


            // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_login);
        MobileAds.initialize(this, "ca-app-pub-1643780640868125~3426418277");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("DA3D8F99BA8CF0AAE67E8A262D535B4F").build();
        mAdView.loadAd(adRequest);
        ActionBar actionbar=getSupportActionBar();
        actionbar.hide();
        email=(TextView)findViewById(R.id.editText);
        password=(TextView)findViewById(R.id.editText2);
        loader=findViewById(R.id.loaderLogin);
        loader.setVisibility(View.GONE);

    }

    public void onLogin(View v)
    {
        new LoginFirebase().execute();
    }


    public void toSignup(View v)
    {
        Intent intent =new Intent(login.this,signup.class);
        startActivity(intent);
    }

    //Local db se check kry ga using room
    public class LoginTask extends AsyncTask<Void,Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
            User log=db.userDao().login(email.getText().toString(),password.getText().toString());
            if(log!=null)
                return true;
            return false;

        }

        @Override
        protected void onPostExecute(Boolean v) {
            if(v)
            {
                Intent intent =new Intent(login.this,signup.class);
                startActivity(intent);
            }
            else
                {

                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT);
                }
        }
    }

    public void test(View v)
    {
        Intent intent =new Intent(this,home.class);
        startActivity(intent); }

    public class LoginFirebase extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            loginWithFirebase();
            return null;
        }
    }


    public void loginWithFirebase()
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putBoolean(session, true);
//                    editor.commit();
                    Intent intent =new Intent(login.this,home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                    loader.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

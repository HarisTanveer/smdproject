package com.example.bagpackers;


import android.content.Intent;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MobileAds.initialize(this, "ca-app-pub-1643780640868125~3426418277");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("DA3D8F99BA8CF0AAE67E8A262D535B4F").build();
        mAdView.loadAd(adRequest);
        ActionBar actionbar=getSupportActionBar();
        actionbar.hide();
        email=(TextView)findViewById(R.id.editText);
        password=(TextView)findViewById(R.id.editText2);


    }

    public void onLogin(View v)
    {
       FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Intent intent =new Intent(login.this,home.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
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
        protected Void doInBackground(Void... voids)
        {

            return null;
        }
    }

}

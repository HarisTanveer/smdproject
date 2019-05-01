package com.example.bagpackers;

import android.content.Intent;
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

import com.example.bagpackers.Classes.User;
import com.example.bagpackers.RoomDB.AppDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        ActionBar actionbar=getSupportActionBar();
        actionbar.hide();
        p=(ImageView) findViewById(R.id.imageView2);
        final Animation a= AnimationUtils.loadAnimation(this,R.anim.fadein);
        p = (ImageView) findViewById(R.id.imageView2);
        p.startAnimation(a);

        u=new User();
        u.email="haristanveer.ht@gmail.com";
        u.name="Haris Tanveer";
        u.password="12345";
        u.number="03468578686";



        Map<String, Object> user = new HashMap<>();
        user.put("Name", "Haris Tanveer");
        user.put("email", "aristanveer.ht@gmail.com");
        user.put("password", "12345");
        user.put("number","03468578686");


        //To insert in firebase
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//// Add a new document with a generated ID
//        db.collection("user")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("dasd", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.w("asd", "Error adding document", e);
//            }
//        });


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent =new Intent(MainActivity.this,login.class);
                startActivity(intent);
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


    }
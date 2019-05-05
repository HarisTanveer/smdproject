package com.example.bagpackers;

import android.arch.persistence.room.Room;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bagpackers.Classes.User;
import com.example.bagpackers.RoomDB.AppDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class profile extends AppCompatActivity {

    private String currentEmail;
    ImageView r;
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        r=findViewById(R.id.imageView3);

        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        currentEmail=currentUser.getEmail();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/"+currentEmail);
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                imageURL = uri.toString();
                Glide.with(getApplicationContext()).load(imageURL).into(r);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
           setValues();
            }
        });

t.start();

    }




    public void setValues()
    {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
        User curr=db.userDao().findByName(currentEmail);

        TextView t=findViewById(R.id.textView2);
        t.setText(curr.name);

        t=findViewById(R.id.textView5);
        t.setText(curr.email);

        t=findViewById(R.id.textView6);
        t.setText(curr.number);


    }

    public void onUpdate(View v)
    {

    }

}

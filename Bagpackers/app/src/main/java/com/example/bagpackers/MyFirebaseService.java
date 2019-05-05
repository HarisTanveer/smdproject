package com.example.bagpackers;

import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.Classes.User;
import com.example.bagpackers.RoomDB.AppDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MyFirebaseService extends Service
{
    List<Place> types;
    List<Hotels> hotels;
    List<User> users;
    private final IBinder binder = new LocalBinder();

    @Nullable
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void onCreate() { }

    public void getStatus()
    {
        Toast toast;
        toast = Toast.makeText(this, "Service running!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public int onStartCommand(Intent intent,int flags,int startId)
    {
        Toast.makeText(this,"Service starting",Toast.LENGTH_SHORT).show();
        Thread thread = new Thread(new Runnable(){
            public void run(){
                DownloadAllHotels();
                DownloadAllPlaces();
                DownloadCurrentUser();
            }
        });

        thread.start();
        return START_NOT_STICKY;
    }

    public class LocalBinder extends Binder
    {
        public MyFirebaseService getService()
        {
            return MyFirebaseService.this;
        }
    }


    public void DownloadAllPlaces()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("place").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots)
                    {
                        if (documentSnapshots.isEmpty())
                        {
                            Log.d(" ", "onSuccess: LIST EMPTY");
                            return;
                        }
                        else
                        {

                             types = documentSnapshots.toObjects(Place.class);

                            // Add all to your list
                            Thread t=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
                                    db.placeDao().insertAll(types);
                                }
                            });
                            t.start();

                            Log.d("", "onSuccess: ");
                        }
                    }


                }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void DownloadAllHotels()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("rooms").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots)
                    {
                        if (documentSnapshots.isEmpty())
                        {
                            Log.d(" ", "onSuccess: LIST EMPTY");
                            return;
                        }
                        else
                        {

                         hotels = documentSnapshots.toObjects(Hotels.class);

                            // Add all to your list

                            Thread t=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
                                    db.hotelDao().insertAll(hotels);
                                }
                            });
                            t.start();





                            Log.d("", "onSuccess: ");
                        }
                    }


                }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void DownloadCurrentUser()
    {

        FirebaseUser a=FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("user").whereEqualTo("email",a.getEmail()).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots)
                    {
                        if (documentSnapshots.isEmpty())
                        {
                            Log.d(" ", "onSuccess: LIST EMPTY");
                            return;
                        }
                        else
                        {

                            users = documentSnapshots.toObjects(User.class);

                            // Add all to your list
                            Thread t=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user").build();
                                    db.userDao().insertAll(users);
                                }
                            });
                            t.start();

                            Log.d("", "onSuccess: ");
                        }
                    }


                }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

}

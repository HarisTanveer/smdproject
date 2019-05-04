package com.example.bagpackers.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Location;
import com.example.bagpackers.Classes.Place;

import com.example.bagpackers.Classes.User;
import com.example.bagpackers.Daos.HotelDao;
import com.example.bagpackers.Daos.LocationDao;
import com.example.bagpackers.Daos.PlaceDao;

import com.example.bagpackers.Daos.UserDao;

@Database(entities =  {User.class, Hotels.class, Place.class, Location.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {


    @SuppressWarnings("WeakerAccess")
   // public abstract PlaceDao place();

    private static AppDatabase sInstance;


    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ex")
                    .build();
           // sInstance.populateInitialData();
        }
        return sInstance;
    }

//    @VisibleForTesting
//    public static void switchToInMemory(Context context) {
//        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
//                SampleDatabase.class).build();
//    }
//




    public abstract UserDao userDao();
    public abstract PlaceDao placeDao();
    public abstract LocationDao locationDao();
    public abstract HotelDao hotelDao();


}

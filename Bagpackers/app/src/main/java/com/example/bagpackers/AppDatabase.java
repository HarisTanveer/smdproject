package com.example.bagpackers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.bagpackers.User;

@Database(entities =  {User.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract com.example.bagpackers.UserDao userDao();

}

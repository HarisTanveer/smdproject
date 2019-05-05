package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Location;
import com.example.bagpackers.Classes.User;

import java.util.List;

@Dao
public interface LocationDao
{
    @Query("Select * from Location")
    List<Location> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Location... locations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Location location);

    @Delete
    void delete(Location location);
}

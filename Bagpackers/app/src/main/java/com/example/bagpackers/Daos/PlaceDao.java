package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.Classes.User;

import java.util.List;

@Dao
public interface PlaceDao
{

    @Query("DELETE FROM " + "place" + " WHERE " + "pid" + " = :id")
    int deleteById(long id);

    @Query("SELECT * FROM " + "Place")
    Cursor selectAll();

    @Query("SELECT * FROM " + "Place" + " WHERE " + "pid" + " = :id")
    Cursor selectById(long id);

    @Query("Select * from Place")
    List<Place> getAll();

    @Query("Select * from Place where location=:loc")
    List<Place> getAllbyProvince(String loc);

    @Query("Select * from Place where pid = :first limit 1")
    Place findByName(int first);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Place... places);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Delete
    void delete(Place place);
}

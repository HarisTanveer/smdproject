package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.Classes.User;

import java.util.List;

@Dao
public interface PlaceDao
{
    @Query("Select * from Place")
    List<Place> getAll();

    @Query("Select * from Place where pid In (:placeIds)")
    List<Place> loadAllByIds(int[] placeIds);

    @Query("Select * from Place where pid = :first limit 1")
    Place findByName(int first);

    @Insert
    void insertAll(Place... places);

    @Insert
    void insert(Place place);

    @Delete
    void delete(Place place);
}

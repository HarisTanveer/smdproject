package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.User;

import java.util.List;

@Dao
public interface HotelDao {
    @Query("Select * from hotels")
    List<Hotels> getAll();

    @Query("Select * from hotels where hid In (:placeIds)")
    List<Hotels> loadAllByIds(int[] placeIds);

    @Query("Select * from hotels where hid = :first limit 1")
    Hotels findByName(int first);

    @Insert
    void insertAll(Hotels... hotels);

    @Insert
    void insert(Hotels hotel);

    @Delete
    void delete(Hotels hotel);
}

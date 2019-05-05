package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bagpackers.Classes.Hotels;
import com.example.bagpackers.Classes.User;

import java.util.List;

@Dao
public interface HotelDao {
    @Query("Select * from hotels")
    List<Hotels> getAll();

    @Query("Select * from hotels where Placeid =:placeIds")
    List<Hotels> loadAllByIds(String placeIds);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Hotels> hotels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Hotels hotel);

    @Delete
    void delete(Hotels hotel);
}

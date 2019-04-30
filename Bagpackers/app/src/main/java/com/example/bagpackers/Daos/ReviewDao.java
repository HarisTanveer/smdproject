package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.bagpackers.Classes.Place;
import com.example.bagpackers.Classes.Reviews;

import java.util.List;

@Dao
public interface ReviewDao
{
    @Query("Select * from Reviews")
    List<Reviews> getAll();

    @Query("Select * from Reviews where rid In (:placeIds)")
    List<Reviews> loadAllByIds(int[] placeIds);

    @Query("Select * from Reviews where rid = :first limit 1")
    Reviews findByName(int first);

    @Insert
    void insertAll(Reviews... reviews);

    @Insert
    void insert(Reviews reviews);

    @Delete
    void delete(Reviews reviews);
}

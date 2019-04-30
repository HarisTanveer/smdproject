package com.example.bagpackers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from user")
    List<User> getAll();

    @Query("Select * from user where email In (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("Select * from user where email = :first limit 1")
    User findByName(String first);

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}

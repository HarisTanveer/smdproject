package com.example.bagpackers.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bagpackers.Classes.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from user")
    List<User> getAll();

    @Query("Select * from user where email In (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("Select * from user where email = :first limit 1")
    User findByName(String first);

    @Query("Select * from user where email = :email and password=:password")
    User login(String email, String password);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);
}

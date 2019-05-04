package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "number")
    public String number;


    public User()
    {

    }

    @Ignore
    public User(String name, String email, String password, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
    }
}

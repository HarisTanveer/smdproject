package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

@IgnoreExtraProperties
@Entity
public class Location implements Serializable {

    @NonNull
    @PrimaryKey
    public String province;



    @ColumnInfo(name = "place")
    public int placeid;


    public Location()
    {

    }


    @Ignore
   public Location(String Provinc)
    {
        province=Provinc;
    }


}

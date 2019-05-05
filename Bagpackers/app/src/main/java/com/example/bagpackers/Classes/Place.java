package com.example.bagpackers.Classes;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
@IgnoreExtraProperties
@Entity(primaryKeys = {"name","location"})
public class  Place implements Serializable
{
//    @PrimaryKey(autoGenerate = true)
//    public int pid;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "picture")
    public String picture;

    @ColumnInfo(name = "reviews")
    public String reviews;



    public Place()
    {

    }

    @Ignore
    public Place(String name,String location,String description ,String picture)
    {
        this.name=name;
        this.location=location;
        this.description=description;
        this.picture=picture;


    }

//    @Ignore
//  public  ArrayList<Hotels> hotels=new ArrayList<Hotels>();




}

package com.example.bagpackers.Classes;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;


import com.example.bagpackers.R;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = Hotels.class,
        parentColumns = "hid",
        childColumns = "hotel"))
public class  Place implements Serializable
{
    @PrimaryKey
    public int pid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "description")
    public String description;



    @ColumnInfo(name = "picture")
    public String picture;

    @ColumnInfo(name = "reviews")
    public String reviews;

    public int getid()
    {
        return pid;
    }

    public Place(String name,String location,String description ,String picture)
    {
        this.name=name;
        this.location=location;
        this.description=description;
        this.picture=picture;


    }
    public Place()
    {

    }

  public  ArrayList<Hotels> hotels=new ArrayList<Hotels>();




}

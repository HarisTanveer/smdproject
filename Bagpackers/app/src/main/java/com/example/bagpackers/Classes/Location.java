package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = Place.class,
        parentColumns = "pid",
        childColumns = "place"))
public class Location {
    @PrimaryKey
    public int lid;

    @ColumnInfo(name = "province")
    public String province;



    @ColumnInfo(name = "place")
    public int placeid;


    public Location() {

    }

    public ArrayList<Place> places=new ArrayList<>();

   public Location(String Provinc)
    {
        province=Provinc;
    }


}

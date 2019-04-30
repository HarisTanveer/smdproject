package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Place.class,
        parentColumns = "pid",
        childColumns = "place"))
public class Location
{
    @PrimaryKey
    public int lid;

    @ColumnInfo(name = "province")
    public String province;

    @ColumnInfo(name = "city")
    public float city;

    @ColumnInfo(name = "place")
    public int placeid;

}

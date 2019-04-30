package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Place.class,
        parentColumns = "pid",
        childColumns = "place"))
public class Hotels
{
    @PrimaryKey
    public int hid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "rating")
    public float rating;

    @ColumnInfo(name = "rate")
    public float rate;

    @ColumnInfo(name = "number")
    public String number;

}

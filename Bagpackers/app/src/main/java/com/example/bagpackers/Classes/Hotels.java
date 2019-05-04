package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.widget.ImageView;

@Entity(foreignKeys = @ForeignKey(entity = Place.class,
        parentColumns = "pid",
        childColumns = "placeid"))
public class Hotels
{
    @PrimaryKey
    public int hid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "placeid")
    public Integer placeid;

    @ColumnInfo(name = "rating")
    public double rating;

    @ColumnInfo(name = "rate")
    public double rate;

    @ColumnInfo(name = "number")
    public String number;

    public Hotels()
    {

    }
    public Hotels(String nam,Integer plac,String numbe,double pric,double rat)
    {
        name=nam;
        placeid=plac;
        number=numbe;
        rate=pric;
        rating=rat;

    }

}

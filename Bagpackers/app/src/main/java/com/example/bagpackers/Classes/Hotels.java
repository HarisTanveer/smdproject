package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.widget.ImageView;

import java.io.Serializable;

@Entity
public class Hotels implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int hid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "placeid")
    public String placeid;

    @ColumnInfo(name = "rating")
    public double rating;

    @ColumnInfo(name = "rate")
    public double rate;

    @ColumnInfo(name = "number")
    public String number;

    @Ignore
    public Hotels(String name,String plac,String numbe,double pric,double rat)
    {
        this.name=name;
        placeid=plac;
        number=numbe;
        rate=pric;
        rating=rat;

    }
    public Hotels()
    {

    }

}

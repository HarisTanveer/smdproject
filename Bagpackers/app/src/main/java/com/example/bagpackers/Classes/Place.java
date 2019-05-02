package com.example.bagpackers.Classes;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;

import com.example.bagpackers.R;

@Entity(foreignKeys = @ForeignKey(entity = Hotels.class,
        parentColumns = "hid",
        childColumns = "hotel"))
public class Place
{
    @PrimaryKey
    public int pid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "hotel")
    public String hotel;

    @ColumnInfo(name = "picture")
    public String picture;

    @ColumnInfo(name = "reviews")
    public String reviews;


}

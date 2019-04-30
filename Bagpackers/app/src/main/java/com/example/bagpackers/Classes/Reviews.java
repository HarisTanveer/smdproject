package com.example.bagpackers.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "uid",
                childColumns = "user"),
        @ForeignKey(entity = Place.class,
                parentColumns = "pid",
                childColumns = "place")

})
public class Reviews
{
    @PrimaryKey
    public int rid;

    @ColumnInfo(name = "user")
    public int user;

    @ColumnInfo(name = "place")
    public int placeid;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "picture")
    public String picture;


}

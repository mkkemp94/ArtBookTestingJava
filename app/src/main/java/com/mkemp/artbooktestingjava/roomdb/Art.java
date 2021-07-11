package com.mkemp.artbooktestingjava.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "arts")
public class Art
{
    private final String name;
    private final String artistName;
    private final int year;
    private final String imageUrl;

    @PrimaryKey(autoGenerate = true)
    private final int id;

    public Art(String name, String artistName, int year, String imageUrl, int id)
    {
        this.name = name;
        this.artistName = artistName;
        this.year = year;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public int getYear()
    {
        return year;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public int getId()
    {
        return id;
    }
}

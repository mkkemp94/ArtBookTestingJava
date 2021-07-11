package com.mkemp.artbooktestingjava.roomdb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "arts")
public class Art
{
    private String name;
    private String artistName;
    private int year;
    private String imageUrl;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Art(String name, String artistName, int year, String imageUrl, int id)
    {
        this.name = name;
        this.artistName = artistName;
        this.year = year;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    @Ignore
    public Art(String name, String artistName, int year, String imageUrl)
    {
        this.name = name;
        this.artistName = artistName;
        this.year = year;
        this.imageUrl = imageUrl;
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
}

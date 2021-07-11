package com.mkemp.artbooktestingjava.roomdb;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ArtDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArt(Art art);

    @Delete
    void deleteArt(Art art);

    @Query("SELECT * FROM arts")
    LiveData<List<Art>> observeArts();
}

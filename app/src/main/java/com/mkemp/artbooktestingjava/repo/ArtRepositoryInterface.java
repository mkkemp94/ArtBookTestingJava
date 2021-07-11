package com.mkemp.artbooktestingjava.repo;

import com.mkemp.artbooktestingjava.model.ImageResponse;
import com.mkemp.artbooktestingjava.roomdb.Art;
import com.mkemp.artbooktestingjava.util.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface ArtRepositoryInterface
{
    void insertArt(Art art);

    void deleteArt(Art art);

    LiveData<List<Art>> getArt();

    Resource<ImageResponse> searchImage(String imageString);
}

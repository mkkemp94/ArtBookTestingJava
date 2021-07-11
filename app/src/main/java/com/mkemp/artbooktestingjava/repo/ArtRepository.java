package com.mkemp.artbooktestingjava.repo;

import com.mkemp.artbooktestingjava.api.RetrofitAPI;
import com.mkemp.artbooktestingjava.model.ImageResponse;
import com.mkemp.artbooktestingjava.roomdb.Art;
import com.mkemp.artbooktestingjava.roomdb.ArtDao;
import com.mkemp.artbooktestingjava.util.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import retrofit2.Response;

import static com.mkemp.artbooktestingjava.util.Util.API_KEY;

public class ArtRepository implements ArtRepositoryInterface
{
    private final ArtDao artDao;
    private final RetrofitAPI retrofitAPI;

    @Inject
    public ArtRepository(ArtDao artDao, RetrofitAPI retrofitAPI)
    {
        this.artDao = artDao;
        this.retrofitAPI = retrofitAPI;
    }

    @Override
    public void insertArt(Art art)
    {
        artDao.insertArt(art);
    }

    @Override
    public void deleteArt(Art art)
    {
        artDao.deleteArt(art);
    }

    @Override
    public LiveData<List<Art>> getArt()
    {
        return artDao.observeArts();
    }

    @Override
    public Resource<ImageResponse> searchImage(String imageString)
    {
        try
        {
            final Response<ImageResponse> response = retrofitAPI.imageSearch(imageString, API_KEY).execute();
            if (response.isSuccessful())
            {
                if (response.body() != null)
                {
                    return Resource.success(response.body());
                }
                else {
                    return Resource.error("Error", null);
                }
            }
            else
            {
                return Resource.error("Error", null);
            }
        }
        catch (Exception e)
        {
            return Resource.error("No data!", null);
        }
    }
}

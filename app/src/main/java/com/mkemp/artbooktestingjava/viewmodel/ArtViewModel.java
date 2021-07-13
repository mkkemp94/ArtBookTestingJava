package com.mkemp.artbooktestingjava.viewmodel;

import com.mkemp.artbooktestingjava.model.ImageResponse;
import com.mkemp.artbooktestingjava.repo.ArtRepositoryInterface;
import com.mkemp.artbooktestingjava.roomdb.Art;
import com.mkemp.artbooktestingjava.util.Resource;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ArtViewModel extends ViewModel
{
    private final ExecutorService executor;
    private final ArtRepositoryInterface repository;

    @Inject
    public ArtViewModel(ExecutorService executor, ArtRepositoryInterface repository)
    {
        this.repository = repository;
        this.executor = executor;
    }

    // Art Fragment

    public LiveData<List<Art>> getArtList()
    {
        return repository.getArt();
    }

    // Image API Fragment

    private final MutableLiveData<Resource<ImageResponse>> images = new MutableLiveData<>();

    public LiveData<Resource<ImageResponse>> getImageList()
    {
        return images;
    }

    private final MutableLiveData<String> selectedImage = new MutableLiveData<>();

    public LiveData<String> getSelectedImageUrl()
    {
        return selectedImage;
    }

    // Art Details Fragment

    private MutableLiveData<Resource<Art>> insertArtMsg = new MutableLiveData<>();

    public LiveData<Resource<Art>> getInsertArtMessage()
    {
        return insertArtMsg;
    }

    public void resetInsertArtMessage()
    {
        insertArtMsg = new MutableLiveData<>();
    }

    // Functions

    public void setSelectedImage(String url)
    {
        selectedImage.postValue(url);
    }

    public void deleteArt(Art art)
    {
        executor.execute(() -> repository.deleteArt(art));
    }

    public void insertArt(Art art)
    {
        executor.execute(() -> repository.insertArt(art));
    }

    public void makeArt(String name, String artistName, String year)
    {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty())
        {
            insertArtMsg.postValue(Resource.error("Enter name, artist, year", null));
            return;
        }

        final int yearInt;
        try
        {
            yearInt = Integer.parseInt(year);
        }
        catch (Exception e)
        {
            insertArtMsg.postValue(Resource.error("Year should be a number", null));
            return;
        }

        final String selectedImageUrl = selectedImage.getValue();
        final Art art = new Art(
                name,
                artistName,
                yearInt,
                selectedImageUrl == null ? "" : selectedImageUrl
        );

        insertArt(art);
        setSelectedImage("");
        insertArtMsg.postValue(Resource.success(art));
    }

    public void searchForImage(String searchString)
    {
        if (searchString.isEmpty())
        {
            return;
        }

        images.setValue(Resource.loading(null));
        executor.execute(() ->
        {
            final Resource<ImageResponse> response = repository.searchImage(searchString);
            images.postValue(response);
        });
    }
}

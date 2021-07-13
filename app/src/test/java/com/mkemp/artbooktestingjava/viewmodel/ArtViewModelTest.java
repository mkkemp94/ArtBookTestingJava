package com.mkemp.artbooktestingjava.viewmodel;

import com.mkemp.artbooktestingjava.model.ImageResponse;
import com.mkemp.artbooktestingjava.repo.ArtRepositoryInterface;
import com.mkemp.artbooktestingjava.roomdb.Art;
import com.mkemp.artbooktestingjava.util.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ArtViewModelTest
{
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    // region constants
    public static final String NAME = "name";
    public static final String ARTISTNAME = "artistname";
    public static final String YEAR = "2021";
    // endregion constants

    // region helper fields
    ArtViewModel SUT;
    ArtRepositoryTd repository;
    @Mock ExecutorService executorService;

    @Mock Observer<Resource<Art>> artResourceObserver;
    @Captor ArgumentCaptor<Resource<Art>> artResourceCaptor;

    @Mock Observer<String> selectedImageObserver;
    @Captor ArgumentCaptor<String> selectedImageCaptor;
    // endregion helper fields

    @Before
    public void setup()
    {
        implementAsDirectExecutor(executorService);

        repository = new ArtRepositoryTd();
        SUT = new ArtViewModel(executorService, repository);
        SUT.getInsertArtMessage().observeForever(artResourceObserver);
        SUT.getSelectedImageUrl().observeForever(selectedImageObserver);
    }

    @Test
    public void makeArt_withoutYear_returnsError()
    {
        // Arrange

        // Act
        SUT.makeArt(NAME, ARTISTNAME, "");

        // Assert
        verify(artResourceObserver).onChanged(artResourceCaptor.capture());

        assertEquals(Resource.Status.ERROR, artResourceCaptor.getValue().status);
    }

    @Test
    public void makeArt_invalidYearCharacter_returnsError()
    {
        // Arrange

        // Act
        SUT.makeArt(NAME, ARTISTNAME, "556j");

        // Assert
        verify(artResourceObserver).onChanged(artResourceCaptor.capture());

        assertEquals(Resource.Status.ERROR, artResourceCaptor.getValue().status);
    }

    @Test
    public void makeArt_withoutName_returnsError()
    {
        // Arrange

        // Act
        SUT.makeArt("", ARTISTNAME, YEAR);

        // Assert
        verify(artResourceObserver).onChanged(artResourceCaptor.capture());

        assertEquals(Resource.Status.ERROR, artResourceCaptor.getValue().status);
    }

    @Test
    public void makeArt_withoutArtistName_returnsError()
    {
        // Arrange

        // Act
        SUT.makeArt(NAME, "", YEAR);

        // Assert
        verify(artResourceObserver).onChanged(artResourceCaptor.capture());

        assertEquals(Resource.Status.ERROR, artResourceCaptor.getValue().status);
    }

    @Test
    public void makeArt_allCorrectInfo_returnsSuccess()
    {
        // Arrange

        // Act
        SUT.makeArt(NAME, ARTISTNAME, YEAR);

        // Assert
        verify(artResourceObserver).onChanged(artResourceCaptor.capture());

        assertEquals(Resource.Status.SUCCESS, artResourceCaptor.getValue().status);
    }

    @Test
    public void makeArt_allCorrectInfo_insertsArtIntoRepository()
    {
        // Arrange

        // Act
        SUT.makeArt(NAME, ARTISTNAME, YEAR);

        // Assert
        verify(executorService, times(1)).execute(any(Runnable.class));
        assertThat(repository.insertCount).isEqualTo(1);
    }

    @Test
    public void makeArt_allCorrectInfo_clearsSelectedImageUrl()
    {
        // Arrange

        // Act
        SUT.makeArt(NAME, ARTISTNAME, YEAR);

        // Assert
        verify(selectedImageObserver).onChanged(selectedImageCaptor.capture());

        assertEquals("", selectedImageCaptor.getValue());
    }

    // region helper methods
    protected void implementAsDirectExecutor(ExecutorService executor)
    {
        doAnswer((Answer<Object>) invocation ->
        {
            Object[] args = invocation.getArguments();
            Runnable runnable = (Runnable) args[0];
            runnable.run();
            return null;
        }).when(executor).execute(any(Runnable.class));
    }
    // endregion helper methods

    // region helper classes
    public static class ArtRepositoryTd implements ArtRepositoryInterface
    {
        private final ArrayList<Art> arts = new ArrayList<>();
        private final MutableLiveData<List<Art>> artsLiveData = new MutableLiveData<>();

        public int insertCount = 0;

        @Override
        public void insertArt(Art art)
        {
            insertCount++;
            arts.add(art);
            refreshData();
        }

        @Override
        public void deleteArt(Art art)
        {
            arts.remove(art);
            refreshData();
        }

        @Override
        public LiveData<List<Art>> getArt()
        {
            return artsLiveData;
        }

        @Override
        public Resource<ImageResponse> searchImage(String imageString)
        {
            return Resource.success(new ImageResponse(new ArrayList<>(), 0, 0));
        }

        private void refreshData()
        {
            artsLiveData.postValue(arts);
        }
    }
    // endregion helper classes
}
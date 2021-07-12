package com.mkemp.artbooktestingjava.di;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.api.RetrofitAPI;
import com.mkemp.artbooktestingjava.repo.ArtRepository;
import com.mkemp.artbooktestingjava.repo.ArtRepositoryInterface;
import com.mkemp.artbooktestingjava.roomdb.ArtDao;
import com.mkemp.artbooktestingjava.roomdb.ArtDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mkemp.artbooktestingjava.util.Util.BASE_URL;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule
{
    @Singleton
    @Provides
    public ExecutorService provideExecutorService()
    {
        return Executors.newFixedThreadPool(10);
    }

    @Singleton
    @Provides
    public ArtDatabase injectRoomDatabase(
            @ApplicationContext Context context
    )
    {
        return Room.databaseBuilder(context, ArtDatabase.class, "ArtBookDB").build();
    }

    @Singleton
    @Provides
    public ArtDao injectDao(ArtDatabase database)
    {
        return database.artDao();
    }

    @Singleton
    @Provides
    public RetrofitAPI injectRetrofitAPI()
    {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(RetrofitAPI.class);
    }

    @Singleton
    @Provides
    public ArtRepositoryInterface injectNormalRepo(ArtDao artDao, RetrofitAPI retrofitAPI)
    {
        return new ArtRepository(artDao, retrofitAPI);
    }

    @Singleton
    @Provides
    public RequestManager injectGlide(@ApplicationContext Context context)
    {
        return Glide.with(context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground)
                );
    }
}

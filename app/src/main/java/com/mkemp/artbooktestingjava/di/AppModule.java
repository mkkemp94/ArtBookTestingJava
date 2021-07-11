package com.mkemp.artbooktestingjava.di;

import android.content.Context;

import com.mkemp.artbooktestingjava.api.RetrofitAPI;
import com.mkemp.artbooktestingjava.roomdb.ArtDao;
import com.mkemp.artbooktestingjava.roomdb.ArtDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mkemp.artbooktestingjava.util.Util.BASE_URL;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule
{
    @Singleton
    @Provides
    public RoomDatabase.Builder<ArtDatabase> injectRoomDatabase(
            @ApplicationContext Context context
    )
    {
        return Room.databaseBuilder(context, ArtDatabase.class, "ArtBookDB");
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
}

package com.mkemp.artbooktestingjava.api;

import com.mkemp.artbooktestingjava.model.ImageResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI
{
    @GET("/api/")
    Response<ImageResponse> imageSearch(@Query("q") String searchQuery,
                                        @Query("key") String apiKey);
}

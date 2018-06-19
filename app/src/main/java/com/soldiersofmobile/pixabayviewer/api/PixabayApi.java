package com.soldiersofmobile.pixabayviewer.api;

import com.soldiersofmobile.pixabayviewer.api.model.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {

    //https://pixabay.com/api/?key=6789991-13556dcf7bcb9aa8ec9945744&q=yellow+flowers&image_type=photo&pretty=true
    @GET("/api/?key=6789991-13556dcf7bcb9aa8ec9945744&image_type=photo")
    Call<PixabayResponse> getSearch(@Query("q") String query);
    //, @Query("key") String key, @Query("image_type") String imageType
}

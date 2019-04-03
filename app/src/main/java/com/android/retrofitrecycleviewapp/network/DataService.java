package com.android.retrofitrecycleviewapp.network;

import com.android.retrofitrecycleviewapp.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("photos")
    Call<List<RetroPhoto>> getPhotos();
}

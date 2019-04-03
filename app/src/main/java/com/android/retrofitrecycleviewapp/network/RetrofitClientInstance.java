package com.android.retrofitrecycleviewapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    public static Retrofit sRetrofit;
    public static final String BASE_URL="https://jsonplaceholder.typicode.com";

    private void RetrofitClientInstance(){}

    public static Retrofit getRetrofitInstance(){

        if (sRetrofit==null){
            sRetrofit =new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

            return sRetrofit;

    }
}

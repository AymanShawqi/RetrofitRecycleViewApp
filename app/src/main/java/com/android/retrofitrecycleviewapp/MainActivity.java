package com.android.retrofitrecycleviewapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.retrofitrecycleviewapp.network.DataService;
import com.android.retrofitrecycleviewapp.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.recycler);

        mProgressBar=findViewById(R.id.progress);

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        DataService dataService=retrofit.create(DataService.class);
        Call<List<RetroPhoto>> call=dataService.getPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                if (!response.isSuccessful()){
                    String message=String.valueOf(response.code());
                    handleFailure(message);
                    return;
                }
                mProgressBar.setVisibility(View.GONE);
                generateDataList(response.body());
            }
            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                String message=t.getMessage();
                handleFailure(message);
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<RetroPhoto> dataList){
        CustomAdapter adapter=new CustomAdapter(this,dataList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //handle connection failure
    private void handleFailure(String message){
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT);
    }
}

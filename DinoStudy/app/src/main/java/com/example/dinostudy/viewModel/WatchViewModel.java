package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.AddWatchData;
import com.example.dinostudy.model.AddWatchResponse;
import com.example.dinostudy.model.CreateWatchResponse;
import com.example.dinostudy.model.CreateWatchData;
import com.example.dinostudy.model.EditWatchData;
import com.example.dinostudy.model.EditWatchResponse;
import com.example.dinostudy.model.ReadWatchData;
import com.example.dinostudy.model.ReadWatchResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchViewModel extends AndroidViewModel {
    public MutableLiveData<ReadWatchResponse> readResult = new MutableLiveData<>();
    public MutableLiveData<CreateWatchResponse> createResult = new MutableLiveData<>();
    public MutableLiveData<AddWatchResponse> addResult = new MutableLiveData<>();
    public MutableLiveData<EditWatchResponse> editResult = new MutableLiveData<>();

    ServiceApi service;


    public WatchViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
    }


    public void createWatch(CreateWatchData data){
        System.out.println("********* createWatchData *********");

        service.createWatchData(data).enqueue(new Callback<CreateWatchResponse>() {
            @Override
            public void onResponse(Call<CreateWatchResponse> call, Response<CreateWatchResponse> response) {
                CreateWatchResponse result = response.body();
                createResult.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<CreateWatchResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readWatch(ReadWatchData data){
        System.out.println("********* readWatchData *********");

        service.readWatchData(data).enqueue(new Callback<ReadWatchResponse>() {
            @Override
            public void onResponse(Call<ReadWatchResponse> call, Response<ReadWatchResponse> response) {
                ReadWatchResponse result = response.body();
                readResult.postValue(result);
                System.out.println("read resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<ReadWatchResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void addWatch(AddWatchData data){
        System.out.println("********* addWatchData *********");

        service.addWatchData(data).enqueue(new Callback<AddWatchResponse>() {
            @Override
            public void onResponse(Call<AddWatchResponse> call, Response<AddWatchResponse> response) {
                AddWatchResponse result = response.body();
                addResult.postValue(result);
                System.out.println("add resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<AddWatchResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void editWatch(EditWatchData data){
        System.out.println("********* EditWatchData *********");

        service.editWatchData(data).enqueue(new Callback<EditWatchResponse>() {
            @Override
            public void onResponse(Call<EditWatchResponse> call, Response<EditWatchResponse> response) {
                EditWatchResponse result = response.body();
                editResult.postValue(result);
                System.out.println("edit resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<EditWatchResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
}
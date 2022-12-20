package com.example.orion.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.orion.model.heart.CreateGameData;
import com.example.orion.model.heart.CreateGameResponse;
import com.example.orion.model.heart.EditGameData;
import com.example.orion.model.heart.EditGameResponse;
import com.example.orion.model.heart.ReadGameData;
import com.example.orion.model.heart.ReadGameResponse;
import com.example.orion.repository.RetrofitClient;
import com.example.orion.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeartViewModel extends AndroidViewModel {
    public MutableLiveData<CreateGameResponse> createResult = new MutableLiveData<>();
    public MutableLiveData<ReadGameResponse> readResult = new MutableLiveData<>();
    public MutableLiveData<EditGameResponse> editResult = new MutableLiveData<>();


    ServiceApi service;

    public HeartViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
    }

    public void createGame(CreateGameData data) {
        System.out.println("********* createGameData *********");

        service.createGame(data).enqueue(new Callback<CreateGameResponse>() {
            @Override
            public void onResponse(Call<CreateGameResponse> call, Response<CreateGameResponse> response) {
                CreateGameResponse result = response.body();
                createResult.postValue(result);
                System.out.println("read resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<CreateGameResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
    public void readGame(ReadGameData data){
        System.out.println("********* readGameData *********");

        service.readGame(data).enqueue(new Callback<ReadGameResponse>() {
            @Override
            public void onResponse(Call<ReadGameResponse> call, Response<ReadGameResponse> response) {
                ReadGameResponse result = response.body();
                readResult.postValue(result);
                System.out.println("read resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<ReadGameResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void editGame(EditGameData data){
        System.out.println("********* editGameData *********");

        service.editGame(data).enqueue(new Callback<EditGameResponse>() {
            @Override
            public void onResponse(Call<EditGameResponse> call, Response<EditGameResponse> response) {
                EditGameResponse result = response.body();
                editResult.postValue(result);
                System.out.println("edit resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<EditGameResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
}

package com.example.orion.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.orion.model.heart.CreateGameData;
import com.example.orion.model.heart.CreateGameResponse;
import com.example.orion.repository.RetrofitClient;
import com.example.orion.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeartViewModel extends AndroidViewModel {
    public MutableLiveData<CreateGameResponse> createResult = new MutableLiveData<>();

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
}

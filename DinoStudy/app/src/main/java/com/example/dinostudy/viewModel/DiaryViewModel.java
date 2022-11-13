package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.diary.AddDiaryData;
import com.example.dinostudy.model.diary.AddDiaryResponse;
import com.example.dinostudy.model.diary.CreateDiaryData;
import com.example.dinostudy.model.diary.CreateDiaryResponse;
import com.example.dinostudy.model.diary.ReadDiaryData;
import com.example.dinostudy.model.diary.ReadDiaryResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryViewModel extends AndroidViewModel {
    public MutableLiveData<ReadDiaryResponse> readResult = new MutableLiveData<>();
    public MutableLiveData<CreateDiaryResponse> createResult = new MutableLiveData<>();
    public MutableLiveData<AddDiaryResponse> addResult = new MutableLiveData<>();

    ServiceApi service;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
    }

    public void readDiary(ReadDiaryData data) {
        System.out.println("********* readDiaryData *********");

        service.readDiaryData(data).enqueue(new Callback<ReadDiaryResponse>() {
            @Override
            public void onResponse(Call<ReadDiaryResponse> call, Response<ReadDiaryResponse> response) {
                ReadDiaryResponse result = response.body();
                readResult.postValue(result);
                System.out.println("read resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<ReadDiaryResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void createDiary(CreateDiaryData data){
        System.out.println("********* createDiaryData *********");

        service.createDiaryData(data).enqueue(new Callback<CreateDiaryResponse>() {
            @Override
            public void onResponse(Call<CreateDiaryResponse> call, Response<CreateDiaryResponse> response) {
                CreateDiaryResponse result = response.body();
                createResult.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<CreateDiaryResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void addDiary(AddDiaryData data){
        System.out.println("********* addDiaryData *********");

        service.addDiaryData(data).enqueue(new Callback<AddDiaryResponse>() {
            @Override
            public void onResponse(Call<AddDiaryResponse> call, Response<AddDiaryResponse> response) {
                AddDiaryResponse result = response.body();
                addResult.postValue(result);
                System.out.println("add resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<AddDiaryResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
}

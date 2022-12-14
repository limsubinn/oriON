package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.mission.CreateMissionData;
import com.example.dinostudy.model.mission.CreateMissionResponse;
import com.example.dinostudy.model.mission.EditMissionData;
import com.example.dinostudy.model.mission.EditMissionResponse;
import com.example.dinostudy.model.mission.ReadMissionData;
import com.example.dinostudy.model.mission.ReadMissionResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissionViewModel extends AndroidViewModel {

    public MutableLiveData<CreateMissionResponse> createResult = new MutableLiveData<>();
    public MutableLiveData<ReadMissionResponse> readResult = new MutableLiveData<>();
    public MutableLiveData<ReadMissionResponse> readResult1 = new MutableLiveData<>();
    public MutableLiveData<ReadMissionResponse> readResult2 = new MutableLiveData<>();
    public MutableLiveData<ReadMissionResponse> readResult3 = new MutableLiveData<>();
    public MutableLiveData<EditMissionResponse> editResult = new MutableLiveData<>();

    ServiceApi service;

    public MissionViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
    }

    public void createMission(CreateMissionData data){
        System.out.println("********* createMissionData *********");

        service.createMission(data).enqueue(new Callback<CreateMissionResponse>() {
            @Override
            public void onResponse(Call<CreateMissionResponse> call, Response<CreateMissionResponse> response) {
                CreateMissionResponse result = response.body();
                createResult.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<CreateMissionResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readMission(ReadMissionData data){
        System.out.println("********* readMissionData *********");

        service.readMission(data).enqueue(new Callback<ReadMissionResponse>() {
            @Override
            public void onResponse(Call<ReadMissionResponse> call, Response<ReadMissionResponse> response) {
                ReadMissionResponse result = response.body();
                readResult.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<ReadMissionResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readMission1(ReadMissionData data){
        System.out.println("********* readMissionData *********");

        service.readMission(data).enqueue(new Callback<ReadMissionResponse>() {
            @Override
            public void onResponse(Call<ReadMissionResponse> call, Response<ReadMissionResponse> response) {
                ReadMissionResponse result = response.body();
                readResult1.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<ReadMissionResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readMission2(ReadMissionData data){
        System.out.println("********* readMissionData *********");

        service.readMission(data).enqueue(new Callback<ReadMissionResponse>() {
            @Override
            public void onResponse(Call<ReadMissionResponse> call, Response<ReadMissionResponse> response) {
                ReadMissionResponse result = response.body();
                readResult2.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<ReadMissionResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readMission3(ReadMissionData data){
        System.out.println("********* readMissionData *********");

        service.readMission(data).enqueue(new Callback<ReadMissionResponse>() {
            @Override
            public void onResponse(Call<ReadMissionResponse> call, Response<ReadMissionResponse> response) {
                ReadMissionResponse result = response.body();
                readResult3.postValue(result);
                System.out.println("create resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<ReadMissionResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void editMission(EditMissionData data){
        System.out.println("********* editMissionData *********");

        service.editMission(data).enqueue(new Callback<EditMissionResponse>() {
            @Override
            public void onResponse(Call<EditMissionResponse> call, Response<EditMissionResponse> response) {
                EditMissionResponse result = response.body();
                editResult.postValue(result);
                System.out.println("edit resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<EditMissionResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
}

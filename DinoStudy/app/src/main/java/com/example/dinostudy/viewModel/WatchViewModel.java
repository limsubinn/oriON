package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.watch.AddWatchData;
import com.example.dinostudy.model.watch.AddWatchResponse;
import com.example.dinostudy.model.watch.CreateWatchResponse;
import com.example.dinostudy.model.watch.CreateWatchData;
import com.example.dinostudy.model.watch.DeleteTimeData;
import com.example.dinostudy.model.watch.DeleteTimeResponse;
import com.example.dinostudy.model.watch.DeleteWatchData;
import com.example.dinostudy.model.watch.DeleteWatchResponse;
import com.example.dinostudy.model.watch.EditSubjectData;
import com.example.dinostudy.model.watch.EditSubjectResponse;
import com.example.dinostudy.model.watch.EditTimeData;
import com.example.dinostudy.model.watch.EditTimeResponse;
import com.example.dinostudy.model.watch.ReadWatchData;
import com.example.dinostudy.model.watch.ReadWatchResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchViewModel extends AndroidViewModel {
    public MutableLiveData<ReadWatchResponse> readResult = new MutableLiveData<>();
    public MutableLiveData<CreateWatchResponse> createResult = new MutableLiveData<>();
    public MutableLiveData<AddWatchResponse> addResult = new MutableLiveData<>();
    public MutableLiveData<EditSubjectResponse> editSubResult = new MutableLiveData<>();
    public MutableLiveData<EditTimeResponse> editTimeResult = new MutableLiveData<>();
    public MutableLiveData<DeleteWatchResponse> deleteResult = new MutableLiveData<>();
    public MutableLiveData<DeleteTimeResponse> deleteTimeResult = new MutableLiveData<>();

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

    public void editSubject (EditSubjectData data){
        System.out.println("********* EditSubjectData *********");

        service.editWatchSubject(data).enqueue(new Callback<EditSubjectResponse>() {
            @Override
            public void onResponse(Call<EditSubjectResponse> call, Response<EditSubjectResponse> response) {
                EditSubjectResponse result = response.body();
                editSubResult.postValue(result);
                System.out.println("edit resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<EditSubjectResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void editTime (EditTimeData data){
        System.out.println("********* EditTimeData *********");

        service.editWatchTime(data).enqueue(new Callback<EditTimeResponse>() {
            @Override
            public void onResponse(Call<EditTimeResponse> call, Response<EditTimeResponse> response) {
                EditTimeResponse result = response.body();
                editTimeResult.postValue(result);
                System.out.println("edit resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<EditTimeResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void deleteWatch(DeleteWatchData data){
        System.out.println("********* DeleteWatchData *********");

        service.deleteWatchData(data).enqueue(new Callback<DeleteWatchResponse>() {
            @Override
            public void onResponse(Call<DeleteWatchResponse> call, Response<DeleteWatchResponse> response) {
                DeleteWatchResponse result = response.body();
                deleteResult.postValue(result);
                System.out.println("delete resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<DeleteWatchResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void deleteTime (DeleteTimeData data) {
        System.out.println("********* DeleteTimeData *********");

        service.deleteWatchTime(data).enqueue(new Callback<DeleteTimeResponse>() {
            @Override
            public void onResponse(Call<DeleteTimeResponse> call, Response<DeleteTimeResponse> response) {
                DeleteTimeResponse result = response.body();
                deleteTimeResult.postValue(result);
                System.out.println("edit resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<DeleteTimeResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
}
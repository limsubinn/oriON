package com.example.orion.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.orion.model.chart.ReadChartData;
import com.example.orion.model.chart.ReadChartResponse;
import com.example.orion.repository.RetrofitClient;
import com.example.orion.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartViewModel extends AndroidViewModel {
    public MutableLiveData<ReadChartResponse> readResult = new MutableLiveData<>();
    ServiceApi service;

    public ChartViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
    }

    public void readChart(ReadChartData data){
        System.out.println("********* readChartData *********");

        service.readChart(data).enqueue(new Callback<ReadChartResponse>() {
            @Override
            public void onResponse(Call<ReadChartResponse> call, Response<ReadChartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ReadChartResponse result = response.body();
                    readResult.postValue(result);
                    System.out.println("success");
                }
            }

            @Override
            public void onFailure(Call<ReadChartResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }


}
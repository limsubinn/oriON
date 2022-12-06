package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.chart.ReadChartData;
import com.example.dinostudy.model.chart.ReadChartResponse;
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

import java.util.List;

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
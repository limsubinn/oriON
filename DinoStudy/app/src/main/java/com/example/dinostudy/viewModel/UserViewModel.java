package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.user.JoinData;
import com.example.dinostudy.model.user.JoinResponse;
import com.example.dinostudy.model.user.LoginData;
import com.example.dinostudy.model.user.LoginResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {
    public MutableLiveData<JoinResponse> joinResult = new MutableLiveData<>();
    public MutableLiveData<LoginResponse> loginResult = new MutableLiveData<>();


    ServiceApi service;
    //private SharedPreferences pref;


    public UserViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        //pref = application.getSharedPreferences();
    }


    // 계정 등록
    public void join (JoinData data) {
        System.out.println("********* joinData *********");

        service.join(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                joinResult.postValue(result);
                System.out.println("join resultCode: "+result.getCode());
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void login (LoginData data) {
        System.out.println("********* loginData *********");

        service.login(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                loginResult.postValue(result);
                System.out.println("login resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

}
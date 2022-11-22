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

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<LoginResponse> loginResult = new MutableLiveData<>();

    ServiceApi service;
    //private SharedPreferences pref;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        //pref = application.getSharedPreferences();

    }

    // 서버에서 응답받는 코드 -> 응답코드 받아서 성공/실패여부 확인?
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


//    public String getLoginMethod(){
//        return pref.getLoginMethod();
//    }
//
//    public void getLoginSession() {
//        String userId = " ";
//        Iterator<String> iterator = pref.getCookies().iterator();
//        if (iterator != null) {
//            while (iterator.hasNext()) {
//                userId = iterator.next();
//                userId = userId.split(";")[0].split("=")[1];
//                Log.d("SESSION", "getLoginSession: " +userId);
//            }
//        }
//        userIdLiveData.postValue(userId);
//    }

}
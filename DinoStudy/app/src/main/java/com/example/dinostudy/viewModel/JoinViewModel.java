package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.user.CheckUserData;
import com.example.dinostudy.model.user.CheckUserResponse;
import com.example.dinostudy.model.user.JoinData;
import com.example.dinostudy.model.user.JoinResponse;
import com.example.dinostudy.model.user.LoginData;
import com.example.dinostudy.model.user.LoginResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinViewModel extends AndroidViewModel {
    public MutableLiveData<CheckUserResponse> checkResult = new MutableLiveData<>();
    public MutableLiveData<JoinResponse> joinResult = new MutableLiveData<>();

    ServiceApi service;
    //private SharedPreferences pref;


    public JoinViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        //pref = application.getSharedPreferences();
    }

    // 아이디 중복 체크
    public void checkUser (CheckUserData data) {
        System.out.println("********* checkUserData *********");

        service.checkUser(data).enqueue(new Callback<CheckUserResponse>() {
            @Override
            public void onResponse(Call<CheckUserResponse> call, Response<CheckUserResponse> response) {
                CheckUserResponse result = response.body();
                checkResult.postValue(result);
                System.out.println("check resultCode: "+result.getCode());
            }

            @Override
            public void onFailure(Call<CheckUserResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
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
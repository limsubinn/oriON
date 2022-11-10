package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.CheckEmailData;
import com.example.dinostudy.model.CheckEmailResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<CheckEmailResponse> resultCode = new MutableLiveData<>();
    public MutableLiveData<String> userIdLiveData = new MutableLiveData<>();

    ServiceApi service;
    //private SharedPreferences pref;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        //pref = application.getSharedPreferences();

    }

    // 서버에서 응답받는 코드 -> 응답코드 받아서 성공/실패여부 확인?
    public void checkUserEmail(CheckEmailData data){
        System.out.println("#4 checkUserEmail 메서드 실행");
        System.out.println("email: "+ data);

        service.checkUserEmail(data).enqueue(new Callback<CheckEmailResponse>() {
            @Override
            public void onResponse(Call<CheckEmailResponse> call, Response<CheckEmailResponse> response) {
                CheckEmailResponse result = response.body();
                resultCode.postValue(result);
                System.out.println("#5 서버에서 받은 code값: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<CheckEmailResponse> call, Throwable t) {
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
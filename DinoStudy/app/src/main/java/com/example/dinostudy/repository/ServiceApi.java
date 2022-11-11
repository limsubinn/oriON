package com.example.dinostudy.repository;

import com.example.dinostudy.model.AddWatchData;
import com.example.dinostudy.model.AddWatchResponse;
import com.example.dinostudy.model.CheckEmailData;
import com.example.dinostudy.model.CheckEmailResponse;
import com.example.dinostudy.model.CreateWatchData;
import com.example.dinostudy.model.CreateWatchResponse;
import com.example.dinostudy.model.DeleteWatchData;
import com.example.dinostudy.model.DeleteWatchResponse;
import com.example.dinostudy.model.EditWatchData;
import com.example.dinostudy.model.EditWatchResponse;
import com.example.dinostudy.model.ReadWatchData;
import com.example.dinostudy.model.ReadWatchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    // 사용자의 이메일이 등록되어 있는지 확인
    @POST("/user/checkEmail")
    Call<CheckEmailResponse> checkUserEmail (@Body CheckEmailData data);

    // 사용자의 timer 데이터 생성
    @POST("/watch/create")
    Call<CreateWatchResponse> createWatchData (@Body CreateWatchData data);

    // 사용자의 timer 데이터 추가
    @POST("/watch/add")
    Call<AddWatchResponse> addWatchData (@Body AddWatchData data);

    // 사용자의 timer 데이터 읽기
    @POST("/watch/read")
    Call<ReadWatchResponse> readWatchData (@Body ReadWatchData data);

    // 사용자의 timer 데이터 수정
    @POST("/watch/edit")
    Call<EditWatchResponse> editWatchData (@Body EditWatchData data);

    // 사용자의 timer 데이터 삭제
    @POST("/watch/delete")
    Call<DeleteWatchResponse> deleteWatchData (@Body DeleteWatchData data);
}
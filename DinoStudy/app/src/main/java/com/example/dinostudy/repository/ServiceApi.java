package com.example.dinostudy.repository;

import com.example.dinostudy.model.AddDiaryData;
import com.example.dinostudy.model.AddDiaryResponse;
import com.example.dinostudy.model.AddWatchData;
import com.example.dinostudy.model.AddWatchResponse;
import com.example.dinostudy.model.CheckEmailData;
import com.example.dinostudy.model.CheckEmailResponse;
import com.example.dinostudy.model.CreateDiaryData;
import com.example.dinostudy.model.CreateDiaryResponse;
import com.example.dinostudy.model.CreateWatchData;
import com.example.dinostudy.model.CreateWatchResponse;
import com.example.dinostudy.model.DeleteTimeData;
import com.example.dinostudy.model.DeleteTimeResponse;
import com.example.dinostudy.model.DeleteWatchData;
import com.example.dinostudy.model.DeleteWatchResponse;
import com.example.dinostudy.model.EditSubjectData;
import com.example.dinostudy.model.EditSubjectResponse;
import com.example.dinostudy.model.EditTimeData;
import com.example.dinostudy.model.EditTimeResponse;
import com.example.dinostudy.model.ReadDiaryData;
import com.example.dinostudy.model.ReadDiaryResponse;
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

    // 사용자의 timer 과목 수정
    @POST("/watch/editSubject")
    Call<EditSubjectResponse> editWatchSubject (@Body EditSubjectData data);

    // 사용자의 timer 시간 수정
    @POST("/watch/editTime")
    Call<EditTimeResponse> editWatchTime (@Body EditTimeData data);

    // 사용자의 timer 데이터 삭제
    @POST("/watch/delete")
    Call<DeleteWatchResponse> deleteWatchData (@Body DeleteWatchData data);

    // 사용자의 timer 시간 삭제
    @POST("/watch/deleteTime")
    Call<DeleteTimeResponse> deleteWatchTime (@Body DeleteTimeData data);

    // 사용자의 diary 데이터 생성
    @POST("/diary/create")
    Call<CreateDiaryResponse> createDiaryData (@Body CreateDiaryData data);

    // 사용자의 diary 데이터 추가
    @POST("/diary/add")
    Call<AddDiaryResponse> addDiaryData (@Body AddDiaryData data);

    // 사용자의 diary 데이터 읽기
    @POST("/diary/read")
    Call<ReadDiaryResponse> readDiaryData (@Body ReadDiaryData data);
}
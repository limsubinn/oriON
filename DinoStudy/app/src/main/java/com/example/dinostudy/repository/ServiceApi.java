package com.example.dinostudy.repository;

import com.example.dinostudy.model.diary.AddDiaryData;
import com.example.dinostudy.model.diary.AddDiaryResponse;
import com.example.dinostudy.model.todo.AddTodoData;
import com.example.dinostudy.model.todo.AddTodoResponse;
import com.example.dinostudy.model.todo.CreateTodoData;
import com.example.dinostudy.model.todo.CreateTodoResponse;
import com.example.dinostudy.model.todo.DeleteTodoData;
import com.example.dinostudy.model.todo.DeleteTodoResponse;
import com.example.dinostudy.model.todo.EditTodoData;
import com.example.dinostudy.model.todo.EditTodoResponse;
import com.example.dinostudy.model.todo.ReadTodoData;
import com.example.dinostudy.model.todo.ReadTodoResponse;
import com.example.dinostudy.model.todo.UpdateCheckTodoData;
import com.example.dinostudy.model.todo.UpdateCheckTodoResponse;
import com.example.dinostudy.model.watch.AddWatchData;
import com.example.dinostudy.model.watch.AddWatchResponse;
import com.example.dinostudy.model.CheckEmailData;
import com.example.dinostudy.model.CheckEmailResponse;
import com.example.dinostudy.model.diary.CreateDiaryData;
import com.example.dinostudy.model.diary.CreateDiaryResponse;
import com.example.dinostudy.model.watch.CreateWatchData;
import com.example.dinostudy.model.watch.CreateWatchResponse;
import com.example.dinostudy.model.watch.DeleteTimeData;
import com.example.dinostudy.model.watch.DeleteTimeResponse;
import com.example.dinostudy.model.watch.DeleteWatchData;
import com.example.dinostudy.model.watch.DeleteWatchResponse;
import com.example.dinostudy.model.watch.EditSubjectData;
import com.example.dinostudy.model.watch.EditSubjectResponse;
import com.example.dinostudy.model.watch.EditTimeData;
import com.example.dinostudy.model.watch.EditTimeResponse;
import com.example.dinostudy.model.diary.ReadDiaryData;
import com.example.dinostudy.model.diary.ReadDiaryResponse;
import com.example.dinostudy.model.watch.ReadWatchData;
import com.example.dinostudy.model.watch.ReadWatchResponse;

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

    @POST("/todo/create")
    Call<CreateTodoResponse> createTodoData (@Body CreateTodoData data);

    @POST("/todo/read")
    Call<ReadTodoResponse> readTodoData (@Body ReadTodoData data);

    @POST("/todo/add")
    Call<AddTodoResponse> addTodoData (@Body AddTodoData data);

    @POST("/todo/edit")
    Call<EditTodoResponse> editTodoData (@Body EditTodoData data);

    @POST("/todo/delete")
    Call<DeleteTodoResponse> deleteTodoData (@Body DeleteTodoData data);

    @POST("/todo/edit/checkbox")
    Call<UpdateCheckTodoResponse> updateCheckTodoData (@Body UpdateCheckTodoData data);
}
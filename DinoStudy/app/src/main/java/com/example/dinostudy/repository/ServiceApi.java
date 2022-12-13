package com.example.dinostudy.repository;

import com.example.dinostudy.model.board.CreateCommentData;
import com.example.dinostudy.model.board.CreateCommentResponse;
import com.example.dinostudy.model.board.CreatePostData;
import com.example.dinostudy.model.board.CreatePostResponse;
import com.example.dinostudy.model.board.ReadCommentData;
import com.example.dinostudy.model.board.ReadCommentResponse;
import com.example.dinostudy.model.board.ReadPostResponse;
import com.example.dinostudy.model.chart.ReadChartData;
import com.example.dinostudy.model.chart.ReadChartResponse;
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
import com.example.dinostudy.model.todo.EditTodoCheckData;
import com.example.dinostudy.model.todo.EditTodoCheckResponse;
import com.example.dinostudy.model.user.CheckUserData;
import com.example.dinostudy.model.user.CheckUserResponse;
import com.example.dinostudy.model.user.JoinData;
import com.example.dinostudy.model.user.JoinResponse;
import com.example.dinostudy.model.watch.AddWatchData;
import com.example.dinostudy.model.watch.AddWatchResponse;
import com.example.dinostudy.model.user.LoginData;
import com.example.dinostudy.model.user.LoginResponse;
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
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {
    // 사용자의 이메일이 등록되어 있는지 확인
    @POST("/user/login")
    Call<LoginResponse> login(@Body LoginData data);

    // 사용자 아이디 중복 체크
    @POST("/user/check")
    Call<CheckUserResponse> checkUser(@Body CheckUserData data);

    // 사용자 계정 생성
    @POST("/user/join")
    Call<JoinResponse> join(@Body JoinData data);

    // 사용자의 timer 데이터 생성
    @POST("/watch/create")
    Call<CreateWatchResponse> createWatch(@Body CreateWatchData data);

    // 사용자의 timer 데이터 추가
    @POST("/watch/add")
    Call<AddWatchResponse> addWatch(@Body AddWatchData data);

    // 사용자의 timer 데이터 읽기
    @POST("/watch/read")
    Call<ReadWatchResponse> readWatch(@Body ReadWatchData data);

    // 사용자의 timer 과목 수정
    @POST("/watch/editSubject")
    Call<EditSubjectResponse> editWatchSubject(@Body EditSubjectData data);

    // 사용자의 timer 시간 수정
    @POST("/watch/editTime")
    Call<EditTimeResponse> editWatchTime(@Body EditTimeData data);

    // 사용자의 timer 데이터 삭제
    @POST("/watch/delete")
    Call<DeleteWatchResponse> deleteWatch(@Body DeleteWatchData data);

    // 사용자의 timer 시간 삭제
    @POST("/watch/deleteTime")
    Call<DeleteTimeResponse> deleteWatchTime(@Body DeleteTimeData data);

    // 사용자의 diary 데이터 생성
    @POST("/diary/create")
    Call<CreateDiaryResponse> createDiary(@Body CreateDiaryData data);

    // 사용자의 diary 데이터 추가
    @POST("/diary/add")
    Call<AddDiaryResponse> addDiary(@Body AddDiaryData data);

    // 사용자의 diary 데이터 읽기
    @POST("/diary/read")
    Call<ReadDiaryResponse> readDiary(@Body ReadDiaryData data);

    @POST("/todo/create")
    Call<CreateTodoResponse> createPost(@Body CreateTodoData data);

    @POST("/todo/read")
    Call<ReadTodoResponse> readTodo(@Body ReadTodoData data);

    @POST("/todo/add")
    Call<AddTodoResponse> addTodo(@Body AddTodoData data);

    @POST("/todo/edit")
    Call<EditTodoResponse> editTodo(@Body EditTodoData data);

    @POST("/todo/edit/checkbox")
    Call<EditTodoCheckResponse> editTodoCheck(@Body EditTodoCheckData data);

    @POST("/todo/delete")
    Call<DeleteTodoResponse> deleteTodo(@Body DeleteTodoData data);

    @POST("/chart/read")
    Call<ReadChartResponse> readChart(@Body ReadChartData data);

    @POST("/board/post/create")
    Call<CreatePostResponse> createPost(@Body CreatePostData data);

    @GET("/board/post/read")
    Call<ReadPostResponse> readPost();

    @POST("/board/comment/create")
    Call<CreateCommentResponse> createComment(@Body CreateCommentData data);

    @POST("board/comment/read")
    Call<ReadCommentResponse> readComment(@Body ReadCommentData data);
}
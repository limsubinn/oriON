package com.example.orion.repository;

import com.example.orion.model.board.CountCommentData;
import com.example.orion.model.board.CountCommentResponse;
import com.example.orion.model.board.CreateCommentData;
import com.example.orion.model.board.CreateCommentResponse;
import com.example.orion.model.board.CreatePostData;
import com.example.orion.model.board.CreatePostResponse;
import com.example.orion.model.board.DeleteCommentData;
import com.example.orion.model.board.DeleteCommentResponse;
import com.example.orion.model.board.DeletePostData;
import com.example.orion.model.board.DeletePostResponse;
import com.example.orion.model.board.EditCommentData;
import com.example.orion.model.board.EditCommentResponse;
import com.example.orion.model.board.EditPostData;
import com.example.orion.model.board.EditPostResponse;
import com.example.orion.model.board.ReadCommentData;
import com.example.orion.model.board.ReadCommentResponse;
import com.example.orion.model.board.ReadPostResponse;
import com.example.orion.model.chart.ReadChartData;
import com.example.orion.model.chart.ReadChartResponse;
import com.example.orion.model.diary.AddDiaryData;
import com.example.orion.model.diary.AddDiaryResponse;
import com.example.orion.model.heart.CreateGameData;
import com.example.orion.model.heart.CreateGameResponse;
import com.example.orion.model.heart.EditGameData;
import com.example.orion.model.heart.EditGameResponse;
import com.example.orion.model.heart.ReadGameData;
import com.example.orion.model.heart.ReadGameResponse;
import com.example.orion.model.mission.CreateMissionData;
import com.example.orion.model.mission.CreateMissionResponse;
import com.example.orion.model.mission.EditMissionData;
import com.example.orion.model.mission.EditMissionResponse;
import com.example.orion.model.mission.ReadMissionData;
import com.example.orion.model.mission.ReadMissionResponse;
import com.example.orion.model.todo.AddTodoData;
import com.example.orion.model.todo.AddTodoResponse;
import com.example.orion.model.todo.CreateTodoData;
import com.example.orion.model.todo.CreateTodoResponse;
import com.example.orion.model.todo.DeleteTodoData;
import com.example.orion.model.todo.DeleteTodoResponse;
import com.example.orion.model.todo.EditTodoData;
import com.example.orion.model.todo.EditTodoResponse;
import com.example.orion.model.todo.ReadTodoData;
import com.example.orion.model.todo.ReadTodoResponse;
import com.example.orion.model.todo.EditTodoCheckData;
import com.example.orion.model.todo.EditTodoCheckResponse;
import com.example.orion.model.user.EditCoinData;
import com.example.orion.model.user.EditCoinResponse;
import com.example.orion.model.user.JoinData;
import com.example.orion.model.user.JoinResponse;
import com.example.orion.model.user.ReadMyPostData;
import com.example.orion.model.user.ReadMyPostResponse;
import com.example.orion.model.watch.AddWatchData;
import com.example.orion.model.watch.AddWatchResponse;
import com.example.orion.model.user.LoginData;
import com.example.orion.model.user.LoginResponse;
import com.example.orion.model.diary.CreateDiaryData;
import com.example.orion.model.diary.CreateDiaryResponse;
import com.example.orion.model.watch.CreateWatchData;
import com.example.orion.model.watch.CreateWatchResponse;
import com.example.orion.model.watch.DeleteTimeData;
import com.example.orion.model.watch.DeleteTimeResponse;
import com.example.orion.model.watch.DeleteWatchData;
import com.example.orion.model.watch.DeleteWatchResponse;
import com.example.orion.model.watch.EditSubjectData;
import com.example.orion.model.watch.EditSubjectResponse;
import com.example.orion.model.watch.EditTimeData;
import com.example.orion.model.watch.EditTimeResponse;
import com.example.orion.model.diary.ReadDiaryData;
import com.example.orion.model.diary.ReadDiaryResponse;
import com.example.orion.model.watch.ReadWatchData;
import com.example.orion.model.watch.ReadWatchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {
    // 사용자의 이메일이 등록되어 있는지 확인
    @POST("/user/login")
    Call<LoginResponse> login(@Body LoginData data);

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

    @POST("/board/post/edit")
    Call<EditPostResponse> editPost(@Body EditPostData data);

    @POST("/board/post/delete")
    Call<DeletePostResponse> deletePost(@Body DeletePostData data);

    @POST("/board/comment/create")
    Call<CreateCommentResponse> createComment(@Body CreateCommentData data);

    @POST("board/comment/read")
    Call<ReadCommentResponse> readComment(@Body ReadCommentData data);

    @POST("board/comment/count")
    Call<CountCommentResponse> countComment(@Body CountCommentData data);

    @POST("board/comment/edit")
    Call<EditCommentResponse> editComment(@Body EditCommentData data);

    @POST("board/comment/delete")
    Call<DeleteCommentResponse> deleteComment(@Body DeleteCommentData data);

    @POST("/user/post/read")
    Call<ReadMyPostResponse> readUserPost(@Body ReadMyPostData data);

    @POST("/user/coin/edit")
    Call<EditCoinResponse> setCoin(@Body EditCoinData data);

    @POST("/mission/create")
    Call<CreateMissionResponse> createMission(@Body CreateMissionData data);

    @POST("/mission/read")
    Call<ReadMissionResponse> readMission(@Body ReadMissionData data);

    @POST("/mission/edit")
    Call<EditMissionResponse> editMission(@Body EditMissionData data);

    @POST("/game/create")
    Call<CreateGameResponse> createGame(@Body CreateGameData data);

    @POST("/game/read")
    Call<ReadGameResponse> readGame(@Body ReadGameData data);

    @POST("/game/edit")
    Call<EditGameResponse> editGame(@Body EditGameData data);
}
package com.example.orion.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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
import com.example.orion.repository.RetrofitClient;
import com.example.orion.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardViewModel extends AndroidViewModel {
    public MutableLiveData<CreatePostResponse> createPostResult = new MutableLiveData<>();
    public MutableLiveData<ReadPostResponse> readPostResult = new MutableLiveData<>();
    public MutableLiveData<EditPostResponse> editPostResult = new MutableLiveData<>();
    public MutableLiveData<DeletePostResponse> deletePostResult = new MutableLiveData<>();

    public MutableLiveData<CreateCommentResponse> createCommentResult = new MutableLiveData<>();
    public MutableLiveData<ReadCommentResponse> readCommentResult = new MutableLiveData<>();
    public MutableLiveData<CountCommentResponse> countCommentResult = new MutableLiveData<>();
    public MutableLiveData<EditCommentResponse> editCommentResult = new MutableLiveData<>();
    public MutableLiveData<DeleteCommentResponse> deleteCommentResult = new MutableLiveData<>();


    ServiceApi service;


    public BoardViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);

    }

    public void createPost(CreatePostData data) {
        System.out.println("********* createPostData *********");

        service.createPost(data).enqueue(new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                CreatePostResponse result = response.body();
                createPostResult.postValue(result);
                System.out.println("createPost resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readPost() {
        System.out.println("********* readPostData *********");

        service.readPost().enqueue(new Callback<ReadPostResponse>() {
            @Override
            public void onResponse(Call<ReadPostResponse> call, Response<ReadPostResponse> response) {
                ReadPostResponse result = response.body();
                readPostResult.postValue(result);
                System.out.println("read post resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<ReadPostResponse> call, Throwable t) {
                System.out.println("ReadPostResponse fail");
                t.printStackTrace();
            }
        });
    }

    public void editPost(EditPostData data) {
        System.out.println("********* editPostData *********");

        service.editPost(data).enqueue(new Callback<EditPostResponse>() {
            @Override
            public void onResponse(Call<EditPostResponse> call, Response<EditPostResponse> response) {
                EditPostResponse result = response.body();
                editPostResult.postValue(result);
                System.out.println("edit post resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<EditPostResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void deletePost(DeletePostData data) {
        System.out.println("********* deletePostData *********");

        service.deletePost(data).enqueue(new Callback<DeletePostResponse>() {
            @Override
            public void onResponse(Call<DeletePostResponse> call, Response<DeletePostResponse> response) {
                DeletePostResponse result = response.body();
                deletePostResult.postValue(result);
                System.out.println("delete post resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<DeletePostResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void createComment(CreateCommentData data) {
        System.out.println("********* createCommentData *********");

        service.createComment(data).enqueue(new Callback<CreateCommentResponse>() {
            @Override
            public void onResponse(Call<CreateCommentResponse> call, Response<CreateCommentResponse> response) {
                CreateCommentResponse result = response.body();
                createCommentResult.postValue(result);
                System.out.println("createPost resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<CreateCommentResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readComment(ReadCommentData data) {
        System.out.println("********* readCommentData *********");

        service.readComment(data).enqueue(new Callback<ReadCommentResponse>() {
            @Override
            public void onResponse(Call<ReadCommentResponse> call, Response<ReadCommentResponse> response) {
                ReadCommentResponse result = response.body();
                readCommentResult.postValue(result);
                System.out.println("read comment resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<ReadCommentResponse> call, Throwable t) {
                System.out.println("ReadCommentResponse fail");
                t.printStackTrace();
            }
        });
    }

    public void countComment(CountCommentData data) {
        System.out.println("********* countCommentData *********");

        service.countComment(data).enqueue(new Callback<CountCommentResponse>() {
            @Override
            public void onResponse(Call<CountCommentResponse> call, Response<CountCommentResponse> response) {
                CountCommentResponse result = response.body();
                countCommentResult.postValue(result);
                System.out.println("countComment resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<CountCommentResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void editComment(EditCommentData data) {
        System.out.println("********* editCommentData *********");

        service.editComment(data).enqueue(new Callback<EditCommentResponse>() {
            @Override
            public void onResponse(Call<EditCommentResponse> call, Response<EditCommentResponse> response) {
                EditCommentResponse result = response.body();
                editCommentResult.postValue(result);
                System.out.println("editComment resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<EditCommentResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void deleteComment(DeleteCommentData data) {
        System.out.println("********* deleteCommentData *********");

        service.deleteComment(data).enqueue(new Callback<DeleteCommentResponse>() {
            @Override
            public void onResponse(Call<DeleteCommentResponse> call, Response<DeleteCommentResponse> response) {
                DeleteCommentResponse result = response.body();
                deleteCommentResult.postValue(result);
                System.out.println("deleteComment resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<DeleteCommentResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }
}
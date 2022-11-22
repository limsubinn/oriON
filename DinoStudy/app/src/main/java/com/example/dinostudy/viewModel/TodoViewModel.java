package com.example.dinostudy.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinostudy.model.todo.AddTodoData;
import com.example.dinostudy.model.todo.AddTodoResponse;
import com.example.dinostudy.model.todo.CreateTodoData;
import com.example.dinostudy.model.todo.CreateTodoResponse;
import com.example.dinostudy.model.todo.DeleteTodoData;
import com.example.dinostudy.model.todo.DeleteTodoResponse;
import com.example.dinostudy.model.todo.EditTodoData;
import com.example.dinostudy.model.todo.ReadTodoData;
import com.example.dinostudy.model.todo.ReadTodoResponse;
import com.example.dinostudy.model.todo.EditTodoResponse;
import com.example.dinostudy.model.todo.EditTodoCheckData;
import com.example.dinostudy.model.todo.EditTodoCheckResponse;
import com.example.dinostudy.repository.RetrofitClient;
import com.example.dinostudy.repository.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoViewModel extends AndroidViewModel {
    public MutableLiveData<CreateTodoResponse> createResult = new MutableLiveData<>();
    public MutableLiveData<ReadTodoResponse> readResult = new MutableLiveData<>();
    public MutableLiveData<AddTodoResponse> addResult = new MutableLiveData<>();
    public MutableLiveData<EditTodoResponse> editResult = new MutableLiveData<>();
    public MutableLiveData<DeleteTodoResponse> deleteResult = new MutableLiveData<>();
    public MutableLiveData<EditTodoCheckResponse> checkResult = new MutableLiveData<>();




    ServiceApi service;


    public TodoViewModel(@NonNull Application application) {
        super(application);
        service = RetrofitClient.getClient().create(ServiceApi.class);

    }

    public void createTodo(CreateTodoData data) {
        System.out.println("********* createTodoData *********");

        service.createTodo(data).enqueue(new Callback<CreateTodoResponse>() {
            @Override
            public void onResponse(Call<CreateTodoResponse> call, Response<CreateTodoResponse> response) {
                CreateTodoResponse result = response.body();
                createResult.postValue(result);
                System.out.println("create resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<CreateTodoResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void readTodo(ReadTodoData data) {
        System.out.println("********* readTodoData *********");

        service.readTodo(data).enqueue(new Callback<ReadTodoResponse>() {
            @Override
            public void onResponse(Call<ReadTodoResponse> call, Response<ReadTodoResponse> response) {
                ReadTodoResponse result = response.body();
                readResult.postValue(result);
                System.out.println("read resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<ReadTodoResponse> call, Throwable t) {
                System.out.println("readTodoResponse fail");
                t.printStackTrace();
            }
        });
    }

    public void addTodo(AddTodoData data) {
        System.out.println("********* addTodoData *********");

        service.addTodo(data).enqueue(new Callback<AddTodoResponse>() {
            @Override
            public void onResponse(Call<AddTodoResponse> call, Response<AddTodoResponse> response) {
                AddTodoResponse result = response.body();
                addResult.postValue(result);
                System.out.println("add resultCode: " + result.getCode());
            }

            @Override
            public void onFailure(Call<AddTodoResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void editTodo(EditTodoData data){
        System.out.println("********* EditWatchData *********");

        service.editTodo(data).enqueue(new Callback<EditTodoResponse>() {
            @Override
            public void onResponse(Call<EditTodoResponse> call, Response<EditTodoResponse> response) {
                EditTodoResponse result = response.body();
                editResult.postValue(result);
                System.out.println("edit resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<EditTodoResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void deleteTodo(DeleteTodoData data){
        System.out.println("********* DeleteTodoData *********");

        service.deleteTodo(data).enqueue(new Callback<DeleteTodoResponse>() {
            @Override
            public void onResponse(Call<DeleteTodoResponse> call, Response<DeleteTodoResponse> response) {
                DeleteTodoResponse result = response.body();
                deleteResult.postValue(result);
                System.out.println("delete resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<DeleteTodoResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

    public void updateCheckTodo(EditTodoCheckData data){
        System.out.println("********* UpdateCheckTodoData *********");

        service.editTodoCheck(data).enqueue(new Callback<EditTodoCheckResponse>() {
            @Override
            public void onResponse(Call<EditTodoCheckResponse> call, Response<EditTodoCheckResponse> response) {
                EditTodoCheckResponse result = response.body();
                checkResult.postValue(result);
                System.out.println("check resultCode: "+ result.getCode());
            }

            @Override
            public void onFailure(Call<EditTodoCheckResponse> call, Throwable t) {
                System.out.println("fail");
                t.printStackTrace();
            }
        });
    }

}
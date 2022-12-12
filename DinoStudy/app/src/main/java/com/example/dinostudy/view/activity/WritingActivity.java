package com.example.dinostudy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.databinding.ActivityWriteBinding;
import com.example.dinostudy.model.board.CreatePostData;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingActivity extends AppCompatActivity {
    private ActivityWriteBinding binding;
    private BoardViewModel boardViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);


        // username 받아오기
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date curdate = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(curdate);

        // 작성하기 버튼
        binding.btnBoardWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContents.getText().toString();

                // 데이터 삽입
                boardViewModel.createPost(new CreatePostData(username, curDate, title, content, 0));

//                Intent intent = new Intent(getBaseContext(),PostActivity.class);
//                intent.putExtra("title", title);
//                intent.putExtra("content", content);
//
//                startActivity(intent);
                finish();
            }
        });
    }

}

package com.example.dinostudy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.databinding.ActivityMywriteBinding;
import com.example.dinostudy.model.board.EditPostData;
import com.example.dinostudy.viewModel.BoardViewModel;

public class MyWriteActivity extends AppCompatActivity {

    private ActivityMywriteBinding binding;
    private BoardViewModel boardViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMywriteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);


        // 사용자 정보 받아옴
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String writer = intent.getStringExtra("writer");
        String coin = intent.getStringExtra("coin");
        String date = intent.getStringExtra("date");
        String before_title = intent.getStringExtra("title");
        String before_content = intent.getStringExtra("content");
        String gender = intent.getStringExtra("gender");
        String username = intent.getStringExtra("nickname");
        String useremail = intent.getStringExtra("email");
        //String comment = intent.getStringExtra("comment");


        binding.etTitle.setText(before_title);
        binding.etContents.setText(before_content);

        // 수정하기 버튼
        binding.btnBoardWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String after_title = binding.etTitle.getText().toString();
                String after_content = binding.etContents.getText().toString();

                // 데이터 수정
                boardViewModel.editPost(new EditPostData(Integer.valueOf(id), after_title, after_content));

                boardViewModel.editPostResult.observe(MyWriteActivity.this, res -> {
                    if (res.getCode() == 200) {

                        Toast.makeText(MyWriteActivity.this, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                        Intent mywrite_to_mypage = new Intent(MyWriteActivity.this, MypageActivity.class);
                        mywrite_to_mypage.putExtra("email", useremail);
                        mywrite_to_mypage.putExtra("gender", gender);
                        mywrite_to_mypage.putExtra("coin", coin);
                        mywrite_to_mypage.putExtra("nickname", writer);

                        startActivity(mywrite_to_mypage);

                        finish();
                    }
                });


            }
        });
    }
}


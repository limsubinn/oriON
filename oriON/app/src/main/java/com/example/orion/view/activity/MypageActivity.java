package com.example.orion.view.activity;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.orion.R;
import com.example.orion.databinding.ActivityMypageBinding;
import com.example.orion.model.user.LoginData;
import com.example.orion.model.user.ReadMyPostData;
import com.example.orion.view.adapter.BoardAdapter;
import com.example.orion.view.item.BoardItem;
import com.example.orion.viewModel.UserViewModel;

import java.util.ArrayList;

public class MypageActivity extends AppCompatActivity {

    private String username;
    private String userEmail;
    private String userCoin;
    private String gender;

    private UserViewModel userViewModel;
    private ArrayList<BoardItem> arrayList;
    private BoardAdapter boardAdapter;
    private LinearLayoutManager linearLayoutManager;

    private String writer;
    private String title;
    private String curdate;
    private String content;

    private int cnt;
    Context ct;




    ActivityMypageBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMypageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        arrayList = new ArrayList<>();
        ct = getApplicationContext();

        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvMypage.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        boardAdapter = new BoardAdapter(ct,arrayList);
        binding.rvMypage.setAdapter(boardAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar_mypage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기

        // 사용자 정보 받아오기
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
        username = intent.getStringExtra("nickname");
        userCoin = intent.getStringExtra("coin");
        gender = intent.getStringExtra("gender");

        // 사용자 정보 호출 후 띄우기
        userViewModel.login(new LoginData(userEmail));

        userViewModel.loginResult.observe(MypageActivity.this, res -> {
            if(res.getCode() == 200) {

                binding.user.setText(res.getUsername());
                binding.email.setText(res.getUserEmail());
                binding.birth.setText(res.getBirth());


                if(res.getGender().equals("F")){
                    binding.gender.setText("여");
                    gender ="여";
                }else{
                    binding.gender.setText("남");
                    gender ="남";

                }

            } else if (res.getCode() == 204) {
                Log.d(TAG, "마이페이지 오류 : 서버에서 값을 못 받아옴!");
            }
        });

        // 사용자 목록 띄우기
        userViewModel.readUserPost(new ReadMyPostData(username));

        userViewModel.userPostResult.observe(MypageActivity.this, res -> {
            if(res.getCode() == 200) {
                int n = res.getResult().size();
                for(int i=n-1; i>=0; i--){
                    int id = res.getResult().get(i).getId();
                    writer = res.getResult().get(i).getWriter();
                    title = res.getResult().get(i).getTitle();
                    curdate = res.getResult().get(i).getCurdate();
                    content = res.getResult().get(i).getContent();
                    cnt = res.getResult().get(i).getN();

                    BoardItem boardItem = new BoardItem(id, writer, title, curdate, content, cnt);
                    arrayList.add(boardItem);
                }
                boardAdapter.notifyDataSetChanged(); //새로고침


            } else if (res.getCode() == 204) {
                System.out.println("에러");
            }

        });

        // 리사이클러뷰 아이템 클릭 이벤트
        boardAdapter.setOnItemClickListener (new BoardAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {

                Intent mypage_to_mypost = new Intent(getApplicationContext(), MyPostActivity.class);
                mypage_to_mypost.putExtra("id", Integer.toString(arrayList.get(position).getId())); // username 보내기
                mypage_to_mypost.putExtra("writer", arrayList.get(position).getUsername());
                mypage_to_mypost.putExtra("title", arrayList.get(position).getTitle());
                mypage_to_mypost.putExtra("date", arrayList.get(position).getDate());
                mypage_to_mypost.putExtra("content", arrayList.get(position).getContent());
                mypage_to_mypost.putExtra("username", username);
                mypage_to_mypost.putExtra("gender", gender);
                //mypage_to_mypost.putExtra("comment", arrayList.get(position).getComment());
//                mypage_to_mypost.putExtra("cnt", arrayList.get(position).get);
                mypage_to_mypost.putExtra("coin", userCoin);
                mypage_to_mypost.putExtra("email",userEmail);

                startActivity(mypage_to_mypost);
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //뒤로가기 구현
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent mypage_to_main = new Intent(getApplicationContext(), MainActivity.class);
                mypage_to_main.putExtra("nickname",username); // username 보내기
                mypage_to_main.putExtra("coin", userCoin);
                mypage_to_main.putExtra("email",userEmail);
                startActivity(mypage_to_main);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}

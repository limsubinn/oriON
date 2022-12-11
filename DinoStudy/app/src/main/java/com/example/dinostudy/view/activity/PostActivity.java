package com.example.dinostudy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.ActivityMainBinding;
import com.example.dinostudy.databinding.ActivityPostBinding;
import com.example.dinostudy.view.adapter.CommentAdapter;
import com.example.dinostudy.view.item.CommentItem;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    private ActivityPostBinding binding;

    private ArrayList<CommentItem> arrayList;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private TextView tv_title,post_category, post_nickname, board_date, board_post_content;
    private EditText et_comment_write;
    private String user,date;
    //private ImageButton btn_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recyclerView = (RecyclerView) findViewById(R.id.rv_comment);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        tv_title = findViewById(R.id.tv_title);
        post_nickname = findViewById(R.id.nickname);
        board_date = findViewById(R.id.board_date);
        board_post_content = findViewById(R.id.board_post_content);
        //btn_back = findViewById(R.id.btn_back);

        arrayList = new ArrayList<>();

        commentAdapter = new CommentAdapter(this, arrayList);
        recyclerView.setAdapter(commentAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras(); // bundle을 통해 Extra들을 모두 가져온다
        String title = bundle.getString("title"); // 키 값을 통해서 extras에 있는 값들을 얻는다.
        String contents = bundle.getString("contents");
        String category = bundle.getString("category");

        tv_title.setText(title);
        post_category.setText(category);
        board_post_content.setText(contents);

        //댓글 작성하기
        et_comment_write = findViewById(R.id.et_comment_write);
        user = "hk";
        date = "2022.12.07";

        Button btn_comment_insert = (Button) findViewById(R.id.btn_comment_insert);
        btn_comment_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = et_comment_write.getText().toString();

                CommentItem dataComment = new CommentItem(user,date,comment);
                arrayList.add(dataComment);
                commentAdapter.notifyDataSetChanged();
            }
        });

        /*
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment_watch fragment_watch = new Fragment_watch();
                transaction.replace(R.id.frame,fragment_watch);
                transaction.commit();
            }
        });

         */

    }
}

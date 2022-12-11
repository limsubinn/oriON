package com.example.dinostudy.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.FragmentBoardBinding;
import com.example.dinostudy.view.activity.WritingActivity;
import com.example.dinostudy.view.adapter.BoardAdapter;
import com.example.dinostudy.view.item.BoardItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private ArrayList<BoardItem> arrayList;
    private BoardAdapter boardAdapter;
    private LinearLayoutManager linearLayoutManager;

    Context ct;

    public BoardFragment(){
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        ct = container.getContext();

        // username 받아오기
        String username = getArguments().getString("username");

//        // 현재 날짜 불러오기
//        long now = System.currentTimeMillis();
//        Date curdate = new Date(now);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//        String curDate = sdf.format(curdate);

        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvBoard.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();  //mDatas
        arrayList.add(new BoardItem("username", "title", "date", 2));

        boardAdapter = new BoardAdapter(ct, arrayList);
        binding.rvBoard.setAdapter(boardAdapter);

        // 글쓰기 버튼
        binding.btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WritingActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }


}

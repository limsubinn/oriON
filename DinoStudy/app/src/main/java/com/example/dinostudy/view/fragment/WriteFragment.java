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
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.databinding.FragmentWriteBinding;
import com.example.dinostudy.model.board.CreatePostData;
import com.example.dinostudy.view.activity.MainActivity;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteFragment extends Fragment {

    private FragmentWriteBinding binding;
    private BoardViewModel boardViewModel;
    private String username;

    Context ct;
    MainActivity mainActivity;


    public WriteFragment() {
    }

    // 메인 액티비티 위에 올린다.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    // 메인 액티비티에서 내려온다.
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater, container, false);
        ct = container.getContext();

        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date curdate = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(curdate);

        // username 받아오기
        getParentFragmentManager().setFragmentResultListener("board_to_write", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String username = bundle.getString("username");

                // 작성하기 버튼
                binding.btnBoardWrite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = binding.etTitle.getText().toString();
                        String content = binding.etContents.getText().toString();

                        // 데이터 삽입
                        boardViewModel.createPost(new CreatePostData(username, curDate, title, content, 0));

                        // BoardFragment로 돌아감.
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        getParentFragmentManager().setFragmentResult("goto_board", bundle);
                        mainActivity.fragmentChange(0);
                    }
                });
            }
        });


        return binding.getRoot();
    }
}
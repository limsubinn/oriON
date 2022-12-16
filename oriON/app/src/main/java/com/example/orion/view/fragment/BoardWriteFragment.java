package com.example.orion.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.orion.databinding.FragmentWriteBinding;
import com.example.orion.model.board.CreatePostData;
import com.example.orion.model.board.EditPostData;
import com.example.orion.view.activity.MainActivity;
import com.example.orion.viewModel.BoardViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardWriteFragment extends Fragment {

    private FragmentWriteBinding binding;
    private BoardViewModel boardViewModel;
    private String username;

    Context ct;
    MainActivity mainActivity;


    public BoardWriteFragment() {
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

        // board_to_write
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

        // post_to_write
        getParentFragmentManager().setFragmentResultListener("post_to_write", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String username = bundle.getString("username");
                String writer = bundle.getString("writer");
                String before_title = bundle.getString("title");
                String before_content = bundle.getString("content");
                String date = bundle.getString("date");
                int id = bundle.getInt("id");
                int cnt = bundle.getInt("comment");


                binding.etTitle.setText(before_title);
                binding.etContents.setText(before_content);
                binding.btnBoardWrite.setText("수정하기");

                // 수정하기 버튼
                binding.btnBoardWrite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String after_title = binding.etTitle.getText().toString();
                        String after_content = binding.etContents.getText().toString();

                        // 데이터 수정
                        boardViewModel.editPost(new EditPostData(id ,after_title, after_content));

                        boardViewModel.editPostResult.observe(getViewLifecycleOwner(), res -> {
                            if (res.getCode() == 200) {

                                Toast.makeText(getContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                                Bundle result = new Bundle();
                                result.putInt("id", id);
                                result.putString("writer", writer);
                                result.putString("title", after_title);
                                result.putString("date", date);
                                result.putString("content", after_content);
                                result.putString("username", username);
                                result.putInt("comment", cnt);

                                getParentFragmentManager().setFragmentResult("goto_post", result);
                                mainActivity.fragmentChange(2);
                            }
                        });


                    }
                });
            }
        });

        return binding.getRoot();
    }
}
package com.example.dinostudy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.databinding.FragmentPostBinding;
import com.example.dinostudy.view.activity.MainActivity;
import com.example.dinostudy.view.adapter.BoardAdapter;
import com.example.dinostudy.view.item.BoardItem;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.util.ArrayList;

public class PostFragment extends Fragment {

    private FragmentPostBinding binding;
    private BoardViewModel boardViewModel;
    private ArrayList<BoardItem> arrayList;
    private BoardAdapter boardAdapter;
    private LinearLayoutManager linearLayoutManager;

    Context ct;
    MainActivity mainActivity;


    public PostFragment() {

    }
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        ct = container.getContext();

        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);

        getParentFragmentManager().setFragmentResultListener("board_to_post", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String writer = bundle.getString("writer");
                String title = bundle.getString("title");
                String date = bundle.getString("date");
                String content = bundle.getString("content");
                // int comment = bundle.getInt("comment");
                String username = bundle.getString("username");

                binding.postNickname.setText(writer);
                binding.tvTitle.setText(title);
                binding.boardDate.setText(date);
                binding.boardPostContent.setText(content);

                // 목록보기 버튼
                binding.btnGotomain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

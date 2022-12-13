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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.FragmentBoardBinding;
import com.example.dinostudy.view.activity.MainActivity;
import com.example.dinostudy.view.adapter.BoardAdapter;
import com.example.dinostudy.view.item.BoardItem;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.util.ArrayList;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private BoardViewModel boardViewModel;
    private ArrayList<BoardItem> arrayList;
    private BoardAdapter boardAdapter;
    private String username;
    private LinearLayoutManager linearLayoutManager;

    Context ct;
    MainActivity mainActivity;


    public BoardFragment() {
    }

    // 메인 액티비티 위에 올린다.

    //onAttach : 프래그먼트와 액티비티가 연결될때 호출되는 메서드
    //onDetach : 프래그먼트와 액티비티의 연결이 끊길때 호출되는 메서드
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
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        ct = container.getContext();

        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);

        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvBoard.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();  //mDatas
//        arrayList.add(new BoardItem("username", "title", "date", 2));

        boardAdapter = new BoardAdapter(ct, arrayList);
        binding.rvBoard.setAdapter(boardAdapter);

        // username 받기
        getParentFragmentManager().setFragmentResultListener("goto_board", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String username = bundle.getString("username");

                // 글쓰기 버튼
                binding.btnWrite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        getParentFragmentManager().setFragmentResult("board_to_write", bundle);

                        mainActivity.fragmentChange(1);
                    }
                });

                // db 내용 불러오기
                boardViewModel.readPost();

                // 서버에서 정상적인 값 받음
                boardViewModel.readPostResult.observe(getViewLifecycleOwner(), res -> {
                    System.out.println("*****readPost 서버에서 값 잘 받음*******");

                    int n = res.getResult().size();

                    if (res.getCode() == 200) {
                        for(int i=n-1; i>=0; i--){

                            int id = res.getResult().get(i).getId();
                            String writer = res.getResult().get(i).getWriter();
                            String title = res.getResult().get(i).getTitle();
                            String curdate = res.getResult().get(i).getCurdate();
                            String content = res.getResult().get(i).getContent();
                            int cnt = res.getResult().get(i).getN();

                            BoardItem boardItem = new BoardItem(id, writer, title, curdate, content, cnt);
                            arrayList.add(boardItem);
                        }
                        boardAdapter.notifyDataSetChanged(); //새로고침

                    } else {
                        System.out.println("에러");
                    }
                });

                // 리사이클러뷰 아이템 클릭 이벤트
                boardAdapter.setOnItemClickListener (new BoardAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View v, int position) {
                        Bundle result = new Bundle();
                        result.putInt("id", arrayList.get(position).getId());
                        result.putString("writer", arrayList.get(position).getUsername());
                        result.putString("title", arrayList.get(position).getTitle());
                        result.putString("date", arrayList.get(position).getDate());
                        result.putString("content", arrayList.get(position).getContent());
                        result.putString("username", username);
                        result.putInt("comment", arrayList.get(position).getComment());

                        getParentFragmentManager().setFragmentResult("goto_post", result);
                        mainActivity.fragmentChange(2);
                    }

                });

            }
        });


        return binding.getRoot();

    }
}

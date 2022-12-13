package com.example.dinostudy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.databinding.FragmentPostBinding;
import com.example.dinostudy.model.board.CreateCommentData;
import com.example.dinostudy.model.board.ReadCommentData;
import com.example.dinostudy.view.activity.MainActivity;
import com.example.dinostudy.view.adapter.CommentAdapter;
import com.example.dinostudy.view.item.BoardItem;
import com.example.dinostudy.view.item.CommentItem;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PostFragment extends Fragment {

    private FragmentPostBinding binding;
    private BoardViewModel boardViewModel;
    private ArrayList<CommentItem> arrayList;
    private CommentAdapter commentAdapter;
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

        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvComment.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();  //mDatas
//        arrayList.add(new CommentItem("username", "date", "content", "id"));

        commentAdapter = new CommentAdapter(ct, arrayList);
        binding.rvComment.setAdapter(commentAdapter);

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date d = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(d);

        getParentFragmentManager().setFragmentResultListener("board_to_post", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                int id = bundle.getInt("id"); // post_id
                String writer = bundle.getString("writer"); // 작성자
                String title = bundle.getString("title");
                String date = bundle.getString("date");
                String content = bundle.getString("content");
                // int cnt = bundle.getInt("comment"); // 댓글수
                String username = bundle.getString("username"); // 현재 로그인한 유저

                binding.postNickname.setText(writer);
                binding.tvTitle.setText(title);
                binding.boardDate.setText(date);
                binding.boardPostContent.setText(content);

                // binding.tvCnt.setText(Integer.toString(cnt));

                // 댓글 작성 버튼
                binding.btnCommentInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment = binding.etCommentWrite.getText().toString();
                        boardViewModel.createComment(new CreateCommentData(username, curDate, id, comment));

                        // 키보드 내리기
                        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        // 댓글 & 댓글수 추가
                        CommentItem commentItem = new CommentItem(username, curDate, comment, id);
                        arrayList.add(commentItem);
                        commentAdapter.notifyDataSetChanged();
                        binding.tvCnt.setText(Integer.toString(arrayList.size()));

                        // EditText 비우기
                        binding.etCommentWrite.setText("");
                    }
                });

                // 댓글 불러오기
                boardViewModel.readComment(new ReadCommentData(id));
                boardViewModel.readCommentResult.observe(getViewLifecycleOwner(), res -> {
                    int n = res.getResult().size();

                    if (res.getCode() == 200) {
                        for(int i=0; i<n; i++){
                            int c_id = res.getResult().get(i).getId();
                            String c_writer = res.getResult().get(i).getWriter();
                            String c_date = res.getResult().get(i).getCurdate();
                            String c_content = res.getResult().get(i).getContent();

                            CommentItem commentItem = new CommentItem(c_writer, c_date, c_content, c_id);
                            arrayList.add(commentItem);
                        }
                        commentAdapter.notifyDataSetChanged(); //새로고침
                        binding.tvCnt.setText(Integer.toString(n));

                    } else {
                        System.out.println("에러");
                    }
                });

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

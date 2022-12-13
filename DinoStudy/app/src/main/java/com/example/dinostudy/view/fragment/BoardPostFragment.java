package com.example.dinostudy.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.databinding.FragmentPostBinding;
import com.example.dinostudy.model.board.CountCommentData;
import com.example.dinostudy.model.board.CreateCommentData;
import com.example.dinostudy.model.board.DeleteCommentData;
import com.example.dinostudy.model.board.DeletePostData;
import com.example.dinostudy.model.board.EditCommentData;
import com.example.dinostudy.model.board.ReadCommentData;
import com.example.dinostudy.view.activity.MainActivity;
import com.example.dinostudy.view.adapter.CommentAdapter;
import com.example.dinostudy.view.item.CommentItem;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardPostFragment extends Fragment {

    private FragmentPostBinding binding;
    private BoardViewModel boardViewModel;
    private ArrayList<CommentItem> arrayList;
    private CommentAdapter commentAdapter;
    private LinearLayoutManager linearLayoutManager;

    Context ct;
    MainActivity mainActivity;


    public BoardPostFragment() {

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

        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvComment.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();  //mDatas
//        arrayList.add(new CommentItem("username", "date", "content", "id"));


        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date d = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(d);

        getParentFragmentManager().setFragmentResultListener("goto_post", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                int id = bundle.getInt("id"); // post_id
                String writer = bundle.getString("writer"); // 작성자
                String title = bundle.getString("title");
                String date = bundle.getString("date");
                String content = bundle.getString("content");
                int cnt = bundle.getInt("comment"); // 댓글수
                String username = bundle.getString("username"); // 현재 로그인한 유저

                binding.postNickname.setText(writer);
                binding.tvTitle.setText(title);
                binding.boardDate.setText(date);
                binding.boardPostContent.setText(content);

                // binding.tvCnt.setText(Integer.toString(cnt));

                commentAdapter = new CommentAdapter(ct, arrayList, username);
                binding.rvComment.setAdapter(commentAdapter);

                // 작성자와 로그인 유저랑 같으면 편집, 삭제 버튼 띄우기
                if(writer.equals(username)) {
                    binding.btnEdit.setVisibility(View.VISIBLE);
                    binding.btnDelete.setVisibility(View.VISIBLE);

                    // 게시글 편집
                    binding.btnEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Bundle result = new Bundle();
                            result.putInt("id", id);
                            result.putString("content", content);
                            result.putString("title", title);
                            result.putString("date", date);
                            result.putString("writer", writer);
                            result.putString("username", username);
                            result.putInt("comment", cnt);

                            getParentFragmentManager().setFragmentResult("post_to_write", result);
                            mainActivity.fragmentChange(1);
                        }
                    });

                    // 게시글 삭제
                    binding.btnDelete.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                            builder.setMessage("정말 삭제하시겠습니까?")
                                    .setPositiveButton("예",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    boardViewModel.deletePost(new DeletePostData(id, username));
                                                    boardViewModel.deletePostResult.observe(getViewLifecycleOwner(), res -> {
                                                        if (res.getCode() == 200) {

                                                            Bundle bundle = new Bundle();
                                                            bundle.putString("username", username);

                                                            Toast.makeText(getContext(), "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                            getParentFragmentManager().setFragmentResult("goto_board", bundle);
                                                            mainActivity.fragmentChange(0);

                                                        }
                                                    });
                                                }
                                            }
                                    )
                                    .setNegativeButton("아니오",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(ct, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                    .show();
                        }
                    });
                } else {
                    binding.btnEdit.setVisibility(View.INVISIBLE);
                    binding.btnDelete.setVisibility(View.INVISIBLE);
                }


                // 댓글 작성 버튼
                binding.btnCommentInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment = binding.etCommentWrite.getText().toString();
                        boardViewModel.createComment(new CreateCommentData(username, curDate, id, comment));

                        // 키보드 내리기
                        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        // 댓글 & 댓글수 추가
                        CommentItem commentItem = new CommentItem(username, curDate, comment, id);
                        arrayList.add(commentItem);
                        commentAdapter.notifyDataSetChanged();
                        binding.tvCnt.setText(Integer.toString(arrayList.size()));

                        // EditText 비우기
                        binding.etCommentWrite.setText("");

                        // 댓글 수 갱신 (board)
                        boardViewModel.countComment(new CountCommentData(id, arrayList.size()));
                    }
                });

                // 댓글 불러오기
                boardViewModel.readComment(new ReadCommentData(id));
                boardViewModel.readCommentResult.observe(getViewLifecycleOwner(), res -> {
                    int n = res.getResult().size();

                    if (res.getCode() == 200) {
                        for (int i = 0; i < n; i++) {
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

                // 리사이클러뷰 아이템 클릭 이벤트
                commentAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {

                    // 댓글 편집
                    @Override
                    public void onEditClick(View v, int position) {
                        // 키보드 올리기
                        inputManager.showSoftInput(getActivity().getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);

                        // EditText에 텍스트 설정
                        binding.etCommentWrite.setText(arrayList.get(position).getComment_content());

                        // 작성 버튼 "수정"으로 변경
                        binding.btnCommentInsert.setText("수정");

                        // 댓글 내용 수정
                        binding.btnCommentInsert.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String afterContent = binding.etCommentWrite.getText().toString();
                                boardViewModel.editComment(new EditCommentData(id, afterContent));

                                arrayList.get(position).setComment_content(afterContent);
                                commentAdapter.notifyItemChanged(position);

                                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                                binding.btnCommentInsert.setText("작성");
                                binding.etCommentWrite.setText("");

                            }
                        });

                    }

                    // 댓글 삭제
                    @Override
                    public void onDeleteClick(View v, int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                        builder.setMessage("정말 삭제하시겠습니까?")
                                .setPositiveButton("예",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                int commentId = arrayList.get(position).getPost_id();

                                                // db에서 지우기
                                                boardViewModel.deleteComment(new DeleteCommentData(commentId));

                                                // 리사이클러뷰에서 지우기
                                                arrayList.remove(position);
                                                commentAdapter.notifyItemRemoved(position);
                                                binding.tvCnt.setText(Integer.toString(arrayList.size()));

                                                // 댓글 수 갱신 (board)
                                                boardViewModel.countComment(new CountCommentData(id, arrayList.size()));
                                            }
                                        }
                                )
                                .setNegativeButton("아니오",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(ct, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                .show();
                    }

                });
            }
        });

        return binding.getRoot();
    }
}

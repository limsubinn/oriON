package com.example.dinostudy.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.databinding.ActivityMypostBinding;
import com.example.dinostudy.model.board.CountCommentData;
import com.example.dinostudy.model.board.CreateCommentData;
import com.example.dinostudy.model.board.DeleteCommentData;
import com.example.dinostudy.model.board.DeletePostData;
import com.example.dinostudy.model.board.EditCommentData;
import com.example.dinostudy.model.board.ReadCommentData;
import com.example.dinostudy.view.adapter.CommentAdapter;
import com.example.dinostudy.view.item.CommentItem;
import com.example.dinostudy.viewModel.BoardViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyPostActivity extends AppCompatActivity {

    private ActivityMypostBinding binding;
    private BoardViewModel boardViewModel;
    private ArrayList<CommentItem> arrayList;
    private CommentAdapter commentAdapter;
    private LinearLayoutManager linearLayoutManager;



    Context ct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMypostBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);

        ct = getApplicationContext();
        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvComment.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();  //mDatas


        InputMethodManager inputManager = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        // 사용자 정보 받아옴
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String writer = intent.getStringExtra("writer");
        String coin = intent.getStringExtra("coin");
        String date = intent.getStringExtra("date");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String gender = intent.getStringExtra("gender");
        String username = intent.getStringExtra("username");
        String useremail = intent.getStringExtra("email");
        //String comment = intent.getStringExtra("comment");


        binding.tvTitle.setText(title);
        binding.postNickname.setText(username);
        binding.boardDate.setText(date);
        binding.boardPostContent.setText(content);

        commentAdapter = new CommentAdapter(ct, arrayList, username);
        binding.rvComment.setAdapter(commentAdapter);

        // 현재 날짜 불러옴
        long now = System.currentTimeMillis();
        Date date1 = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(date1);


            // 게시글 편집
            binding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mypost_to_mywrite = new Intent(getApplicationContext(), MyWriteActivity.class);
                    mypost_to_mywrite.putExtra("id",id ); // username 보내기
                    mypost_to_mywrite.putExtra("writer",writer );
                    mypost_to_mywrite.putExtra("title", title);
                    mypost_to_mywrite.putExtra("date", date);
                    mypost_to_mywrite.putExtra("content",content );
                    mypost_to_mywrite.putExtra("gender", gender);
                    //mypage_to_mypost.putExtra("comment", arrayList.get(position).getComment());
//                mypage_to_mypost.putExtra("cnt", arrayList.get(position).get);
                    mypost_to_mywrite.putExtra("coin", coin);
                    mypost_to_mywrite.putExtra("email",useremail);

                    startActivity(mypost_to_mywrite);
                }
            });

            // 게시글 삭제
            binding.btnDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MyPostActivity.this);
                    builder.setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("예",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            boardViewModel.deletePost(new DeletePostData(Integer.valueOf(id) , username));
                                            boardViewModel.deletePostResult.observe(MyPostActivity.this, res -> {
                                                if (res.getCode() == 200) {

                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("username", username);

                                                    Toast.makeText(MyPostActivity.this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();

                                                    Intent mypost_to_mypage = new Intent(MyPostActivity.this, MypageActivity.class);
                                                    mypost_to_mypage.putExtra("email",useremail);
                                                    mypost_to_mypage.putExtra("gender",gender);
                                                    mypost_to_mypage.putExtra("coin",coin);
                                                    mypost_to_mypage.putExtra("nickname",username);

                                                    startActivity(mypost_to_mypage);

                                                }
                                            });
                                        }
                                    }
                            )
                            .setNegativeButton("아니오",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(MyPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .show();
                }
            });



        // 댓글 작성 버튼
        binding.btnCommentInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = binding.etCommentWrite.getText().toString();
                boardViewModel.createComment(new CreateCommentData(username, curDate, Integer.valueOf(id), comment));

                // 키보드 내리기
                inputManager.hideSoftInputFromWindow(MyPostActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // 댓글 & 댓글수 추가
                CommentItem commentItem = new CommentItem(username, curDate, comment, Integer.valueOf(id));
                arrayList.add(commentItem);
                commentAdapter.notifyDataSetChanged();
                binding.tvCnt.setText(Integer.toString(arrayList.size()));

                // EditText 비우기
                binding.etCommentWrite.setText("");

                // 댓글 수 갱신 (board)
                boardViewModel.countComment(new CountCommentData(Integer.valueOf(id), arrayList.size()));
            }
        });

        // 댓글 불러오기
        boardViewModel.readComment(new ReadCommentData(Integer.valueOf(id)));
        boardViewModel.readCommentResult.observe(this, res -> {
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

        // 마이페이지 버튼
        binding.btnGotomypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //mypage로 돌아감
                Intent mypost_to_mypage = new Intent(MyPostActivity.this, MypageActivity.class);
                mypost_to_mypage.putExtra("email",useremail);
                mypost_to_mypage.putExtra("gender",gender);
                mypost_to_mypage.putExtra("coin",coin);
                mypost_to_mypage.putExtra("nickname",username);

                startActivity(mypost_to_mypage);
            }
        });

        // 리사이클러뷰 아이템 클릭 이벤트
        commentAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {

            // 댓글 편집
            @Override
            public void onEditClick(View v, int position) {
                // 키보드 올리기
                inputManager.showSoftInput(MyPostActivity.this.getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);

                // EditText에 텍스트 설정
                binding.etCommentWrite.setText(arrayList.get(position).getComment_content());

                // 작성 버튼 "수정"으로 변경
                binding.btnCommentInsert.setText("수정");

                // 댓글 내용 수정
                binding.btnCommentInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String afterContent = binding.etCommentWrite.getText().toString();
                        boardViewModel.editComment(new EditCommentData(Integer.valueOf(id), afterContent));

                        arrayList.get(position).setComment_content(afterContent);
                        commentAdapter.notifyItemChanged(position);

                        inputManager.hideSoftInputFromWindow(MyPostActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        binding.btnCommentInsert.setText("작성");
                        binding.etCommentWrite.setText("");

                    }
                });

            }

            // 댓글 삭제
            @Override
            public void onDeleteClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPostActivity.this);
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
                                        boardViewModel.countComment(new CountCommentData(Integer.valueOf(id), arrayList.size()));
                                    }
                                }
                        )
                        .setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(MyPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .show();
            }

        });

    }
}

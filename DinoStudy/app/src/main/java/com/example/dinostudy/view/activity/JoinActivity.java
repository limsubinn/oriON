package com.example.dinostudy.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.databinding.ActivityJoinBinding;
import com.example.dinostudy.model.user.CheckUserData;
import com.example.dinostudy.model.user.JoinData;
import com.example.dinostudy.viewModel.JoinViewModel;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class JoinActivity extends AppCompatActivity {
    private ActivityJoinBinding binding;
    private JoinViewModel joinViewModel;
    private int flagCheck = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        joinViewModel = new ViewModelProvider(this).get(JoinViewModel.class);
        // AtomicInteger flagCheck = new AtomicInteger(); // 0: 에러, 1: 중복 존재, 2: 사용 가능

        // email 받아오기
        Intent getIntent = getIntent();
        String email = getIntent.getStringExtra("email");


        // 생년월일 설정
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.btnBirth.setText(year+"년 " + (month+1) + "월 " + dayOfMonth + "일");
            }
        }, mYear, mMonth, mDay);

        binding.btnBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.btnBirth.isClickable()) {
                    datePickerDialog.show();
                }
            }
        });

        joinViewModel.checkResult.observe(JoinActivity.this, res -> {
            if (res.getCode() == 200) { // 닉네임 사용 가능
                flagCheck = 2;
            } else if (res.getCode() == 204) { // 닉네임 중복
                flagCheck = 1;
            } else { // 에러

            }
        });

        // 중복 체크
        binding.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 계정 등록
        binding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = binding.etNickname.getText().toString();
                String birth = binding.btnBirth.getText().toString();
                String gender = binding.rbMale.isChecked()? "M":"F";

                binding.etNickname.setError(null);



                if (nickname.isEmpty()) { // 닉네임 비어있을 때
                    Toast.makeText(JoinActivity.this, "닉네임을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if (birth.equals("0000년 00월 00일")) { // 생년월일 비어있을 때
                    Toast.makeText(JoinActivity.this, "생년월일을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if (binding.rgGender.getCheckedRadioButtonId() == -1) { // 라디오그룹 체크값 없을 때
                    Toast.makeText(JoinActivity.this, "성별을 선택해주세요!", Toast.LENGTH_SHORT).show();
                } else { // join 시도
                    joinViewModel.checkUser(new CheckUserData(nickname)); // 닉네임 중복 체크

                    if (flagCheck == 1) {
                        binding.etNickname.setError("이미 존재하는 닉네임입니다.");
                    } else if (flagCheck == 2) {
                        joinViewModel.join(new JoinData(nickname, email, 20, gender, birth));

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("nickname", nickname); // username 보내기
                        startActivity(intent);
                        finish();
                    }


//                    joinViewModel.checkResult.observe(JoinActivity.this, res -> {
//                        if (res.getCode() == 200) {
//                            joinViewModel.join(new JoinData(nickname, email, 20, gender, birth));
//                            binding.etNickname.setError(null);
//
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            intent.putExtra("nickname", nickname); // username 보내기
//                            startActivity(intent);
//                            finish();
//                        } else if (res.getCode() == 204) {
//                            binding.etNickname.setError("이미 존재하는 닉네임입니다.");
//                        } else { // 에러
//
//                        }
//                    });
                }

//                joinViewModel.joinResult.observe(JoinActivity.this, res -> {
//                    if (res.getCode() == 200) {
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        intent.putExtra("nickname", res.getMessage()); // username 보내기
//                        startActivity(intent);
//                        finish();
//                    } else { // 에러
//
//                    }
//                });
            }
        });
    }
}

// nickname, email, coin, gender, birth

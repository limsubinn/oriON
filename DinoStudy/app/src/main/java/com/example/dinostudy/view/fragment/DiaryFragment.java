package com.example.dinostudy.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.databinding.FragmentDiaryBinding;
import com.example.dinostudy.databinding.FragmentDiaryPlusContentBinding;
import com.example.dinostudy.model.diary.AddDiaryData;
import com.example.dinostudy.model.diary.CreateDiaryData;
import com.example.dinostudy.model.diary.ReadDiaryData;
import com.example.dinostudy.viewModel.DiaryViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryFragment extends Fragment {
    private FragmentDiaryBinding binding;
    private FragmentDiaryPlusContentBinding binding_plus_diary;

    private DiaryViewModel diaryViewModel;

    public DiaryFragment(){

    }

    Context ct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDiaryBinding.inflate(inflater, container, false);

        ct = container.getContext();

        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);

        // username 받아오기
        String username = getArguments().getString("username");

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(date);

        // 날짜 설정
        binding.dayDiary.setText(curDate);

        // 데이터 읽기
        diaryViewModel.readDiary(new ReadDiaryData(username, curDate));
        diaryViewModel.readResult.observe(getViewLifecycleOwner(), res -> {
            if (res.getCode() == 200) { // 데이터 존재
                binding.tvCompliment.setText(res.getGood());
                binding.tvReflection.setText(res.getBad());

                binding.tvCompliment.setMovementMethod(new ScrollingMovementMethod());
                binding.tvReflection.setMovementMethod(new ScrollingMovementMethod());
            } else if (res.getCode() == 204) { // 데이터 없음
                // diary에 초기 데이터 삽입
                diaryViewModel.createDiary(new CreateDiaryData(username, curDate, "", ""));
            } else { // 에러

            }
        });

        binding.btnDiaryPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                binding_plus_diary = FragmentDiaryPlusContentBinding.inflate(inflater, container, false);
                builder.setView(binding_plus_diary.getRoot());

                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                binding_plus_diary.btnDiaryInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //사용자 입력한 내용
                        String compliment = binding_plus_diary.etCompliment.getText().toString();
                        String reflection = binding_plus_diary.etReflection.getText().toString();

                        diaryViewModel.addDiary(new AddDiaryData(username, curDate, compliment, reflection));
                        diaryViewModel.addResult.observe(getViewLifecycleOwner(), res -> {
                            if (res.getCode() == 200) {
                                binding.tvCompliment.setText(compliment);
                                binding.tvReflection.setText(reflection);

                                binding.tvCompliment.setMovementMethod(new ScrollingMovementMethod());
                                binding.tvReflection.setMovementMethod(new ScrollingMovementMethod());
                            } else { // 에러

                            }
                        });

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return binding.getRoot();
    }
}

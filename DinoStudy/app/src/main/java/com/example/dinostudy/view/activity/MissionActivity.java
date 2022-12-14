package com.example.dinostudy.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.ActivityMissionBinding;
import com.example.dinostudy.model.mission.CreateMissionData;
import com.example.dinostudy.model.mission.EditMissionData;
import com.example.dinostudy.model.mission.ReadMissionData;
import com.example.dinostudy.model.watch.CreateWatchData;
import com.example.dinostudy.model.watch.ReadWatchData;
import com.example.dinostudy.viewModel.MissionViewModel;
import com.example.dinostudy.viewModel.WatchViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MissionActivity extends AppCompatActivity {

    private ActivityMissionBinding binding;
    private WatchViewModel watchViewModel;
    private MissionViewModel missionViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMissionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toolbar toolbar = findViewById(R.id.toolbar_mission);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기

        watchViewModel = new ViewModelProvider(this).get(WatchViewModel.class);
        missionViewModel = new ViewModelProvider(this).get(MissionViewModel.class);

        // 사용자 정보 받아오기
        Intent main_to_mission = getIntent();
        String username = main_to_mission.getStringExtra("nickname");
        String email = main_to_mission.getStringExtra("email");
        String coin = main_to_mission.getStringExtra("coin");

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(date);

        // 뒷배경 흐리게
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(70);

        // 미션 초기 데이터 삽입
        missionViewModel.createMission(new CreateMissionData(username, curDate, false, false, false));

        // 미션 데이터 읽기
        missionViewModel.readMission1(new ReadMissionData(username, curDate));
        missionViewModel.readResult1.observe(MissionActivity.this, res -> {
            if (res.getCode() == 200) { // 데이터 존재
                if (res.getMission1() == 1) {
                    binding.btnMission1.setText("완료");
                } else {
                    binding.btnMission1.setText("보상받기");
                }

                if (res.getMission2() == 1) {
                    binding.btnMission2.setText("완료");
                } else {
                    binding.btnMission2.setText("보상받기");
                }

                if (res.getMission3() == 1) {
                    binding.btnMission3.setText("완료");
                } else {
                    binding.btnMission3.setText("보상받기");
                }
            }
        });


        // 타이머 데이터 읽기
        watchViewModel.readWatch(new ReadWatchData(username, curDate));
        watchViewModel.readResult.observe(MissionActivity.this, res -> {
            if (res.getCode() == 200) { // 데이터 존재
                String time1 = "", time2 = "", time3 = "", time4 = "", time5 = "",
                        time6 = "", time7 = "", time8 = "", time9 = "", time10 = "";
                int hour1 = 0, min1 = 0, sec1 = 0;
                int hour2 = 0, min2 = 0, sec2 = 0;
                int hour3 = 0, min3 = 0, sec3 = 0;
                int hour4 = 0, min4 = 0, sec4 = 0;
                int hour5 = 0, min5 = 0, sec5 = 0;
                int hour6 = 0, min6 = 0, sec6 = 0;
                int hour7 = 0, min7 = 0, sec7 = 0;
                int hour8 = 0, min8 = 0, sec8 = 0;
                int hour9 = 0, min9 = 0, sec9 = 0;
                int hour10 = 0, min10 = 0, sec10 = 0;

                if (!res.getSubject1().equals(".")) {
                    time1 = res.getTime1();
                    hour1 = Integer.parseInt(time1.substring(0, 2));
                    min1 = Integer.parseInt(time1.substring(3, 5));
                    sec1 = Integer.parseInt(time1.substring(6, 8));
                }

                if (!res.getSubject2().equals(".")) {
                    time2 = res.getTime2();
                    hour2 = Integer.parseInt(time2.substring(0, 2));
                    min2 = Integer.parseInt(time2.substring(3, 5));
                    sec2 = Integer.parseInt(time2.substring(6, 8));
                }

                if (!res.getSubject3().equals(".")) {
                    time3 = res.getTime3();
                    hour3 = Integer.parseInt(time3.substring(0, 2));
                    min3 = Integer.parseInt(time3.substring(3, 5));
                    sec3 = Integer.parseInt(time3.substring(6, 8));
                }

                if (!res.getSubject4().equals(".")) {
                    time4 = res.getTime4();
                    hour4 = Integer.parseInt(time4.substring(0, 2));
                    min4 = Integer.parseInt(time4.substring(3, 5));
                    sec4 = Integer.parseInt(time4.substring(6, 8));
                }

                if (!res.getSubject5().equals(".")) {
                    time5 = res.getTime5();
                    hour5 = Integer.parseInt(time5.substring(0, 2));
                    min5 = Integer.parseInt(time5.substring(3, 5));
                    sec5 = Integer.parseInt(time5.substring(6, 8));
                }

                if (!res.getSubject6().equals(".")) {
                    time6 = res.getTime6();
                    hour6 = Integer.parseInt(time6.substring(0, 2));
                    min6 = Integer.parseInt(time6.substring(3, 5));
                    sec6 = Integer.parseInt(time6.substring(6, 8));
                }

                if (!res.getSubject7().equals(".")) {
                    time7 = res.getTime7();
                    hour7 = Integer.parseInt(time7.substring(0, 2));
                    min7 = Integer.parseInt(time7.substring(3, 5));
                    sec7 = Integer.parseInt(time7.substring(6, 8));
                }

                if (!res.getSubject8().equals(".")) {
                    time8 = res.getTime8();
                    hour8 = Integer.parseInt(time8.substring(0, 2));
                    min8 = Integer.parseInt(time8.substring(3, 5));
                    sec8 = Integer.parseInt(time8.substring(6, 8));
                }

                if (!res.getSubject9().equals(".")) {
                    time9 = res.getTime9();
                    hour9 = Integer.parseInt(time9.substring(0, 2));
                    min9 = Integer.parseInt(time9.substring(3, 5));
                    sec9 = Integer.parseInt(time9.substring(6, 8));
                }

                if (!res.getSubject10().equals(".")) {
                    time10 = res.getTime10();
                    hour10 = Integer.parseInt(time10.substring(0, 2));
                    min10 = Integer.parseInt(time10.substring(3, 5));
                    sec10 = Integer.parseInt(time10.substring(6, 8));
                }

                int hour = hour1 + hour2 + hour3 + hour4 + hour5 + hour6 + hour7 + hour8 + hour9 + hour10;
                int min = min1 + min2 + min3 + min4 + min5 + min6 + min7 + min8 + min9 + min10;
                int sec = sec1 + sec2 + sec3 + sec4 + sec5 + sec6 + sec7 + sec8 + sec9 + sec10;

                min += sec / 60;
                min += hour * 60;

                Log.d("Mission", "공부시간 " + min);
                binding.progress1.setProgress(min);
                binding.progress3.setProgress(min);

                if (min >= 120) {
                    Log.d("Mission", "미션1 달성");
                    binding.btnMission1.setVisibility(View.VISIBLE);
                    binding.layout1.setBackgroundColor(paint.getColor());

                    binding.btnMission1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (binding.btnMission1.getText().toString().equals("보상받기")) {
                                missionViewModel.readMission1(new ReadMissionData(username, curDate));
                                missionViewModel.readResult1.observe(MissionActivity.this, res -> {
                                    if (res.getCode() == 200) { // 데이터 존재
                                        if (res.getMission1() == 0) {
                                            Toast.makeText(getApplicationContext(), "20 코인을 획득하셨습니다!", Toast.LENGTH_SHORT).show();
                                            missionViewModel.editMission(new EditMissionData(username, curDate, "mission1", true));
                                            binding.btnMission1.setText("완료");
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "이미 보상을 획득하셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                if (min >= 360) {
                    Log.d("Mission", "미션3 달성");
                    binding.btnMission3.setVisibility(View.VISIBLE);
                    binding.layout3.setBackgroundColor(paint.getColor());

                    binding.btnMission3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (binding.btnMission3.getText().toString().equals("보상받기")) {
                                missionViewModel.readMission3(new ReadMissionData(username, curDate));
                                missionViewModel.readResult3.observe(MissionActivity.this, res -> {
                                    if (res.getCode() == 200) { // 데이터 존재
                                        if (res.getMission3() == 0) {
                                            Toast.makeText(getApplicationContext(), "20 코인을 획득하셨습니다!", Toast.LENGTH_SHORT).show();
                                            missionViewModel.editMission(new EditMissionData(username, curDate, "mission3", true));
                                            binding.btnMission3.setText("완료");
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "이미 보상을 획득하셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //뒤로가기 구현
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

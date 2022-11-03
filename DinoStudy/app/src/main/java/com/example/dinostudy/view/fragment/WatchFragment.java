package com.example.dinostudy.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.databinding.FragmentWatchBinding;
import com.example.dinostudy.databinding.FragmentWatchPlusSubjectBinding;
import com.example.dinostudy.model.AddWatchData;
import com.example.dinostudy.model.CheckEmailData;
import com.example.dinostudy.model.CreateWatchData;
import com.example.dinostudy.model.ReadWatchData;
import com.example.dinostudy.view.adapter.WatchAdapter;
import com.example.dinostudy.view.item.SubjectItem;
import com.example.dinostudy.viewModel.WatchViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WatchFragment extends Fragment {

    private FragmentWatchBinding binding;
    private FragmentWatchPlusSubjectBinding binding_plus_subject;

    private WatchViewModel watchViewModel;

    private ArrayList<SubjectItem> arrayList;
    private WatchAdapter stopwatchAdapter;
//    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    //private Chronometer chronometer;
    private Thread timeThread = null;
    private Boolean isRunning = false;

    private long mSecTime = 0L;     //시작 누르고 흐른 시간
    private long startTime = 0L;    //시작 누른 시간
    private long timeBuff = 0L;     //일시정지 눌렀을 때 측정된 총 시간
    private long updateTime = 0L;   //총 시간 = 일시정지 눌렀을 때 총 시간 + 시작 누르고 난 이후 부터 시간

    Handler handler;

    int sec, min, hour;

    public WatchFragment(){

    }

    Context ct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWatchBinding.inflate(inflater, container, false);

        ct = container.getContext();

        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvStopwatch.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        stopwatchAdapter = new WatchAdapter(arrayList);
        binding.rvStopwatch.setAdapter(stopwatchAdapter);

        handler = new Handler();

        watchViewModel = new ViewModelProvider(this).get(WatchViewModel.class);

        // username 받아오기
        String username = getArguments().getString("username");

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(date);

        // 데이터 읽기
        watchViewModel.readWatch(new ReadWatchData(username, curDate));
        watchViewModel.readResult.observe(getViewLifecycleOwner(), res -> {
            if (res.getCode() == 200) { // 데이터 존재
                //ArrayList에 추가
                if (!res.getSubject1().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject1(), res.getTime1()));
                }

                if (!res.getSubject2().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject2(), res.getTime2()));
                }

                if (!res.getSubject3().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject3(), res.getTime3()));
                }

                if (!res.getSubject4().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject4(), res.getTime4()));
                }

                if (!res.getSubject5().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject5(), res.getTime5()));
                }

                if (!res.getSubject6().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject6(), res.getTime6()));
                }

                if (!res.getSubject7().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject7(), res.getTime7()));
                }

                if (!res.getSubject8().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject8(), res.getTime8()));
                }

                if (!res.getSubject9().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject9(), res.getTime9()));
                }

                if (!res.getSubject10().equals(".")) {
                    arrayList.add(new SubjectItem(res.getSubject10(), res.getTime10()));
                }

                stopwatchAdapter.notifyDataSetChanged(); //새로고침
            } else if (res.getCode() == 204) { // 데이터 없음
                // timer에 초기 데이터 삽입
                watchViewModel.createWatch(new CreateWatchData(username, curDate, "과목1", "00:00:00"));
            } else { // 에러
            }
        });

        watchViewModel.createResult.observe(getViewLifecycleOwner(), res -> {
            if (res.getCode() == 200) {
                arrayList.add(new SubjectItem("과목1", "00:00:00")); //마지막 줄에 추가. 첫번째 줄은 (0, dataSubject)
                stopwatchAdapter.notifyItemInserted(stopwatchAdapter.getItemCount() + 1);

            }
        });


        binding.btnPlusSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Data_Subject dataSubject = new Data_Subject("토익","00:00:00");
                arrayList.add(dataSubject);
                stopwatchAdapter.notifyDataSetChanged();
                */

                if (stopwatchAdapter.getItemCount() >= 10) {
                    Toast.makeText(ct, "과목 추가는 10개까지만 가능합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                    binding_plus_subject = FragmentWatchPlusSubjectBinding.inflate(inflater, container, false);
                    builder.setView(binding_plus_subject.getRoot());

                    //btn_subject_name.setText("삽입");
                    final AlertDialog dialog = builder.create();

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    binding_plus_subject.btnSubjectInsert.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("FragmentLiveDataObserve")
                        @Override
                        public void onClick(View v) {
                            //사용자 입력 내용
                            String strSubject = binding_plus_subject.etSubject.getText().toString();
                            String subjectTime = "00:00:00";

                            watchViewModel.addWatch(new AddWatchData(username, curDate, strSubject, subjectTime, arrayList.size() + 1));
//                        watchViewModel.addResult.observe(WatchFragment.this, res -> {
//                            System.out.println("********실행********");
//                            if(res.getCode() == 200) {
                            //ArrayList에 추가
                            arrayList.add(new SubjectItem(strSubject, subjectTime));
                            stopwatchAdapter.notifyItemInserted(stopwatchAdapter.getItemCount() + 1);
                            System.out.println(strSubject + " add");
//                            } else { // 에러 코드
//
//                            }
//                        });

                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });



        //시작
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                //SystemClock.iptimeMillis()는 디바이스 부팅 후 부터 쉰 시간 '제외'한 밀리초 반환

                handler.postDelayed(runnable, 0);

                binding.btnReset.setEnabled(false);
                //시작하면 리셋버튼 비활성화
            }
        });

        //일시정지
        binding.btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeBuff += mSecTime;
                //일시정지 눌렀을 때의 총 시간

                handler.removeCallbacks(runnable);
                //Runnable 객체 제거

                binding.btnReset.setEnabled(true);
                //일시정지 누르면 초기화 버튼 활성화
            }
        });

        //초기화
        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSecTime = 0L;
                startTime = 0L;
                timeBuff = 0L;
                updateTime = 0L;
                sec = 00;
                min = 00;
                hour = 00;
                //측정 시간 모두 리셋

                binding.studyTime.setText("00:00:00");
            }
        });

        return binding.getRoot();
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mSecTime = SystemClock.uptimeMillis() - startTime;
            //디바이스 부팅 후부터 현재까지 시간 - 시작 버튼 누른 시간

            updateTime = timeBuff + mSecTime;
            //일시정지 눌렀을 때의 총 시간 + 시작 버튼 누르고 난 이후 부터의 시간(이어서 재기)

            sec = (int) (updateTime / 1000);
            min = sec / 60;
            sec = sec % 60;
            hour = min / 60;

            binding.studyTime.setText("" + String.format("%02d", hour) + ":" + String.format("%02d", min) + ":"
                    + String.format("%02d", sec));

            handler.postDelayed(this, 0);
        }
    };

}

package com.example.dinostudy.view.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.dinostudy.model.CreateWatchData;
import com.example.dinostudy.model.DeleteTimeData;
import com.example.dinostudy.model.DeleteWatchData;
import com.example.dinostudy.model.EditSubjectData;
import com.example.dinostudy.model.EditTimeData;
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
    int curPosition = 0;

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

        stopwatchAdapter = new WatchAdapter(ct, arrayList);
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

        // 날짜 설정
        binding.dayStopwatch.setText(curDate);

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

                binding.subject.setText(arrayList.get(0).getSubject());
                binding.studyTime.setText(arrayList.get(0).getTime());

                String curTime = arrayList.get(0).getTime();
                String[] strAry = curTime.split(":");

                int strHour = Integer.parseInt(strAry[0]);
                int strMin = Integer.parseInt(strAry[1]);
                int strSec = Integer.parseInt(strAry[2]);

                Long cur = new Long((strHour * 60 * 60 * 1000) + (strMin * 60 * 1000) + (strSec * 1000));
                timeBuff = cur;

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

                binding.subject.setText(arrayList.get(0).getSubject());
                binding.studyTime.setText(arrayList.get(0).getTime());
            }
        });

        // 과목 설정
//        binding.subject.setText(arrayList.get(0).getSubject());
//        binding.studyTime.setText(arrayList.get(0).getTime());

        // 리사이클러뷰 아이템 클릭 이벤트
        stopwatchAdapter.setOnItemClickListener (new WatchAdapter.OnItemClickListener() {

            // 삭제
            @Override
            public void onDeleteClick(View v, int position) {

                if (arrayList.size() == 1) {
                    Toast.makeText(ct, "과목은 1개 이상 존재해야 합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                    builder.setMessage("정말 삭제하시겠습니까?")
                            .setPositiveButton("예",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String tempSub[] = new String[10];
                                            String tempTime[] = new String[10];

                                            for (int i = 0; i < 10; i++) {
                                                if (i < position) {
                                                    tempSub[i] = arrayList.get(i).getSubject();
                                                    tempTime[i] = arrayList.get(i).getTime();
                                                } else if (i <= arrayList.size() - 2) {
                                                    tempSub[i] = arrayList.get(i + 1).getSubject();
                                                    tempTime[i] = arrayList.get(i + 1).getTime();
                                                } else {
                                                    tempSub[i] = ".";
                                                    tempTime[i] = "";
                                                }
                                            }

                                            watchViewModel.deleteWatch(new DeleteWatchData
                                                    (username, curDate, tempSub[0], tempTime[0], tempSub[1], tempTime[1],
                                                            tempSub[2], tempTime[2], tempSub[3], tempTime[3], tempSub[4], tempTime[4],
                                                            tempSub[5], tempTime[5], tempSub[6], tempTime[6], tempSub[7], tempTime[7],
                                                            tempSub[8], tempTime[8], tempSub[9], tempTime[9]));
                                            watchViewModel.deleteResult.observe(getViewLifecycleOwner(), res -> {
                                                if (res.getCode() == 200) {
                                                    arrayList.remove(position);
                                                    stopwatchAdapter.notifyItemRemoved(position);
                                                    Toast.makeText(ct, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                                    System.out.println("remove " + position);
                                                }
                                            });
                                        }
                                    })
                            .setNegativeButton("아니오",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(ct, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .show();
                }

            }

            // 아이템 클릭
            @Override
            public void onItemClick(View v, int position) {
                binding.subject.setText(arrayList.get(position).getSubject());
                binding.studyTime.setText(arrayList.get(position).getTime());
                curPosition = position;

                String curTime = arrayList.get(position).getTime();
                String[] strAry = curTime.split(":");

                int strHour = Integer.parseInt(strAry[0]);
                int strMin = Integer.parseInt(strAry[1]);
                int strSec = Integer.parseInt(strAry[2]);

                Long cur = new Long((strHour * 60 * 60 * 1000) + (strMin * 60 * 1000) + (strSec * 1000));
                timeBuff = cur;
            }

            // 편집
            @Override
            public void onEditClick(View v, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                binding_plus_subject = FragmentWatchPlusSubjectBinding.inflate(inflater, container, false);
                builder.setView(binding_plus_subject.getRoot());

                String beforeSub = arrayList.get(position).getSubject();
                String beforeTime = arrayList.get(position).getTime();

                binding_plus_subject.btnSubjectInsert.setText("변경"); // 버튼 -> 변경으로 바꾸기
                binding_plus_subject.etSubject.setText(beforeSub); // 기존 데이터


                final AlertDialog dialog = builder.create();

                binding_plus_subject.btnSubjectInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //새로 입력한 이름으로 업데이트
                        String str_subject = binding_plus_subject.etSubject.getText().toString();
                        String str_subject_time = beforeTime;

                        if (str_subject.equals(".")) {
                            Toast.makeText(ct, "과목명은 .만 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
                        } else {

                            watchViewModel.editSubject(new EditSubjectData(position + 1, str_subject, username, curDate));

                            watchViewModel.editSubResult.observe(getViewLifecycleOwner(), res -> {
                                if (res.getCode() == 200) {
                                    SubjectItem ary = new SubjectItem(str_subject, str_subject_time);

                                    arrayList.set(position, ary);
                                    stopwatchAdapter.notifyItemChanged(position); //새로고침

                                    if (curPosition == position) {
                                        binding.subject.setText(str_subject);
                                    }

                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
                System.out.println("edit "+position);

                dialog.show();
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

                            if (strSubject.equals(".")) {
                                Toast.makeText(ct, "과목명은 .만 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();

                            } else {

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

                String curTime = "" + String.format("%02d", hour) + ":" + String.format("%02d", min) + ":"
                        + String.format("%02d", sec);

                watchViewModel.editTime(new EditTimeData(curPosition+1, curTime, username, curDate));
                watchViewModel.editTimeResult.observe(getViewLifecycleOwner(), res -> {
                    if (res.getCode() == 200) {
                        String curSub = arrayList.get(curPosition).getSubject();
                        SubjectItem ary = new SubjectItem(curSub, curTime);

                        arrayList.set(curPosition, ary);
                        stopwatchAdapter.notifyItemChanged(curPosition); //새로고침
                    }
                });

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

                String curTime = "00:00:00";
                binding.studyTime.setText(curTime);

                watchViewModel.deleteTime(new DeleteTimeData(curPosition+1, curTime, username, curDate));
                watchViewModel.deleteTimeResult.observe(getViewLifecycleOwner(), res -> {
                    if (res.getCode() == 200) {
                        String curSub = arrayList.get(curPosition).getSubject();
                        SubjectItem ary = new SubjectItem(curSub, curTime);

                        arrayList.set(curPosition, ary);
                        stopwatchAdapter.notifyItemChanged(curPosition); //새로고침
                    }
                });

                String curSub = arrayList.get(curPosition).getSubject();
                SubjectItem ary = new SubjectItem(curSub, curTime);

                arrayList.set(curPosition, ary);
                stopwatchAdapter.notifyItemChanged(curPosition); //새로고침
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



//            int tHour = hour + Integer.parseInt(strAry[0]);
//            int tMin = min + Integer.parseInt(strAry[1]);
//            int tSec = sec + Integer.parseInt(strAry[2]);

            binding.studyTime.setText("" + String.format("%02d", hour) + ":" + String.format("%02d", min) + ":"
                    + String.format("%02d", sec));

            handler.postDelayed(this, 0);
        }
    };

}
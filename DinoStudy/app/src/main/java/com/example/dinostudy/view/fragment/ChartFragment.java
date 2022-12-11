package com.example.dinostudy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dinostudy.databinding.FragmentChartBinding;
import com.example.dinostudy.model.chart.ReadChartData;
import com.example.dinostudy.view.adapter.ChartAdapter;
import com.example.dinostudy.view.decorator.NotSelectedFourDecorator;
import com.example.dinostudy.view.decorator.NotSelectedThreeDecorator;
import com.example.dinostudy.view.decorator.NotSelectedTwoDecorator;
import com.example.dinostudy.view.decorator.SelectedFourDecorator;
import com.example.dinostudy.view.decorator.NotSelectedOneDecorator;
import com.example.dinostudy.view.decorator.SaturdayDecorator;
import com.example.dinostudy.view.decorator.SelectDecorator;
import com.example.dinostudy.view.decorator.SundayDecorator;
import com.example.dinostudy.view.decorator.SelectedOneDecorator;
import com.example.dinostudy.view.decorator.SelectedThreeDecorator;
import com.example.dinostudy.view.decorator.TodayDecorator;
import com.example.dinostudy.view.decorator.SelectedTwoDecorator;
import com.example.dinostudy.view.item.SubjectItem;
import com.example.dinostudy.viewModel.ChartViewModel;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChartFragment extends Fragment {
    private FragmentChartBinding binding;
    private DayViewDecorator dayDecorator;
    private ChartViewModel chartViewModel;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<SubjectItem> arrayList;
    private ChartAdapter chartAdapter;
    Context ct;

    public ChartFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(inflater, container, false);
        ct = container.getContext();
        chartViewModel = new ViewModelProvider(this).get(ChartViewModel.class);

        // username 받아오기
        String username = getArguments().getString("username");

        // 현재 날짜 불러오기
        long now = System.currentTimeMillis();
        Date curdate = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String curDate = sdf.format(curdate);

        // 오늘 날짜 & 주말 데코레이터 달기
        binding.calendarView.addDecorators(new TodayDecorator(), new SaturdayDecorator(), new SundayDecorator());

        // 선택 날짜 데코레이터 달기
        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Date selDate = date.getDate();
                binding.calendarView.addDecorator(new SelectDecorator(selDate, getActivity()));

//                String year = Integer.toString(date.getYear());
//                String month = Integer.toString(date.getMonth() + 1);
//                String day = Integer.toString(date.getDay());
//
//                System.out.println(year + "." + month + "." + day);

                String strDate = date.getYear() + "." + String.format("%02d", date.getMonth() + 1) + "." + String.format("%02d", date.getDay());

                System.out.println(strDate);

                arrayList.clear();
                chartAdapter.notifyDataSetChanged(); //새로고침

                chartViewModel.readChart(new ReadChartData(username, strDate));
            }
        });


        linearLayoutManager = new LinearLayoutManager(ct);
        binding.rvChartview.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        chartAdapter = new ChartAdapter(ct, arrayList);
        binding.rvChartview.setAdapter(chartAdapter);

        // 데이터 읽어와서 데코레이터 & 통계 달기
        chartViewModel.readChart(new ReadChartData(username, curDate));
        chartViewModel.readResult.observe(getViewLifecycleOwner(), res -> {
            int n = res.getResult().size();

            for (int i=0; i<n; i++) {

                if (res.getResult().get(i).getCurdate().equals(res.getCurdate())) {
                    String sub1 = res.getResult().get(i).getSubject1();
                    String time1 = res.getResult().get(i).getTime1();

                    String sub2 = res.getResult().get(i).getSubject2();
                    String time2 = res.getResult().get(i).getTime2();

                    String sub3 = res.getResult().get(i).getSubject3();
                    String time3 = res.getResult().get(i).getTime3();

                    String sub4 = res.getResult().get(i).getSubject4();
                    String time4 = res.getResult().get(i).getTime4();

                    String sub5 = res.getResult().get(i).getSubject5();
                    String time5 = res.getResult().get(i).getTime5();

                    String sub6 = res.getResult().get(i).getSubject6();
                    String time6 = res.getResult().get(i).getTime6();

                    String sub7 = res.getResult().get(i).getSubject7();
                    String time7 = res.getResult().get(i).getTime7();

                    String sub8 = res.getResult().get(i).getSubject8();
                    String time8 = res.getResult().get(i).getTime8();

                    String sub9 = res.getResult().get(i).getSubject9();
                    String time9 = res.getResult().get(i).getTime9();

                    String sub10 = res.getResult().get(i).getSubject10();
                    String time10 = res.getResult().get(i).getTime10();

                    if (!sub1.equals(".")) {
                        arrayList.add(new SubjectItem(sub1, time1));
                    }

                    if (!sub2.equals(".")) {
                        arrayList.add(new SubjectItem(sub2, time2));
                    }

                    if (!sub3.equals(".")) {
                        arrayList.add(new SubjectItem(sub3, time3));
                    }

                    if (!sub4.equals(".")) {
                        arrayList.add(new SubjectItem(sub4, time4));
                    }

                    if (!sub5.equals(".")) {
                        arrayList.add(new SubjectItem(sub5, time5));
                    }

                    if (!sub6.equals(".")) {
                        arrayList.add(new SubjectItem(sub6, time6));
                    }

                    if (!sub7.equals(".")) {
                        arrayList.add(new SubjectItem(sub7, time7));
                    }

                    if (!sub8.equals(".")) {
                        arrayList.add(new SubjectItem(sub8, time8));
                    }

                    if (!sub9.equals(".")) {
                        arrayList.add(new SubjectItem(sub9, time9));
                    }

                    if (!sub10.equals(".")) {
                        arrayList.add(new SubjectItem(sub10, time10));
                    }

                    System.out.println(arrayList);
                    chartAdapter.notifyDataSetChanged(); //새로고침
                }

                String time = res.getResult().get(i).getTime();
                String getDate = res.getResult().get(i).getCurdate();

                int hour = Integer.parseInt(time.substring(0, 2));
                int min = Integer.parseInt(time.substring(3, 5));
                int sec = Integer.parseInt(time.substring(6, 8));

                min += sec / 60;
                sec = sec % 60;
                hour += min / 60;
                min = min % 60;

                System.out.println(getDate);
                System.out.println("h: " + hour + ", m: " + min + ", s: " + sec);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                try {
                    Date date = formatter.parse(getDate);

                    if (hour >= 6) {
                        if (res.getResult().get(i).getCurdate().equals(res.getCurdate())) {
                            dayDecorator = new SelectedFourDecorator(date, getActivity());
                        } else {
                            dayDecorator = new NotSelectedFourDecorator(date, getActivity());
                        }
                    } else if (hour >= 4) {
                        if (res.getResult().get(i).getCurdate().equals(res.getCurdate())) {
                            dayDecorator = new SelectedThreeDecorator(date, getActivity());
                        } else {
                            dayDecorator = new NotSelectedThreeDecorator(date, getActivity());
                        }
                    } else if (hour >= 2) {
                        if (res.getResult().get(i).getCurdate().equals(res.getCurdate())) {
                            dayDecorator = new SelectedTwoDecorator(date, getActivity());
                        } else {
                            dayDecorator = new NotSelectedTwoDecorator(date, getActivity());
                        }
                    } else if ((hour < 2) && ((hour > 0) || (min > 0) || (sec > 0))) {
                        if (res.getResult().get(i).getCurdate().equals(res.getCurdate())) {
                            dayDecorator = new SelectedOneDecorator(date, getActivity());
                        } else {
                            dayDecorator = new NotSelectedOneDecorator(date, getActivity());
                        }
                    }

                    // 데코레이터
                    binding.calendarView.addDecorator(dayDecorator);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });




        return binding.getRoot();
    }
}

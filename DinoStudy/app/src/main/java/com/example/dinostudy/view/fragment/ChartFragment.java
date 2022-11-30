package com.example.dinostudy.view.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.FragmentChartBinding;
import com.example.dinostudy.databinding.FragmentWatchBinding;
import com.example.dinostudy.view.decorator.SaturdayDecorator;
import com.example.dinostudy.view.decorator.SelectDecorator;
import com.example.dinostudy.view.decorator.SundayDecorator;
import com.example.dinostudy.view.decorator.TestDecorator;
import com.example.dinostudy.view.decorator.TodayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ChartFragment extends Fragment {
    private FragmentChartBinding binding;
    private DayViewDecorator dayDecorator;

    Context ct;

    public ChartFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(inflater, container, false);

        ct = container.getContext();

        binding.calendarView.addDecorators(new TodayDecorator(), new SaturdayDecorator(), new SundayDecorator());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date date = formatter.parse("2022.11.22");
            dayDecorator = new TestDecorator(date, getActivity());
            binding.calendarView.addDecorator(dayDecorator);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Date selDate = date.getDate();
                binding.calendarView.addDecorator(new SelectDecorator(selDate, getActivity()));
            }
        });

        return binding.getRoot();
    }
}

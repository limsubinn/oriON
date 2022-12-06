package com.example.dinostudy.view.decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dinostudy.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;
import java.util.Date;

public class NotSelectedThreeDecorator implements DayViewDecorator {
    private CalendarDay date;
    private final Calendar calendar = Calendar.getInstance();
    Context context;

    public NotSelectedThreeDecorator(Date value, Context context) {
        date = CalendarDay.from(value);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        return day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.cal_state3_false));}
}

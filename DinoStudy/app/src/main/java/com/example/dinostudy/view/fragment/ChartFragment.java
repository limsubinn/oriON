package com.example.dinostudy.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.FragmentChartBinding;

public class ChartFragment extends Fragment {
    private FragmentChartBinding binding;

    public ChartFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentChartBinding.inflate(getLayoutInflater());


        return inflater.inflate(R.layout.fragment_chart, container, false);
    }
}

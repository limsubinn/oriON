package com.example.dinostudy.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.ActivityMainBinding;
import com.example.dinostudy.view.fragment.BoardFragment;
import com.example.dinostudy.view.fragment.ChartFragment;
import com.example.dinostudy.view.fragment.DiaryFragment;
import com.example.dinostudy.view.fragment.HeartFragment;
import com.example.dinostudy.view.fragment.TodoFragment;
import com.example.dinostudy.view.fragment.WatchFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // username 받아오기
        Intent intent = getIntent();
        String username = intent.getStringExtra("nickname");


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 왼쪽 상단 버튼 만들기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navi_menu); //왼쪽 상단 버튼 아이콘 지정
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        //actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        //actionBar.setHomeAsUpIndicator(R.drawable.menu); //뒤로가기 버튼 이미지 지정

        // default로 to do 보이게 함
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        TodoFragment fragment_todo = new TodoFragment();

        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        fragment_todo.setArguments(bundle);

        transaction.replace(binding.frame.getId(),fragment_todo);
        transaction.commit();


        binding.btnChk.setImageResource(R.drawable.checkbox_y);
        binding.btnChart.setImageResource(R.drawable.chart_n);
        binding.btnWatch.setImageResource(R.drawable.watch_n);
        binding.btnCommu.setImageResource(R.drawable.bubble_n);
        binding.btnDiary.setImageResource(R.drawable.diary_n);
        binding.btnHeart.setImageResource(R.drawable.heart_n);


        binding.btnChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                TodoFragment fragment_todo = new TodoFragment();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment_todo.setArguments(bundle);

                transaction.replace(binding.frame.getId(),fragment_todo);
                transaction.commit();


                binding.btnChk.setImageResource(R.drawable.checkbox_y);
                binding.btnChart.setImageResource(R.drawable.chart_n);
                binding.btnWatch.setImageResource(R.drawable.watch_n);
                binding.btnCommu.setImageResource(R.drawable.bubble_n);
                binding.btnDiary.setImageResource(R.drawable.diary_n);
                binding.btnHeart.setImageResource(R.drawable.heart_n);

            }
        });

        binding.btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                ChartFragment fragment_chart = new ChartFragment();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment_chart.setArguments(bundle);

                transaction.replace(binding.frame.getId(),fragment_chart);
                transaction.commit();

                binding.btnChk.setImageResource(R.drawable.checkbox_n);
                binding.btnChart.setImageResource(R.drawable.chart_y);
                binding.btnWatch.setImageResource(R.drawable.watch_n);
                binding.btnCommu.setImageResource(R.drawable.bubble_n);
                binding.btnDiary.setImageResource(R.drawable.diary_n);
                binding.btnHeart.setImageResource(R.drawable.heart_n);
            }
        });

        binding.btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                WatchFragment fragment_watch = new WatchFragment();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment_watch.setArguments(bundle);

                transaction.replace(binding.frame.getId(),fragment_watch);
                transaction.commit();

                binding.btnChk.setImageResource(R.drawable.checkbox_n);
                binding.btnChart.setImageResource(R.drawable.chart_n);
                binding.btnWatch.setImageResource(R.drawable.watch_y);
                binding.btnCommu.setImageResource(R.drawable.bubble_n);
                binding.btnDiary.setImageResource(R.drawable.diary_n);
                binding.btnHeart.setImageResource(R.drawable.heart_n);
            }
        });

        binding.btnCommu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                BoardFragment fragment_board = new BoardFragment();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment_board.setArguments(bundle);

                transaction.replace(binding.frame.getId(),fragment_board);
                transaction.commit();

                binding.btnChk.setImageResource(R.drawable.checkbox_n);
                binding.btnChart.setImageResource(R.drawable.chart_n);
                binding.btnWatch.setImageResource(R.drawable.watch_n);
                binding.btnCommu.setImageResource(R.drawable.bubble_y);
                binding.btnDiary.setImageResource(R.drawable.diary_n);
                binding.btnHeart.setImageResource(R.drawable.heart_n);
            }
        });

        binding.btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                DiaryFragment fragment_diary = new DiaryFragment();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment_diary.setArguments(bundle);

                transaction.replace(binding.frame.getId(),fragment_diary);
                transaction.commit();

                binding.btnChk.setImageResource(R.drawable.checkbox_n);
                binding.btnChart.setImageResource(R.drawable.chart_n);
                binding.btnWatch.setImageResource(R.drawable.watch_n);
                binding.btnCommu.setImageResource(R.drawable.bubble_n);
                binding.btnDiary.setImageResource(R.drawable.diary_y);
                binding.btnHeart.setImageResource(R.drawable.heart_n);
            }
        });

        binding.btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                HeartFragment fragment_heart = new HeartFragment();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                fragment_heart.setArguments(bundle);

                transaction.replace(binding.frame.getId(),fragment_heart);
                transaction.commit();

                binding.btnChk.setImageResource(R.drawable.checkbox_n);
                binding.btnChart.setImageResource(R.drawable.chart_n);
                binding.btnWatch.setImageResource(R.drawable.watch_n);
                binding.btnCommu.setImageResource(R.drawable.bubble_n);
                binding.btnDiary.setImageResource(R.drawable.diary_n);
                binding.btnHeart.setImageResource(R.drawable.heart_y);
            }
        });

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                binding.drawerLayout.closeDrawers();

                int id = item.getItemId();
                String title = item.getTitle().toString();

                if(id == R.id.item_info){
                    //Intent intent = new Intent(getApplicationContext(), ??.class);
                    //startActivity(intent);
                } else if(id == R.id.item_mission){
                    Intent intent = new Intent(getApplicationContext(), MissionActivity.class);
                    startActivity(intent);
                } else if(id == R.id.item_group){

                }

                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
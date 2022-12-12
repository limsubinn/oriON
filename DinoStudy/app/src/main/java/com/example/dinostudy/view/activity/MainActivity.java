package com.example.dinostudy.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.ActivityMainBinding;
import com.example.dinostudy.view.adapter.BoardAdapter;
import com.example.dinostudy.view.fragment.BoardFragment;
import com.example.dinostudy.view.fragment.ChartFragment;
import com.example.dinostudy.view.fragment.DiaryFragment;
import com.example.dinostudy.view.fragment.HeartFragment;
import com.example.dinostudy.view.fragment.PostFragment;
import com.example.dinostudy.view.fragment.TodoFragment;
import com.example.dinostudy.view.fragment.WatchFragment;
import com.example.dinostudy.view.fragment.WriteFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    public Bundle mBundle;

    public String username;
    public String userEmail;
    public String userCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // 사용자 정보 받아오기
        Intent intent = getIntent();
        username = intent.getStringExtra("nickname");
        userEmail = intent.getStringExtra("email");
        userCoin = intent.getStringExtra("coin");

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

        // 네비게이션 드로어 안에 사용자 정보 띄우기
        LinearLayout ll_navigation_container = (LinearLayout)  LayoutInflater.from(this).inflate(R.layout.navi_header, null);
        ll_navigation_container.setBackground(getResources().getDrawable(R.color.state1));
        ll_navigation_container.setPadding(20, 150, 40, 50);
        ll_navigation_container.setOrientation(LinearLayout.VERTICAL);
        ll_navigation_container.setGravity(Gravity.TOP);
        ll_navigation_container.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);



        // 사용자 이름
        final TextView tv_username = new TextView(this);
        tv_username.setTextColor(getResources().getColor(R.color.black));
        tv_username.setTextSize(20);
        tv_username.setPadding(0, 2, 0, 2);
        param1.setMargins(30, 20, 20, 5);
        tv_username.setLayoutParams(param1);

        // 사용자 이메일
        final TextView tv_useremail = new TextView(this);
        tv_useremail.setTextColor(getResources().getColor(R.color.black));
        tv_useremail.setTextSize(14);
        tv_useremail.setPadding(0, 2, 0, 2);
        param1.setMargins(30, 20, 20, 5);
        tv_useremail.setLayoutParams(param1);


        // 사용자 코인
        final TextView tv_coin = new TextView(this);
        tv_coin.setTextColor(getResources().getColor(R.color.black));
        tv_coin.setTextSize(14);
        tv_coin.setPadding(0, 2, 0, 2);
        param1.setMargins(30, 20, 20, 5);
        tv_coin.setLayoutParams(param1);


        tv_username.setText(username + " 님");
        tv_useremail.setText(userEmail);
        tv_coin.setText("🪙 " +userCoin + "코인");


        // ll_navigation_container에 만든 요소들을 담음
        ll_navigation_container.addView(tv_username);
        ll_navigation_container.addView(tv_useremail);
        ll_navigation_container.addView(tv_coin);


        binding.navigationView.addHeaderView(ll_navigation_container);
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                binding.drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                if(id == R.id.item_info) {
                    // 내 정보 ..
                    Intent main_to_mypage = new Intent(getApplicationContext(), MissionActivity.class);
                    main_to_mypage.putExtra("nickname", username); // username 보내기
                    main_to_mypage.putExtra("coin", userCoin);
                    main_to_mypage.putExtra("email",userEmail);
                    startActivity(main_to_mypage);
                    binding.drawerLayout.closeDrawers();


                } else if (id == R.id.item_mission) {
                    Intent main_to_mission = new Intent(getApplicationContext(), MissionActivity.class);
                    main_to_mission.putExtra("nickname", username); // username 보내기
                    main_to_mission.putExtra("coin", userCoin);
                    main_to_mission.putExtra("email",userEmail);
                    startActivity(main_to_mission);
                    binding.drawerLayout.closeDrawers();

                }

                return true;
            }
        });
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
                getSupportFragmentManager().setFragmentResult("goto_board", bundle);
                // fragment_board.setArguments(bundle);



                transaction.replace(binding.frame.getId(),fragment_board);
                transaction.commit();

//                writeFragment.setArguments(bundle);

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

    // 인덱스를 통해 해당되는 프래그먼트를 띄운다.
    public void fragmentChange(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(), getItem(0)).commit();
        }else if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(), getItem(1)).commit();
        }else if(index == 2) {
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(), getItem(2)).commit();
        }

    }

    public Fragment getItem(int position) {
        if (position == 0) {
            BoardFragment boardFragment = new BoardFragment();
            return boardFragment;
        } else if (position == 1) {
            WriteFragment writeFragment = new WriteFragment();
            return writeFragment;
        } else if (position == 2) {
            PostFragment postFragment = new PostFragment();
            return postFragment;
        }else {
            return null;
        }

    }

//    public void fragBtnClick(Bundle bundle) {
//        this.mBundle = bundle;
//    } //fragBtnClcick()
}
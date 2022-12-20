package com.example.orion.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.orion.model.heart.EditGameData;
import com.example.orion.model.user.EditCoinData;
import com.example.orion.viewModel.HeartViewModel;
import com.example.orion.viewModel.UserViewModel;

public class SubUnityActivity extends AppCompatActivity {
    private HeartViewModel heartViewModel;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        heartViewModel = new ViewModelProvider(this).get(HeartViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        // 사용자 정보 받아오기
        Intent getIntent = getIntent();

        String playerName = getIntent.getStringExtra("playerName");
        String playerGold = getIntent.getStringExtra("playerGold");
        String playerData = getIntent.getStringExtra("playerData");
        String playerEmail = getIntent.getStringExtra("playerEmail");

        // 게임 데이터 수정
        heartViewModel.editGame(new EditGameData(playerName, playerData));

        // 코인 데이터 수정
        userViewModel.setCoin(new EditCoinData(playerName, Integer.parseInt(playerGold)));

        // MainActivity로 보낸다.
        Intent putIntent = new Intent(getApplicationContext(), MainActivity.class);
        putIntent.putExtra("nickname", playerName); // username 보내기
        putIntent.putExtra("coin", playerGold);
        putIntent.putExtra("email", playerEmail);
        startActivity(putIntent);
        finish();
    }
}

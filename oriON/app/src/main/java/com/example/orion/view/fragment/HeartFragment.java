package com.example.orion.view.fragment;

import static android.content.ContentValues.TAG;
import static android.content.Intent.getIntent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.orion.R;
import com.example.orion.databinding.FragmentDiaryBinding;
import com.example.orion.databinding.FragmentHeartBinding;
import com.example.orion.model.heart.ReadGameData;
import com.example.orion.view.activity.MainUnityActivity;
import com.example.orion.view.activity.MainActivity;
import com.example.orion.viewModel.HeartViewModel;
import com.example.orion.viewModel.WatchViewModel;

public class HeartFragment extends Fragment {

    private FragmentHeartBinding binding;
    private HeartViewModel heartViewModel;

    Button btn_unity;
    //유니티 실행 상태
    boolean isUnityLoaded = false;
    String color="red";
    String username;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHeartBinding.inflate(inflater, container, false);

        heartViewModel = new ViewModelProvider(this).get(HeartViewModel.class);


        Intent intent=getActivity().getIntent();
        handleIntent(intent);
        username = getArguments().getString("username");

        heartViewModel.readGame(new ReadGameData(username));
        heartViewModel.readResult.observe(getViewLifecycleOwner(), res -> {
            if (res.getCode() == 200) { // 데이터 존재
                int coin = res.getCoin();
                String gamedata = res.getGamedata();
                String email = res.getEmail();

                binding.btnUnity.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        showUnity(username, coin, gamedata, email);

                    }
                });
            }
            else {
                // 에러
                Log.d("Heart", "error");
            }
        });

        //handleIntent(getIntent());

        return binding.getRoot();
    }
    /*
        //기존 엑티비티(홈화면) 다시 실행 ( quit, finish 버튼엔 안먹힘 )
        @Override
        protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            showToast("다시실행");
            handleIntent(intent);
            getActivity().setIntent(intent);
        }
    */
    void handleIntent(Intent intent) {

        // 정보가 null이면 리턴
        if(intent == null || intent.getExtras() == null) return;

        //setColor 정보 받아와서 버튼 색 바꾸기
        if(intent.getExtras().containsKey("username")){
            View v = btn_unity;
            switch (intent.getExtras().getString("setColor")) {
                case "yellow": showToast("yellow from unity"); break;
                case "red": showToast("red from unity"); break;
                case "blue": showToast("blue from unity"); break;
                default: break;

                /*
                case "yellow": v.setBackgroundColor(Color.YELLOW); break;
                case "red": v.setBackgroundColor(Color.RED); break;
                case "blue": v.setBackgroundColor(Color.BLUE); break;
                default: break;

                 */
            }
        }
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.d(TAG, "MainActivity로 돌아왔다. ");
                    }
                }
            });

    public void showUnity(String username, int coin, String gamedata, String email) {

        Log.d(TAG, "1실행 ");
        isUnityLoaded = true;
        Intent intent = new Intent(getContext(), MainUnityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("username", username);
        intent.putExtra("coin", Integer.toString(coin));
        intent.putExtra("gamedata", gamedata);
        intent.putExtra("email", email);
        startActivityResult.launch(intent);
        Log.d(TAG, "2실행 ");
        Log.d(TAG,intent.toString());
        //showToast(username);
    }

    public void showToast(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), text, duration);
        toast.show();
    }



}

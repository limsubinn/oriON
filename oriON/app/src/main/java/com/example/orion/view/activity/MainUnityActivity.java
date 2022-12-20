package com.example.orion.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.company.product.OverrideUnityActivity;
import com.example.orion.viewModel.HeartViewModel;
import com.example.orion.viewModel.UserViewModel;
import com.unity3d.player.UnityPlayer;


public class MainUnityActivity extends OverrideUnityActivity {
    private HeartViewModel heartViewModel;
    private UserViewModel userViewModel;

    String playerName = "";
    String playerData = "";
    String playerGold = "";
    String playerEmail = "";
    String MessagetoUnity="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addControlsToUnityFrame(); //유니티 위에 안드로이드 레이아웃 겹치기
        Intent intent = getIntent();
        handleIntent(intent);
    }



    //기존 엑티비티 다시 실행
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
        setIntent(intent);
    }

    void handleIntent(Intent intent) {
        playerName = intent.getExtras().getString("username");
        playerGold = intent.getExtras().getString("coin");
        playerData = intent.getExtras().getString("gamedata");

        playerEmail = intent.getExtras().getString("email");

        MessagetoUnity = playerName +"#"+ playerGold +"#"+ playerData;


        if(intent == null || intent.getExtras() == null) {
            Log.v("unitycheck","if_unitycheck");
            return;
        }
        else if(intent.getExtras().containsKey("username")){

            //View v = btn_unity;
            Log.v("unitycheck","elseif_unitycheck");
            Log.v("unitycheck",MessagetoUnity);
            UnityPlayer.UnitySendMessage("DataManager", "GetMessage", MessagetoUnity);
            switch (intent.getExtras().getString("username")) {
                case "yellow":  break;
                case "red":  break;
                case "blue":  break;
                default: Log.v("unitycheck",playerName); break;

                /*
                case "yellow": v.setBackgroundColor(Color.YELLOW); break;
                case "red": v.setBackgroundColor(Color.RED); break;
                case "blue": v.setBackgroundColor(Color.BLUE); break;
                default: break;

                 */
            }
        }
        else{

            //showToast(playerName);
            Log.v("unitycheck","1unitycheck");
            UnityPlayer.UnitySendMessage("GameManager", "GetName", playerName);  //유니티에 메세지 보내기
            Log.v("unitycheck","2unitycheck");



        }

//        if(intent.getExtras().containsKey("doQuit")) //홈화면에서 Finish 버튼을 누르면 유니티 종료
//            if(mUnityPlayer != null) {
//                finish();
//            }
    }



    //유니티에서의 Unload 버튼을 눌렀을 때
    @Override public void onUnityPlayerUnloaded()
    {
        Log.v("unitycheck","onUnityPlayerunloaded ");
        showMainActivity("");
    }

    @Override
    protected void showMainActivity(String fromUnity) {

        Intent intent = new Intent(this, SubUnityActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //실행하는 엑티비티를 최상위로 올림, 이미 최상단에 있다면 다시 시작하지 않고 재사용
        //intent.putExtra("username", username);
        Log.v("unitycheck","now unitycheck message: " + fromUnity);
        String[] unityMessage = fromUnity.split("#");
        playerName = unityMessage[0];
        playerGold = unityMessage[1];
        playerData = unityMessage[2];

        Log.v("unitycheck","unitycheck From Unity: " + playerName);
        Log.v("unitycheck","unitycheck From Unity: " + playerGold);
        Log.v("unitycheck","unitycheck From Unity: " + playerData);

        intent.putExtra("playerName", playerName);
        intent.putExtra("playerGold", playerGold);
        intent.putExtra("playerData", playerData);
        intent.putExtra("playerEmail", playerEmail);

        startActivity(intent); //홈화면 보이기

    }
}


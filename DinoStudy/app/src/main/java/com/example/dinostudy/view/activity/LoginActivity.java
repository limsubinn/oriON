package com.example.dinostudy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dinostudy.R;
import com.example.dinostudy.databinding.ActivityLoginBinding;
import com.example.dinostudy.model.CheckEmailData;
import com.example.dinostudy.viewModel.LoginViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;

    private FirebaseAuth auth; // 파이어베이스 인증 객체
    private GoogleSignInClient googleSignInClient; // 구글 API 클라이언트 객체
    private static final int REQ_SIGN_IN_GOOGLE = 100; // 구글 로그인 결과 코드
    private static final int REQ_GET_TOKEN = 200;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//                .build();

        auth=FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent,REQ_SIGN_IN_GOOGLE); // 결과값을 받는다.
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // 구글 인증 후 결과 받아냄
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_SIGN_IN_GOOGLE) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//
//            if(result.isSuccess()){ // 인증결과가 성공적이면 실행
//                GoogleSignInAccount account = result.getSignInAccount(); //account에는 구글 로그인 정보를 담고 있다.
//                resultLogin(account);//로그인 결과 값 출력 수행하라는 메소드
//            }

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (task.isSuccessful()) {
                GoogleSignInAccount account = task.getResult();
                resultLogin(account);
            }
        }
    }

    // 인증결과 성공후 로그인 진행
    private void resultLogin(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ // 로그인이 성공했으면
                            Toast.makeText(LoginActivity.this,"로그인 성공", Toast.LENGTH_SHORT).show();
                            // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            // intent.putExtra("nickname", account.getDisplayName());

                            // String g_name = account.getDisplayName(); // 닉네임
                            String g_mail = account.getEmail(); // 이메일

                            loginViewModel.checkUserEmail(new CheckEmailData(g_mail));

                            loginViewModel.resultCode.observe(LoginActivity.this, res -> {
                                if(res.getCode() == 200) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("nickname", res.getMessage()); // username 보내기
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            // startActivity(intent);
                        } else { //로그인이 실패했으면
                            Toast.makeText(LoginActivity.this,"로그인 실패", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnSignIn:
//                Intent intent= googleSignInClient.getSignInIntent();
//                startActivityForResult(intent,RC_SIGN_IN);
//                break;
//        }
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        FirebaseAuth.getInstance().signOut();
//        googleSignInClient.signOut();
//
//    }
}
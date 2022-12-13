package com.example.dinostudy.repository;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private final static String BASE_URL = "http://172.30.1.15:3000/";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

//    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .connectTimeout(1, TimeUnit.MINUTES) // 요청을 시작한 후 서버와의 TCP handshake가 완료되기 까지 지속되는 시간.
//            .readTimeout(30, TimeUnit.SECONDS) // 읽기 시간 초과는 연결이 설정되면 모든 바이트가 전송되는 속도를 감시한다.
//            .writeTimeout(15, TimeUnit.SECONDS) // 쓰기 타임 아웃은 읽기 타움 아웃의 반대 방향이다. 얼마나 빨리 서버에 바이트를 보낼 수 있는지 확인한다.
//            .build();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // 요청을 보낼 base url을 설정한다.
//                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 파싱을 위한 GsonConverterFactory를 추가한다.
                    .build();
        }

        return retrofit;
    }
}
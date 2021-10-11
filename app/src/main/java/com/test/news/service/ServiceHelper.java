package com.test.news.service;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.news.model.Constants.BASE_URL;

public class ServiceHelper {
    public static Retrofit retrofit=null;

    //okhttp

    public static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override public void log(String message) {
            Log.i("News",""+message );
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);


    public static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();



    public static IAPI_Helper getRestAPIHelper(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit.create(IAPI_Helper.class);
    }



}

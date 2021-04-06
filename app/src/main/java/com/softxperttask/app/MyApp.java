package com.softxperttask.app;

import android.app.Application;
import android.content.Context;

import com.softxperttask.app.data.api.RetrofitApiService;
import com.softxperttask.app.data.model.CarResponse;
import com.softxperttask.app.repo.CarsRepo;
import com.softxperttask.app.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MyApp extends Application {

    private static MyApp mInstance;
    private static RetrofitApiService retrofitService;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        retrofitBuilder();
        CarsRepo.init(getRetrofitService());
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static RetrofitApiService getRetrofitService() {
        return retrofitService;
    }

    private void retrofitBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Base_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitApiService.class);
    }

}

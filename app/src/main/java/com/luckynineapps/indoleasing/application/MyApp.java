package com.luckynineapps.indoleasing.application;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.google.android.gms.ads.MobileAds;
import com.luckynineapps.indoleasing.R;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);

        MobileAds.initialize(this, getResources().getString(R.string.ad_app_id));
    }

}

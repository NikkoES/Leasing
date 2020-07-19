package com.luckynineapps.indoleasing.data;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.luckynineapps.indoleasing.activities.PinjamanActivity;

/**
 * Created by Comp on 7/29/2017.
 */

public class Session {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "indo-leasing";

    private static final String IS_LOGIN = "IsLoged";
    private static final String IS_FIRST = "IsFisrt";
    private static final String IS_NOT_ALARMT = "IsAlarm";

    public static final String KEY_USER = "user";
    public static final String KEY_COUNT = "count";
    public static final String KEY_COUNT_ADV = "count_adv";
    public static final String KEY_COUNT_DOWNLOAD = "count_download";
    public static final String KEY_COUNT_COMPARE = "count_compare";
    public static final String KEY_COUNT_CALCULATOR = "count_calculator";
    public static final String KEY_ALARM = "alarm";
    public static final String KEY_IP = "IP";

    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);

        editor.putString(KEY_USER, json);

        editor.putBoolean(IS_LOGIN, true);

        editor.commit();

    }

    public void setAdvanceClickCount(){
        int count = getAdvanceClickCount();
        count++;

        editor.putInt(KEY_COUNT_ADV, 0);
        editor.commit();
    }

    public void setClickCount() {
        int count = getClickCount();
        count++;

        editor.putInt(KEY_COUNT, count);
        editor.commit();
    }

    public void setClickCountCompare() {
        int count = getClickCountCompare();
        count++;

        editor.putInt(KEY_COUNT_COMPARE, count);
        editor.commit();
    }

    public void setClickCountCalculator() {
        int count = getClickCountCalculator();
        count++;

        editor.putInt(KEY_COUNT_CALCULATOR, count);
        editor.commit();
    }

    public void setClickCountDownload() {
        int count = getClickCountDowwload();
        count++;

        editor.putInt(KEY_COUNT_DOWNLOAD, count);
        editor.commit();
    }

    public int getAdvanceClickCount() {
        return pref.getInt(KEY_COUNT_ADV, 0);
    }

    public int getClickCount() {
        return pref.getInt(KEY_COUNT, 0);
    }

    public int getClickCountCompare() {
        return pref.getInt(KEY_COUNT_COMPARE, 0);
    }

    public int getClickCountCalculator() {
        return pref.getInt(KEY_COUNT_CALCULATOR, 0);
    }

    public int getClickCountDowwload() {
        return pref.getInt(KEY_COUNT_DOWNLOAD, 0);
    }

    public String getUser() {
        Gson gson = new Gson();
        String json = pref.getString(KEY_USER, "");
        String obj = gson.fromJson(json, String.class);
        return obj;
    }

    public void checkLogin() {
        if (isLoggedIn()) {
            Intent i = new Intent(_context, PinjamanActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void clearAlarm() {
        pref.edit().remove(KEY_ALARM).commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        editor.putBoolean(IS_LOGIN, false);
        editor.putBoolean(IS_FIRST, true);
        editor.putBoolean(IS_NOT_ALARMT, true);


        //TODO harusnya ke login activity kalo ada
        Intent i = new Intent(_context, PinjamanActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void setIsLogin(Boolean v) {
        editor.putBoolean(IS_LOGIN, v);
        editor.commit();
    }

    public boolean isFirst() {
        return pref.getBoolean(IS_FIRST, false);
    }

    public boolean isAlarm() {
        return pref.getBoolean(IS_NOT_ALARMT, false);
    }

    public String getIp() {
        return pref.getString(KEY_IP, "192.168.1.1");
    }

    public void setIsFisrt(Boolean v) {
        editor.putBoolean(IS_FIRST, v);
        editor.commit();
    }

    public void setAlarm(Boolean v) {
        editor.putBoolean(IS_NOT_ALARMT, v);
        editor.commit();
    }

    public void setIp(String ip) {
        editor.putString(KEY_IP, ip);
        editor.commit();
    }
}

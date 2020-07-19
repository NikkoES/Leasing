package com.luckynineapps.indoleasing.activities.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.luckynineapps.indoleasing.R;
import com.luckynineapps.indoleasing.activities.model.Hasil;
import com.luckynineapps.indoleasing.data.Session;
import com.luckynineapps.indoleasing.utils.AdsUtil;
import com.luckynineapps.indoleasing.utils.NumberUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashscreenActivity extends AppCompatActivity {

    int time = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, time);
    }
}

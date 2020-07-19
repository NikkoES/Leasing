package com.luckynineapps.indoleasing.activities.welcome;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.luckynineapps.indoleasing.activities.PinjamanActivity;
import com.luckynineapps.indoleasing.config.manager.ConfigManager;
import com.luckynineapps.indoleasing.config.manager.ConfigurationManager;
import com.luckynineapps.indoleasing.fragments.SliderFragment;
import com.luckynineapps.indoleasing.R;

public class WelcomeActivity extends AppIntro {

    private InterstitialAd inters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSlider();
        initAd();

        showSkipButton(false);
        setDoneText("GOT IT");

        ConfigurationManager.getInstance().getConfiguration().initialize();
        ConfigurationManager.getInstance().getConfiguration().synchronize(new ConfigManager.OnSynchronizedListener() {
            @Override
            public void onSyncSuccess() {

            }

            @Override
            public void onSyncError() {

            }
        });
    }

    private void initAd() {
        inters = new InterstitialAd(this);
        inters.setAdUnitId(getString(R.string.ad_interstitial));
        inters.loadAd(new AdRequest.Builder().build());
        inters.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                open();
            }
        });
    }

    private void initSlider() {
        addSlide(SliderFragment.newInstance(R.layout.fragment_slider_1));
        addSlide(SliderFragment.newInstance(R.layout.fragment_slider_2));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        if (inters.isLoaded()) {
            inters.show();
        } else {
            open();
        }
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    void open() {
        Intent i = new Intent(this, PinjamanActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}

package com.luckynineapps.indoleasing.utils;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.luckynineapps.indoleasing.R;

public class AdsUtil {
    private static AdsUtil mInstance;

    private InterstitialAd mInterstitialAd;
    private OnAdClosed onAdClosed;


    public static AdsUtil getInstance(Context context){
        if(mInstance == null){
            mInstance = new AdsUtil(context);
        }

        return mInstance;
    }

    private AdsUtil(Context mContext) {
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(mContext.getString(R.string.ad_interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                if(onAdClosed != null){
                    onAdClosed.OnClosed();
                }
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if(onAdClosed != null){
                    onAdClosed.OnClosed();
                }
            }
        });
    }

    public void setOnAdClosed(OnAdClosed onAdClosed) {
        this.onAdClosed = onAdClosed;
    }

    public void showAds(){
        Log.d("TAG", "show The interstitial ads");
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }

    public interface OnAdClosed{
        void OnClosed();
    }
}

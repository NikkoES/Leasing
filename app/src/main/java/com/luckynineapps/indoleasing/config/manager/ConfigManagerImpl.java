package com.luckynineapps.indoleasing.config.manager;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hakim on 18/07/2019.
 */
public class ConfigManagerImpl implements ConfigManager {

    @NonNull
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public ConfigManagerImpl() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    }

    @Override
    public void initialize() {
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaults(getDefaultSettings());
    }

    @Override
    public void synchronize(final OnSynchronizedListener listener) {
        mFirebaseRemoteConfig.fetch(0)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mFirebaseRemoteConfig.activate();
                        listener.onSyncSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onSyncSuccess();
                    }
                });
    }

    @Override
    public String getBaseUrl() {
        return mFirebaseRemoteConfig.getString(OPTIONS.KEY_URL.toString());
    }

    private Map<String, Object> getDefaultSettings() {
        Map<String, Object> defaultConfig = new HashMap<>();
        defaultConfig.put(OPTIONS.KEY_URL.toString(), "https://ciptausahaanugrah.com/fintech");
        return defaultConfig;
    }
}

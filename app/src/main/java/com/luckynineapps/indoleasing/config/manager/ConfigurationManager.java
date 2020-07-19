package com.luckynineapps.indoleasing.config.manager;

/**
 * Created by Hakim on 18/07/2019.
 */
public class ConfigurationManager {
    private static ConfigManager mConfiguration;

    private static ConfigurationManager mInstance = null;

    public ConfigurationManager() {
        mConfiguration = new ConfigManagerImpl();
    }

    public static ConfigurationManager getInstance() {
        if (mInstance == null) {
            mInstance = new ConfigurationManager();
        }
        return mInstance;
    }

    public ConfigManager getConfiguration() {
        return mConfiguration;
    }
}

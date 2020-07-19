package com.luckynineapps.indoleasing.config.manager;

/**
 * Created by Hakim on 18/07/2019.
 */
public interface ConfigManager {

    enum OPTIONS {

        KEY_URL("KEY_URL");

        private final String text;

        OPTIONS(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }


    void initialize();

    void synchronize(OnSynchronizedListener listener);

    String getBaseUrl();

    interface OnSynchronizedListener {
        void onSyncSuccess();

        void onSyncError();
    }
}

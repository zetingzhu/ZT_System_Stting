package com.example.systemsettingtrae;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import java.util.Locale;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        applySavedLocale();
    }

    private void applySavedLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "zh");
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
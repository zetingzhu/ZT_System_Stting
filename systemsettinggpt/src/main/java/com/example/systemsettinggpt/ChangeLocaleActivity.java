package com.example.systemsettinggpt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ChangeLocaleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 应用全局语言
        applyAppLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_locale);

        TextView tvCurrentLanguage = findViewById(R.id.tv_current_language);

        // 设置当前语言文本
        tvCurrentLanguage.setText(getString(R.string.current_language));

        Button btnChinese = findViewById(R.id.btn_chinese);
        Button btnEnglish = findViewById(R.id.btn_english);

        btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocale("zh");
                restartApp();
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocale("en");
                restartApp();
            }
        });
    }

    // 保存语言到SharedPreferences
    private void saveLocale(String lang) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("app_locale", lang).apply();
    }

    // 应用全局语言
    private void applyAppLocale() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = prefs.getString("app_locale", "");
        if (!lang.isEmpty()) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = getResources().getConfiguration();
            config.setLocale(locale);
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }
    }

    // 重启应用主界面
    private void restartApp() {
        Intent intent = new Intent(this, MainActivityGPT.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }
}

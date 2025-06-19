package com.example.systemsettingtrae;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.content.res.Configuration;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class InternationalActivity extends AppCompatActivity {
    private Button btnChinese;
    private Button btnEnglish;
    private TextView tvLanguageStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international);

        btnChinese = findViewById(R.id.btn_chinese);
        btnEnglish = findViewById(R.id.btn_english);
        tvLanguageStatus = findViewById(R.id.tv_language_status);

        btnChinese.setOnClickListener(v -> switchLanguage(new Locale("zh")));
        btnEnglish.setOnClickListener(v -> switchLanguage(new Locale("en")));
    }

    private void switchLanguage(Locale locale) {
        // 保存语言设置到SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        sharedPreferences.edit().putString("language", locale.getLanguage()).apply();

        // 更新应用配置
        updateAppLocale(locale);

        // 重启应用以应用更改
        Intent intent = new Intent(this, MainActivityTrae.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void updateAppLocale(Locale locale) {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
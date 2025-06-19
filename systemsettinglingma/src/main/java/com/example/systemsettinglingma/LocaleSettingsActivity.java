package com.example.systemsettinglingma;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LocaleSettingsActivity extends AppCompatActivity {

    private Button englishButton;
    private Button chineseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_settings);

        englishButton = findViewById(R.id.english_button);
        chineseButton = findViewById(R.id.chinese_button);

        englishButton.setOnClickListener(v -> updateLanguage("en"));
        chineseButton.setOnClickListener(v -> updateLanguage("zh"));
    }

    private void updateLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // 更新应用全局语言配置
        updateAppLanguage(locale);

        // 重启应用以应用语言更改
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    // 新增方法：更新应用全局语言配置
    private void updateAppLanguage(Locale locale) {
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
package com.example.systemsettinglingma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ThemeSettingsActivity extends AppCompatActivity {

    private Button lightThemeButton;
    private Button darkThemeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_settings);


        // 新增: 初始化按钮
        lightThemeButton = findViewById(R.id.light_theme_button);
        darkThemeButton = findViewById(R.id.dark_theme_button);

        // 新增: 设置按钮点击事件
        lightThemeButton.setOnClickListener(v -> setThemeAndRestart(AppCompatDelegate.MODE_NIGHT_NO));
        darkThemeButton.setOnClickListener(v -> setThemeAndRestart(AppCompatDelegate.MODE_NIGHT_YES));
    }

    // 新增方法: 设置主题并重启应用
    private void setThemeAndRestart(int themeMode) {
        AppCompatDelegate.setDefaultNightMode(themeMode);

        // 重启应用以应用主题更改
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}

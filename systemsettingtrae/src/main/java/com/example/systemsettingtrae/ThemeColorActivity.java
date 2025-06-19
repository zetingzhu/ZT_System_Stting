package com.example.systemsettingtrae;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ThemeColorActivity extends AppCompatActivity {
    private Button btnLight;
    private Button btnDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_color);
        
        btnLight = findViewById(R.id.btn_light);
        btnDark = findViewById(R.id.btn_dark);
        
        btnLight.setOnClickListener(v -> switchTheme(AppCompatDelegate.MODE_NIGHT_NO));
        btnDark.setOnClickListener(v -> switchTheme(AppCompatDelegate.MODE_NIGHT_YES));
    }
    
    private void switchTheme(int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
        recreate();
    }
}
// 创建Activity C
package com.example.zt_taskv1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        
        // 跳转到我的Fragment按钮点击事件
        Button navigateButton = findViewById(R.id.btn_navigate_to_profile);
        if (navigateButton != null) {
            navigateButton.setOnClickListener(v -> {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("我的页面");
                }
                if (getApplicationContext() instanceof Navigator) {
                    ((Navigator) getApplicationContext()).navigateToFragment(2); // 2对应我的Fragment
                }
            });
        }
        
        // 返回按钮点击事件
        Button backButton = findViewById(R.id.btn_back);
        if (backButton != null) {
            backButton.setOnClickListener(v -> onBackPressed());
        }
    }
}
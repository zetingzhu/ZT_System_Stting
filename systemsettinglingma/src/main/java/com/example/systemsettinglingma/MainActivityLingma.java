package com.example.systemsettinglingma;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.adapter.StartActivityRecyclerAdapter;
import com.zzt.entity.StartActivityDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivityLingma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 重新初始化语言配置以应用最新的语言设置
        initializeLanguage();
    }

    // 新增方法: 初始化语言配置
    private void initializeLanguage() {
        Configuration config = getResources().getConfiguration();
        Locale locale = config.getLocales().get(0);
        updateAppLanguage(locale);
    }

    // 新增方法: 更新应用全局语言配置
    private void updateAppLanguage(Locale locale) {
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        List<StartActivityDao> mList = new ArrayList<>();
        mList.add(new StartActivityDao(ContextCompat.getString(getBaseContext(), R.string.update_language), "", "1"));
        mList.add(new StartActivityDao(ContextCompat.getString(getBaseContext(), R.string.update_theme), "", "2"));

        StartActivityRecyclerAdapter.setAdapterData(recyclerView, RecyclerView.VERTICAL, mList, new StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao>() {
            @Override
            public void onItemClick(View itemView, int position, StartActivityDao data) {
                String arouter = data.getArouter();
                if ("1".equals(arouter)) {
                    // 跳到修改国际化页面
                    Intent intent = new Intent(MainActivityLingma.this, LocaleSettingsActivity.class);
                    startActivity(intent);
                } else if ("2".equals(arouter)) {
                    // 跳到修改主题色页面
                    Intent intent = new Intent(MainActivityLingma.this, ThemeSettingsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
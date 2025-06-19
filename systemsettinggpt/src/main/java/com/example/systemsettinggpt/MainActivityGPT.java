package com.example.systemsettinggpt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class MainActivityGPT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyAppLocale(); // 确保每次启动都应用最新语言
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

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        ArrayList<StartActivityDao> mList = new ArrayList<>();
        mList.add(new StartActivityDao(ContextCompat.getString(getBaseContext() , R.string.update_language) , "", "1"));
        mList.add(new StartActivityDao(ContextCompat.getString(getBaseContext() , R.string.update_theme ) , "", "2"));

        StartActivityRecyclerAdapter.setAdapterData(recyclerView, RecyclerView.VERTICAL, mList, new StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao>() {
            @Override
            public void onItemClick(View itemView, int position, StartActivityDao data) {
                String arouter = data.getArouter();
                if ("1".equals(arouter)) {
                    // 跳到修改国际化页面
                    startActivity(new Intent(MainActivityGPT.this, ChangeLocaleActivity.class));
                } else if ("2".equals(arouter)) {
                    // 跳到修改主题色页面
                    startActivity(new Intent(MainActivityGPT.this, ChangeThemeActivity.class));
                }
            }
        });
    }
}
package com.example.systemsettingtrae;

import android.content.Intent;
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

public class MainActivityTrae extends AppCompatActivity {

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

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        List<StartActivityDao> mList = new ArrayList<>();
        mList.add(new StartActivityDao(ContextCompat.getString(getBaseContext() , R.string.update_language) , "", "1"));
        mList.add(new StartActivityDao(ContextCompat.getString(getBaseContext() , R.string.update_theme ) , "", "2"));

        StartActivityRecyclerAdapter.setAdapterData(recyclerView, RecyclerView.VERTICAL, mList, new StartActivityRecyclerAdapter.OnItemClickListener<StartActivityDao>() {
            @Override
            public void onItemClick(View itemView, int position, StartActivityDao data) {
                String arouter = data.getArouter();
                if ("1".equals(arouter)) {
                    startActivity(new Intent(MainActivityTrae.this, InternationalActivity.class));
                } else if ("2".equals(arouter)) {
                    startActivity(new Intent(MainActivityTrae.this, ThemeColorActivity.class));
                }
            }
        });
    }
}
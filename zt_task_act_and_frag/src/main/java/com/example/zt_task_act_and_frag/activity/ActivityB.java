package com.example.zt_task_act_and_frag.activity;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zt_task_act_and_frag.R;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Activity B");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // This will effectively finish ActivityB and return to the previous activity in the stack,
            // which should be MainActivity showing DiscoverFragment (a).
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Or, you can override onBackPressed for more custom behavior if needed
    // @Override
    // public void onBackPressed() {
    //     super.onBackPressed(); // Default behavior finishes the activity
    // }
}
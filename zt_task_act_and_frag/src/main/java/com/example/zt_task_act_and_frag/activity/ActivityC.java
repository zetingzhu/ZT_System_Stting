package com.example.zt_task_act_and_frag.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zt_task_act_and_frag.R;

public class ActivityC extends AppCompatActivity {

    private String originFragmentTag; // To know where ActivityC was launched from

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Activity C");
        }

        originFragmentTag = getIntent().getStringExtra("origin");

        Button btnGoToMyFragment = findViewById(R.id.btn_go_to_my_fragment_c);
        btnGoToMyFragment.setOnClickListener(v -> {
            // Navigate to MainActivity and select the 'My' tab (Fragment c)
            // This simulates jumping to the 'My' tab from Activity C.
            Intent intent = new Intent(ActivityC.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("navigateTo", "MyFragment"); // Custom flag to select tab
            startActivity(intent);
            // Potentially finish ActivityC if it should not remain in the back stack after this jump.
            // finish(); 
        });

        Button btnBackToTradeFragmentB = findViewById(R.id.btn_back_to_trade_fragment_b);
        btnBackToTradeFragmentB.setOnClickListener(v -> {
            // This button simulates a back press that should lead to TradeFragment (b)
            // This implies ActivityC should finish and MainActivity (with TradeFragment) should be at the top.
            finish(); // Finishes ActivityC, returning to MainActivity which should show TradeFragment
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Standard back press from ActivityC should return to TradeFragment (b)
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Handle navigation if MainActivity sends an intent to switch tabs
        // This part is more relevant in MainActivity to handle the "navigateTo" extra.
    }

    // The requirement "c 有个按钮返回时候需要返回到 C" is handled by:
    // 1. MyFragment (tab c) in MainActivity having a button.
    // 2. That button in MyFragment, when clicked, should ideally bring ActivityC to the foreground if it exists.
    // This is complex with standard navigation. A simpler interpretation is that if MyFragment is shown
    // *because* of ActivityC (e.g., ActivityC launched a view that *is* MyFragment), then back from that view returns to C.
    // However, the prompt implies MyFragment is a main tab.
    // The current implementation in MyFragment for btn_back_to_c_from_my attempts to finish if it's not MainActivity,
    // which would work if ActivityC launched a temporary Activity hosting MyFragment.
    // For the main tab scenario, bringing ActivityC to front from MyFragment (tab) in MainActivity is non-trivial.
    // One way is to use `startActivity(new Intent(this, ActivityC.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));`
    // from MyFragment's button, but this assumes ActivityC is in the stack.
}
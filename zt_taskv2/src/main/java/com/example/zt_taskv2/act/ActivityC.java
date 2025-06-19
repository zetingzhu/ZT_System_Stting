package com.example.zt_taskv2.act;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zt_taskv2.R;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        Button btnGoToA = findViewById(R.id.btn_go_to_a_from_a);
        btnGoToA.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityC.this, MainActivity.class);
            intent.putExtra("navigateTo", MainActivity.TAG_DISCOVER);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish(); // Finish ActivityB
        });

        Button btnGoToB = findViewById(R.id.btn_go_to_a_from_b);
        btnGoToB.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityC.this, MainActivity.class);
            intent.putExtra("navigateTo", MainActivity.TAG_TRADE);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish(); // Finish ActivityB
        });
        Button btnGoToC = findViewById(R.id.btn_go_to_a_from_c);
        btnGoToC.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityC.this, MainActivity.class);
            intent.putExtra("navigateTo", MainActivity.TAG_MY);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish(); // Finish ActivityB
        });
    }

    @Override
    public void onBackPressed() {
        // Ensure back press also navigates to MainActivity with TradeFragment
        Intent intent = new Intent(ActivityC.this, MainActivity.class);
        intent.putExtra("navigateTo", MainActivity.TAG_TRADE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        super.onBackPressed(); // This will finish ActivityC
    }
}
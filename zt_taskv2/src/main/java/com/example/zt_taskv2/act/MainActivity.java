package com.example.zt_taskv2.act;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.zt_taskv2.R;
import com.example.zt_taskv2.frag.DiscoverFragment;
import com.example.zt_taskv2.frag.MyFragment;
import com.example.zt_taskv2.frag.TradeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    public static final String TAG_DISCOVER = "DiscoverFragment";
    public static final String TAG_TRADE = "TradeFragment";
    public static final String TAG_MY = "MyFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            String tag = "";
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_discover) {
                selectedFragment = new DiscoverFragment();
                tag = TAG_DISCOVER;
            } else if (itemId == R.id.navigation_trade) {
                selectedFragment = new TradeFragment();
                tag = TAG_TRADE;
            } else if (itemId == R.id.navigation_my) {
                selectedFragment = new MyFragment();
                tag = TAG_MY;
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment, tag);
            }
            return true;
        });

        if (savedInstanceState == null) {
            handleIntentNavigation(getIntent());
        } 

        // Set default fragment if no specific navigation target and no existing fragment
        if (getCurrentFragment() == null && (getIntent() == null || getIntent().getStringExtra("navigateTo") == null)) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_discover);
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        // Do not add to back stack for main navigation fragments
        transaction.commit();
    }

    public Fragment getCurrentFragment() {
        return fragmentManager.findFragmentById(R.id.fragment_container);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); // Update the activity's intent
        handleIntentNavigation(intent);
    }

    private void handleIntentNavigation(Intent intent) {
        if (intent != null && intent.hasExtra("navigateTo")) {
            String targetTag = intent.getStringExtra("navigateTo");
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            if (TAG_MY.equals(targetTag)) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_my);
            } else if (TAG_DISCOVER.equals(targetTag)) {
                 bottomNavigationView.setSelectedItemId(R.id.navigation_discover);
            } else if (TAG_TRADE.equals(targetTag)) {
                 bottomNavigationView.setSelectedItemId(R.id.navigation_trade);
            }
            // Clear the navigation extra to prevent re-navigation on configuration change
            intent.removeExtra("navigateTo"); 
        }
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getCurrentFragment();
        // If MyFragment is visible and MainActivity was launched from ActivityC,
        // finishing MainActivity should return to ActivityC.
        if (currentFragment instanceof MyFragment) {
            // Check if MainActivity was started by ActivityC for the specific scenario
            // This might require passing an extra in the intent from ActivityC
            // For now, we assume if MyFragment is visible, back press should finish MainActivity.
            finish(); 
        } else {
            super.onBackPressed();
        }
    }
}
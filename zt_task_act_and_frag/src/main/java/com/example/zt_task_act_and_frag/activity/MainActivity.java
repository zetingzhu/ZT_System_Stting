package com.example.zt_task_act_and_frag.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.zt_task_act_and_frag.fragment.DiscoverFragment;
import com.example.zt_task_act_and_frag.fragment.MyFragment;
import com.example.zt_task_act_and_frag.R;
import com.example.zt_task_act_and_frag.fragment.TradeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

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
                tag = "DiscoverFragment";
            } else if (itemId == R.id.navigation_trade) {
                selectedFragment = new TradeFragment();
                tag = "TradeFragment";
            } else if (itemId == R.id.navigation_my) {
                selectedFragment = new MyFragment();
                tag = "MyFragment";
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment, tag);
            }
            return true;
        });

        // Load the default fragment
        if (savedInstanceState == null) {
            handleIntentNavigation(getIntent()); // Handle initial intent
        } else {
            // If there's a saved instance state, let the system restore the fragment
            // Or, if we need to force a tab based on a new intent after recreation, handle it here.
            // For simplicity, we assume normal restoration or new intent handling below covers it.
        }

        // Set default if no specific navigation is requested by intent
        if (getCurrentFragment() == null && (getIntent().getStringExtra("navigateTo") == null)) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_discover);
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        // It's generally not recommended to add tab fragments to the back stack.
        // transaction.addToBackStack(null); 
        transaction.commit();
    }

    // Helper method to get the current fragment
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
            if (MyFragment.TAG.equals(targetTag)) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_my);
            } else if ("DiscoverFragment".equals(targetTag)) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_discover);
            } else if ("TradeFragment".equals(targetTag)) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_trade);
            }
            // Clear the extra to prevent re-navigation on configuration change if not desired
            intent.removeExtra("navigateTo");
        }
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof MyFragment) {
            // If the current fragment is MyFragment, finish MainActivity to go back to ActivityC
            finish();
        } else {
            // Otherwise, perform the default back action (e.g., switch to previous fragment or exit app)
            super.onBackPressed();
        }
    }
}
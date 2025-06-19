package com.example.zt_taskv1;

import android.content.Context;

public class NavigatorImpl implements Navigator {

    private MainActivity mainActivity;

    public NavigatorImpl(Context context) {
        this.mainActivity = (MainActivity) context;
    }

    @Override
    public void navigateToActivity(Class<?> activityClass) {

    }

    @Override
    public void navigateToFragment(int fragmentIndex) {
        mainActivity.switchFragment(mainActivity.getCurrentTabIndex(), fragmentIndex);
    }
}
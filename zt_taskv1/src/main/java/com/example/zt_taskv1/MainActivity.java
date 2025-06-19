package com.example.zt_taskv1;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Navigator {
    private static final String TAG = "MainActivity";
    
    // 底部导航栏对应的Fragment
    private Fragment[] fragments = new Fragment[3];
    
    // 当前显示的Fragment索引
    protected int currentTabIndex = -1;
    
    // 用于管理Activity和Fragment之间的导航
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 初始化导航服务
        navigator = new NavigatorImpl(this);
        
        // 设置ContentView
        setContentView(R.layout.activity_main);
        
        // 初始化Fragment
        initFragments();
        
        // 设置底部导航栏点击事件
        setupBottomNavigation();
        
        // 如果是通过Intent启动且包含fragment参数，则直接跳转到指定fragment
        if (savedInstanceState == null && getIntent() != null && getIntent().hasExtra("fragment")) {
            int fragmentIndex = getIntent().getIntExtra("fragment", 0);
            switchFragment(currentTabIndex, fragmentIndex);
        }
    }
    
    private void initFragments() {
        // 初始化发现Fragment
        fragments[0] = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragments[0] == null) {
            fragments[0] = new DiscoveryFragment();
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragments[0], "DiscoveryFragment")
                .commitNow();
        }
        
        // 初始化交易Fragment
        fragments[1] = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragments[1] == null) {
            fragments[1] = new TransactionFragment();
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragments[1], "TransactionFragment")
                .commitNow();
        }
        
        // 初始化我的Fragment
        fragments[2] = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragments[2] == null) {
            fragments[2] = new ProfileFragment();
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragments[2], "ProfileFragment")
                .commitNow();
        }
    }
    
    private void setupBottomNavigation() {
        // 设置底部导航栏点击事件
        findViewById(R.id.tab_discovery).setOnClickListener(v -> switchFragment(currentTabIndex, 0));
        findViewById(R.id.tab_transaction).setOnClickListener(v -> switchFragment(currentTabIndex, 1));
        findViewById(R.id.tab_profile).setOnClickListener(v -> switchFragment(currentTabIndex, 2));
    }
    
    protected void switchFragment(int lastindex, int index) {
        if (lastindex == index) {
            return;
        }
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        // 隐藏上一个Fragment
        if (lastindex >= 0 && lastindex < fragments.length) {
            transaction.hide(fragments[lastindex]);
        }
        
        // 显示当前Fragment
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.container, fragments[index], "Fragment" + index);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
        
        currentTabIndex = index;
    }
    
    // 添加 getCurrentTabIndex() 方法以供 NavigatorImpl 使用
    public int getCurrentTabIndex() {
        return currentTabIndex;
    }

    @Override
    public void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        // 添加导航来源信息
        intent.putExtra("source_fragment", currentTabIndex);
        startActivity(intent);
    }
    
    @Override
    public void navigateToFragment(int fragmentIndex) {
        // 创建新的Intent来切换Fragment
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", fragmentIndex);
        // 复用已有的任务栈
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    
    @Override
    public void onBackPressed() {
        // 检查当前Fragment是否需要处理返回键
        if (currentTabIndex >= 0 && currentTabIndex < fragments.length) {
            Fragment currentFragment = fragments[currentTabIndex];
            if (currentFragment instanceof BackPressedHandler && ((BackPressedHandler) currentFragment).onBackPressed()) {
                return; // Fragment已经处理了返回键
            }
        }
        
        // 调用父类默认处理
        super.onBackPressed();
    }
}

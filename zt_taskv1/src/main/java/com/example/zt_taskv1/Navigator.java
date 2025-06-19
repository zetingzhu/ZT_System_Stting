// 创建新文件，定义导航接口
package com.example.zt_taskv1;

public interface Navigator {
    // 导航到新的Activity
    void navigateToActivity(Class<?> activityClass);
    
    // 导航到特定Fragment
    void navigateToFragment(int fragmentIndex);
}
// 创建新文件，定义Fragment返回键处理接口
package com.example.zt_taskv1;

import androidx.fragment.app.Fragment;

public interface BackPressedHandler {
    boolean onBackPressed();
}
// 创建我的Fragment
package com.example.zt_taskv1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment implements BackPressedHandler {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    
    @Override
    public boolean onBackPressed() {
        // 我的Fragment不需要特殊处理返回键，直接返回即可
        return false;
    }
}
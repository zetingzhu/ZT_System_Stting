// 创建发现Fragment
package com.example.zt_taskv1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DiscoveryFragment extends Fragment implements BackPressedHandler {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        
        // 示例按钮：启动Activity B
        Button button = view.findViewById(R.id.btn_launch_activity_b);
        if (button != null) {
            button.setOnClickListener(v -> {
                if (getActivity() instanceof Navigator) {
                    ((Navigator) getActivity()).navigateToActivity(ActivityB.class);
                }
            });
        }
        
        return view;
    }
    
    @Override
    public boolean onBackPressed() {
        // 发现Fragment不需要特殊处理返回键
        return false;
    }
}
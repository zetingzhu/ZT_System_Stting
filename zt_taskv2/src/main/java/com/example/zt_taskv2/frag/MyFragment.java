package com.example.zt_taskv2.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zt_taskv2.R;
import com.example.zt_taskv2.act.ActivityB;
import com.example.zt_taskv2.act.ActivityC;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        Button btnBackToC = view.findViewById(R.id.btn_back_to_c_from_my);
        btnBackToC.setOnClickListener(v -> {
            // This will finish MainActivity and return to ActivityC if ActivityC started MainActivity
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        Button btnToActivityB = view.findViewById(R.id.btn_to_activity_b);
        btnToActivityB.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ActivityB.class);
            startActivity(intent);
        });
        Button btnToActivityC = view.findViewById(R.id.btn_to_activity_c);
        btnToActivityC.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ActivityC.class);
            startActivity(intent);
        });

        return view;
    }
}
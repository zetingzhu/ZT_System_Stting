package com.example.zt_task_act_and_frag.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zt_task_act_and_frag.R;
import com.example.zt_task_act_and_frag.activity.ActivityC;

public class MyFragment extends Fragment {

    public static final String TAG = "MyFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        Button btnBackToC = view.findViewById(R.id.btn_back_to_c_from_my);
        btnBackToC.setOnClickListener(v -> {
            // Finish MainActivity to go back to ActivityC, assuming ActivityC started MainActivity.
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        return view;
    }
}
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

public class TradeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade, container, false);

        Button btnOpenActivityC = view.findViewById(R.id.btn_open_activity_c);
        btnOpenActivityC.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ActivityC.class);
            intent.putExtra("origin", "TradeFragment"); // Mark origin for back navigation
            startActivity(intent);
        });

        return view;
    }
}
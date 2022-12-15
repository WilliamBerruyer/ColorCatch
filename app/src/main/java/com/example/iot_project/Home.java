package com.example.iot_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Home extends Fragment implements View.OnClickListener {

    private LinearLayout colorClicked;

    ColorDetails colorDetails = new ColorDetails();

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        colorClicked = (LinearLayout) root.findViewById(R.id.colorLayout);
        colorClicked.setOnClickListener((View.OnClickListener) this);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.colorLayout:
                getParentFragmentManager().beginTransaction().replace(R.id.container, colorDetails).addToBackStack(null).commit();
                break;
        }
    }
}
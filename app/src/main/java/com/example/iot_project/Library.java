package com.example.iot_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Library extends Fragment implements View.OnClickListener {


    private LinearLayout palettes;
    private LinearLayout likes;

    Palettes paletteFragment = new Palettes();
    ColorLiked colorLikedFragment = new ColorLiked();

    public Library() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_library, container, false);
        palettes = root.findViewById(R.id.horizontalLayoutPalettes);
        likes = root.findViewById(R.id.horizontalLayoutLikes);
        palettes.setOnClickListener(this);
        likes.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.horizontalLayoutPalettes:
                getParentFragmentManager().beginTransaction().replace(R.id.container, paletteFragment).addToBackStack(null).commit();
                break;
            case R.id.horizontalLayoutLikes:
                getParentFragmentManager().beginTransaction().replace(R.id.container, colorLikedFragment).addToBackStack(null).commit();
                break;
        }

    }
}
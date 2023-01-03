package com.example.iot_project;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Home extends Fragment implements ColorRVAdapter.ItemClickListener {

    private DBHandler dbHandler;

    private ArrayList<ColorModal> colorModalArrayList;
    private ColorRVAdapter colorRVAdapter;
    private RecyclerView colorRV;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        // initializing our all variables.
        colorModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());
        colorModalArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        colorRVAdapter = new ColorRVAdapter(colorModalArrayList, getActivity());
        colorRV = root.findViewById(R.id.idRVColors);

        // setting layout manager for our recycler view.
        colorRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        // setting our adapter to recycler view.
        colorRV.setAdapter(colorRVAdapter);

        colorRV.setItemAnimator(new DefaultItemAnimator());
        colorRVAdapter.addItemClickListener(this);

        //Layout to call to add color scanned items
        //colorList = (LinearLayout) root.findViewById(R.id.linearLayoutColorScannedList);

        if (getArguments() != null) {
            int r1 = getArguments().getInt("key");
        }

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onItemClick(int position) {
        System.out.println("Color clicked " + position);
        ColorItem colorItem = dbHandler.getSingleDataInfo(position + 1);

        //get a string containing the hex value of the color clicked
        String hexColor = colorItem.getHex();

        String rgbColor = colorItem.getRgb();

        String hsvColor = colorItem.getHsv();

        String cmykColor = colorItem.getCmyk();

        //Create the list of arguments to give to colorDetails
        Bundle args = new Bundle();
        args.putString("color", hexColor);
        args.putString("name", colorItem.getName());
        args.putString("hexValue", hexColor);
        args.putString("rgbValue", rgbColor);
        args.putString("hsvValue", hsvColor);
        args.putString("cmykValue", cmykColor);

        ColorDetails colorD = new ColorDetails();
        colorD.setArguments(args);

        getParentFragmentManager().beginTransaction().replace(R.id.container, colorD).addToBackStack(null).commit();
    }
}
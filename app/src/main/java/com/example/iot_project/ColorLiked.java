package com.example.iot_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ColorLiked extends Fragment implements ColorRVAdapter.ItemClickListener {

    private DBHandler dbHandler;

    private ArrayList<ColorModal> colorModalArrayList;
    private ColorRVAdapter colorRVAdapter;
    private RecyclerView colorRV;

    public ColorLiked() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_color_liked, container, false);

        // initializing variables.
        colorModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());
        colorModalArrayList = dbHandler.readColorsLiked();

        // on below line passing our array lost to our adapter class.
        colorRVAdapter = new ColorRVAdapter(colorModalArrayList, getActivity());
        colorRV = root.findViewById(R.id.idRVColorsLiked);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        // setting layout manager for our recycler view.
        colorRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        colorRV.setAdapter(colorRVAdapter);

        colorRV.setItemAnimator(new DefaultItemAnimator());

        //allow to click on the elements from the recycler view
        colorRVAdapter.addItemClickListener(this);
        colorRVAdapter.addLikeClickListener(this);

        colorRV.smoothScrollToPosition(colorModalArrayList.size());

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onItemClick(int position) {

        ColorModal modal = colorModalArrayList.get(position);

        //Create the list of arguments to give to colorDetails
        Bundle args = new Bundle();
        args.putString("id", String.valueOf(modal.getId()));
        args.putString("color", modal.getColorHex());
        args.putString("name", modal.getColorName());
        args.putString("hexValue", modal.getColorHex());
        args.putString("rgbValue", modal.getColorRgb());
        args.putString("hsvValue", modal.getColorHsv());
        args.putString("cmykValue", modal.getColorCmyk());
        args.putString("liked", String.valueOf(modal.getLiked()));
        args.putString("position", String.valueOf(position));

        ColorDetails colorD = new ColorDetails();
        colorD.setArguments(args);

        //call the coloDetails fragment on click of the item in recycler view
        getParentFragmentManager().beginTransaction().replace(R.id.container, colorD).addToBackStack(null).commit();

    }

    @Override
    public void onLikeClick(int position) {
        ColorModal modal = colorModalArrayList.get(position);
        dbHandler.addColorLikeToDB(modal.getId());
    }
}
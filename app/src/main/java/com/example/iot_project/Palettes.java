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

public class Palettes extends Fragment implements PaletteRVAdapter.ItemClickListener{

    private DBHandler dbHandler;
    private ArrayList<PaletteModal> paletteModalArrayList;
    private PaletteRVAdapter paletteRVAdapter;
    private RecyclerView paletteRV;

    public Palettes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_palettes, container, false);

        paletteModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());
        paletteModalArrayList = dbHandler.readPalettes();

        // on below line passing our array lost to our adapter class.
        paletteRVAdapter = new PaletteRVAdapter(paletteModalArrayList, getActivity());
        paletteRV = root.findViewById(R.id.idRVPalettesLiked);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        // setting layout manager for our recycler view.
        paletteRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        paletteRV.setAdapter(paletteRVAdapter);

        paletteRV.setItemAnimator(new DefaultItemAnimator());

        paletteRVAdapter.addItemClickListener(this);
        paletteRVAdapter.addLikeClickListener(this);

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onItemClick(int position) {
        PaletteModal modal = paletteModalArrayList.get(position);
        //Create the list of arguments to give to colorDetails
        Bundle args = new Bundle();

        args.putString("color1", modal.getHexOriginal());
        args.putString("color2", modal.getC1());
        args.putString("color3", modal.getC2());
        args.putString("color4", modal.getC3());
        args.putString("color5", modal.getC4());

        PaletteDetails paletteDetails = new PaletteDetails();
        paletteDetails.setArguments(args);

        getParentFragmentManager().beginTransaction().replace(R.id.container, paletteDetails).addToBackStack(null).commit();

    }

    @Override
    public void onLikeClick(int position) {
        dbHandler.addPaletteLikeToDB(position + 1);
    }
}
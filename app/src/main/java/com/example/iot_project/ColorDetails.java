package com.example.iot_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorDetails extends Fragment implements PaletteRVAdapter.ItemClickListener {

    //access to db
    private DBHandler dbHandler;
    private ArrayList<PaletteModal> paletteModalArrayList;
    private PaletteRVAdapter paletteRVAdapter;
    private RecyclerView paletteRV;

    public ColorDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_color_details, container, false);

        TextView colorName = root.findViewById(R.id.colorNameColorDetails);
        CardView colorRec = root.findViewById(R.id.colorRectangle);
        TextView hexText = root.findViewById(R.id.hexTextValue);
        TextView rgbText = root.findViewById(R.id.rgbTextValue);
        TextView hsbText = root.findViewById(R.id.hsbTextValue);
        TextView cmykText = root.findViewById(R.id.cmykTextValue);
        ImageButton likeButton = root.findViewById(R.id.likeButton);

        String hexTextString = "";

        //get the arguments from home page
        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            String id = bundle.getString("id");
            String colorHex = bundle.getString("color");
            String color_name = bundle.getString("name");
            hexTextString = bundle.getString("hexValue");
            String rgbTextString = bundle.getString("rgbValue");
            String hsbTextString = bundle.getString("hsvValue");
            String cmykTextString = bundle.getString("cmykValue");
            String likedString = bundle.getString("liked");

            int col = Color.parseColor(colorHex);

            colorRec.setCardBackgroundColor(col);
            colorName.setText(color_name);
            hexText.setText(hexTextString);
            rgbText.setText(rgbTextString);
            hsbText.setText(hsbTextString);
            cmykText.setText(cmykTextString);

            //update the like button for the color
            if (likedString.equals("1")) {
                likeButton.setImageResource(R.drawable.like_full);
                likeButton.setTag("like_full");
            } else if (likedString.equals("0")) {
                likeButton.setImageResource(R.drawable.ic_action_likebutton);
                likeButton.setTag("ic_action_likebutton");
            }

            //on click method for the like button for the color
            likeButton.setOnClickListener(view -> {
                String tag = (String) likeButton.getTag();
                int idC = Integer.parseInt(id);
                if (tag.equals("ic_action_likebutton")) {
                    likeButton.setImageResource(R.drawable.like_full);
                    likeButton.setTag("like_full");
                } else if (tag.equals("like_full")) {
                    likeButton.setImageResource(R.drawable.ic_action_likebutton);
                    likeButton.setTag("ic_action_likebutton");
                }
                dbHandler.addColorLikeToDB(idC);
            });
        }

        //init variables
        paletteModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());
        paletteModalArrayList = dbHandler.readSpecificPalettes(hexTextString);

        // on below line passing our array lost to our adapter class.
        paletteRVAdapter = new PaletteRVAdapter(paletteModalArrayList, getActivity());
        paletteRV = root.findViewById(R.id.idRVPalettes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        // setting layout manager for our recycler view.
        paletteRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        paletteRV.setAdapter(paletteRVAdapter);

        paletteRV.setItemAnimator(new DefaultItemAnimator());

        //add click listener to item and like buttons to items from the recycler view
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

        String liked = String.valueOf(modal.getLiked());

        args.putString("id", String.valueOf(modal.getId()));
        args.putString("color1", modal.getHexOriginal());
        args.putString("color2", modal.getC1());
        args.putString("color3", modal.getC2());
        args.putString("color4", modal.getC3());
        args.putString("color5", modal.getC4());
        args.putString("liked", liked);

        PaletteDetails paletteDetails = new PaletteDetails();
        paletteDetails.setArguments(args);

        //call the palette details fragment on item click in the recycler view
        getParentFragmentManager().beginTransaction().replace(R.id.container, paletteDetails).addToBackStack(null).commit();
    }

    @Override
    public void onLikeClick(int position) {
        PaletteModal modal = paletteModalArrayList.get(position);

        //add the palette to the like db on like button click
        dbHandler.addPaletteLikeToDB(modal.getId());
    }
}
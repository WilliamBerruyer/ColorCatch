package com.example.iot_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class PaletteDetails extends Fragment implements PaletteRVAdapter.ItemClickListener {

    private DBHandler dbHandler;

    public PaletteDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_palette_details, container, false);

        CardView c1 = root.findViewById(R.id.paletteColorOne);
        CardView c2 = root.findViewById(R.id.paletteColorTwo);
        CardView c3 = root.findViewById(R.id.paletteColorThree);
        CardView c4 = root.findViewById(R.id.paletteColorFour);
        CardView c5 = root.findViewById(R.id.paletteColorFive);

        TextView t1 = root.findViewById(R.id.colorOneText);
        TextView t2 = root.findViewById(R.id.colorTwoText);
        TextView t3 = root.findViewById(R.id.colorThreeText);
        TextView t4 = root.findViewById(R.id.colorFourText);
        TextView t5 = root.findViewById(R.id.colorFiveText);

        ImageButton likeButton = root.findViewById(R.id.likeButtonPalette);

        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            String color1 = bundle.getString("color1");
            String color2 = bundle.getString("color2");
            String color3 = bundle.getString("color3");
            String color4 = bundle.getString("color4");
            String color5 = bundle.getString("color5");
            String likedString = bundle.getString("liked");
            String position = bundle.getString("position");

            c1.setCardBackgroundColor(Color.parseColor(color1));
            c2.setCardBackgroundColor(Color.parseColor(color2));
            c3.setCardBackgroundColor(Color.parseColor(color3));
            c4.setCardBackgroundColor(Color.parseColor(color4));
            c5.setCardBackgroundColor(Color.parseColor(color5));

            t1.setText(color1);
            t2.setText(color2);
            t3.setText(color3);
            t4.setText(color4);
            t5.setText(color5);

            setCorrectFontColor(color1, t1);
            setCorrectFontColor(color2, t2);
            setCorrectFontColor(color3, t3);
            setCorrectFontColor(color4, t4);
            setCorrectFontColor(color5, t5);

            // handle like button behaviour
            if (likedString.equals("1")) {
                likeButton.setImageResource(R.drawable.like_full);
                likeButton.setTag("like_full");
            } else if (likedString.equals("0")) {
                likeButton.setImageResource(R.drawable.ic_action_likebutton);
                likeButton.setTag("ic_action_likebutton");
            }

            likeButton.setOnClickListener(view -> {
                String tag = (String) likeButton.getTag();
                if (tag.equals("ic_action_likebutton")) {
                    likeButton.setImageResource(R.drawable.like_full);
                    likeButton.setTag("like_full");
                } else if (tag.equals("like_full")) {
                    likeButton.setImageResource(R.drawable.ic_action_likebutton);
                    likeButton.setTag("ic_action_likebutton");
                }
                dbHandler.addPaletteLikeToDB(color1, color2, color3, color4, color5);
            });

            dbHandler = new DBHandler(getActivity());

        }

        // Inflate the layout for this fragment
        return root;
    }

    /**
     * Set the correct font color depending the background the font is displayed on
     */
    public void setCorrectFontColor(String bgColor, TextView t) {
        int bg = Color.parseColor(bgColor);
        int c = Color.parseColor("#7e7f7f");

        if (bg > c) {
            t.setTextColor(Color.BLACK);
        } else {
            t.setTextColor(Color.WHITE);
        }
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLikeClick(int position) {

    }
}
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

public class Home extends Fragment implements ColorRVAdapter.ItemClickListener{

    private LinearLayout colorClicked;
    private LinearLayout colorList;


    ColorUtils colorFinder = new ColorUtils();

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
        dbHandler =  new DBHandler(getActivity());
        colorModalArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        colorRVAdapter = new ColorRVAdapter(colorModalArrayList, getActivity());
        colorRV = (RecyclerView) root.findViewById(R.id.idRVColors);

        // setting layout manager for our recycler view.
        colorRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        // setting our adapter to recycler view.
        colorRV.setAdapter(colorRVAdapter);

        colorRV.setItemAnimator(new DefaultItemAnimator());
        colorRVAdapter.addItemClickListener(this);

        //Layout to call to add color scanned items
        //colorList = (LinearLayout) root.findViewById(R.id.linearLayoutColorScannedList);

        if(getArguments() != null){
            int r1 = getArguments().getInt("key");
        }

        //create 10 colors randomly for test purposes
        /*for (int i = 1; i <= 10; i++) {
            View child = getLayoutInflater().inflate(R.layout.color_card_template, null);
            colorList.addView(child);

            //get the color layout global element
            LinearLayout color = (LinearLayout) root.findViewWithTag("colorLayout");
            //redefine its tag to make it unique
            String colorName = "colorLayout" + i;
            color.setTag(colorName);
            LinearLayout colorUpdated = (LinearLayout) root.findViewWithTag(colorName);

            //listener for to redirect to color details
            colorUpdated.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    //get the num of the layout clicked
                    String layoutNum = view.getTag().toString();
                    String num = layoutNum.replaceAll("[^0-9]", "");

                    CardView colorRec = view.findViewWithTag("colorRectangle"+num);
                    TextView colorNameTextUpdated = view.findViewWithTag("colorName"+num);

                    ColorStateList viewColor = colorRec.getCardBackgroundColor();
                    int colorId = viewColor.getDefaultColor();

                    //get a string containing the hex value of the color clicked
                    String hexColor = String.format("#%06X", (0xFFFFFF & colorId));

                    String rgbColor = colorFinder.colorToRGB(hexColor);

                    String hsvColor = colorFinder.colorToHsb(hexColor);

                    String cmykColor = colorFinder.rgbToCmyk(hexColor);

                    //Create the list of arguments to give to colorDetails
                    Bundle args = new Bundle();
                    args.putString("color", hexColor);
                    args.putString("name", String.valueOf(colorNameTextUpdated.getText()));
                    args.putString("hexValue", hexColor);
                    args.putString("rgbValue", rgbColor);
                    args.putString("hsvValue", hsvColor);
                    args.putString("cmykValue", cmykColor);

                    ColorDetails colorD = new ColorDetails();
                    colorD.setArguments(args);

                    getParentFragmentManager().beginTransaction().replace(R.id.container, colorD).addToBackStack(null).commit();
                }
            });

            //get the color rectangle of the layout
            CardView colorRec = (CardView) root.findViewWithTag("colorRectangle");
            //redefine its tag to make it unique
            String colorRecName = "colorRectangle" + i;
            colorRec.setTag(colorRecName);
            CardView colorRecUpdated = (CardView) root.findViewWithTag(colorRecName);

            //generate random r g b colors for test purposes

            Random Red = new Random();
            int r = 0;

            Random G = new Random();
            int g = 0;

            Random B = new Random();
            int b = 0;


            //update the color of the rectangle
            colorRecUpdated.setCardBackgroundColor(Color.rgb(r,g,b));
            //colorRecUpdated.setBackgroundColor(Color.rgb(r, g, b));

            //get the name of the color
            TextView colorNameText = (TextView) root.findViewWithTag("colorName");
            //redefine its tag to make it unique
            String colorNameTextString = "colorName" + i;
            colorNameText.setTag(colorNameTextString);
            TextView colorNameTextUpdated = (TextView) root.findViewWithTag(colorNameTextString);

            //update the name of the color properly
            colorNameTextUpdated.setText( colorFinder.getNameWithSpaces(colorFinder.getColorNameFromRgb(r, g, b)));
        }*/

        //special empty space to add at the end of scroll views
        //View emptySpace = getLayoutInflater().inflate(R.layout.empty_space_end_scrollview, null);
        //colorList.addView(emptySpace);

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
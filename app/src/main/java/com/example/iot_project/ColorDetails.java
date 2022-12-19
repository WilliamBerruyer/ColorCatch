package com.example.iot_project;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ColorDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColorDetails extends Fragment{

    private static final String PREFS_NAME = "YOUR_TAG";
    private static final String DATA_TAG = "DATA_TAG";
    private static final String data = "oh, what a data!";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //init
    //SharedPreferences mSettings = getContext().getSharedPreferences(PREFS_NAME, 0);
    public ColorDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ColorDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ColorDetails newInstance(String param1, String param2) {
        ColorDetails fragment = new ColorDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        //get the arguments from home page
        Bundle bundle = this.getArguments();
        if(getArguments() != null){

            String colorHex = bundle.getString("color");
            String color_name = bundle.getString("name");
            String hexTextString = bundle.getString("hexValue");
            String rgbTextString = bundle.getString("rgbValue");
            String hsbTextString = bundle.getString("hsvValue");
            String cmykTextString = bundle.getString("cmykValue");

            int col = Color.parseColor(colorHex);

            colorRec.setCardBackgroundColor(col);
            colorName.setText(color_name);
            hexText.setText(hexTextString);
            rgbText.setText(rgbTextString);
            hsbText.setText(hsbTextString);
            cmykText.setText(cmykTextString);

        }

        // Inflate the layout for this fragment
        return root;
    }

}
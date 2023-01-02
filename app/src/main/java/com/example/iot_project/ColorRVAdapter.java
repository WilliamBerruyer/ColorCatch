package com.example.iot_project;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ColorRVAdapter extends RecyclerView.Adapter<ColorRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ColorModal> colorModalArrayList;
    private Context context;

    // constructor
    public ColorRVAdapter(ArrayList<ColorModal> colorModalArrayList, Context context) {
        this.colorModalArrayList = colorModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_card_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ColorModal modal = colorModalArrayList.get(position);
        holder.colorNameTV.setText(modal.getColorName());
        /*holder.colorHexTV.setText(modal.getColorHex());
        holder.colorRgbTV.setText(modal.getColorRgb());
        holder.colorHsvTV.setText(modal.getColorHsv());
        holder.colorCmykTV.setText(modal.getColorCmyk());*/
        holder.colorTimeTV.setText(modal.getColorTime());
        holder.cardColor.setCardBackgroundColor(modal.getColorHexToInt(modal.getColorHex()));
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return colorModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView colorNameTV, colorHexTV, colorRgbTV, colorHsvTV, colorCmykTV, colorTimeTV;
        private CardView cardColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            colorNameTV = itemView.findViewById(R.id.colorName);/*
            colorHexTV = itemView.findViewById(R.id.idTVCourseDescription);
            colorRgbTV = itemView.findViewById(R.id.idTVCourseDuration);
            colorHsvTV = itemView.findViewById(R.id.idTVCourseTracks);
            colorCmykTV = itemView.findViewById(R.id.idTVCourseDuration);*/
            colorTimeTV = itemView.findViewById(R.id.timeOfScan);
            cardColor = itemView.findViewById(R.id.colorRectangle);
        }
    }
}

package com.example.iot_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaletteRVAdapter extends RecyclerView.Adapter<PaletteRVAdapter.ViewHolder> {

    private final ArrayList<PaletteModal> paletteModalArrayList;
    private final Context context;


    public PaletteRVAdapter(ArrayList<PaletteModal> paletteModalArrayList, Context context){
        this.paletteModalArrayList = paletteModalArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.palette_card_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaletteModal modal = paletteModalArrayList.get(position);
        holder.cardColor1.setCardBackgroundColor(modal.getColorHexToInt(modal.getHexOriginal()));
        holder.cardColor2.setCardBackgroundColor(modal.getColorHexToInt(modal.getC1()));
        holder.cardColor3.setCardBackgroundColor(modal.getColorHexToInt(modal.getC2()));
        holder.cardColor4.setCardBackgroundColor(modal.getColorHexToInt(modal.getC3()));
        holder.cardColor5.setCardBackgroundColor(modal.getColorHexToInt(modal.getC4()));
    }

    @Override
    public int getItemCount() {
        return paletteModalArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView paletteNameTV;
        private final CardView cardColor1;
        private final CardView cardColor2;
        private final CardView cardColor3;
        private final CardView cardColor4;
        private final CardView cardColor5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            paletteNameTV = itemView.findViewById(R.id.colorName);
            cardColor1 = itemView.findViewById(R.id.paletteColorOne);
            cardColor2 = itemView.findViewById(R.id.paletteColorTwo);
            cardColor3 = itemView.findViewById(R.id.paletteColorThree);
            cardColor4 = itemView.findViewById(R.id.paletteColorFour);
            cardColor5 = itemView.findViewById(R.id.paletteColorFive);
        }
    }
}

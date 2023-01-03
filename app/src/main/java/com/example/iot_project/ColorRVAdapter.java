package com.example.iot_project;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ColorRVAdapter extends RecyclerView.Adapter<ColorRVAdapter.ViewHolder> {

    // variable for our array list and context
    private final ArrayList<ColorModal> colorModalArrayList;
    private final Context context;
    private ItemClickListener mItemClickListener;


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
        holder.colorTimeTV.setText(modal.getColorTime());
        holder.cardColor.setCardBackgroundColor(modal.getColorHexToInt(modal.getColorHex()));
        holder.colorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        // returning the size of our array list
        return colorModalArrayList.size();
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView colorNameTV;
        private final TextView colorTimeTV;
        private final CardView cardColor;
        private final LinearLayout colorLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            colorNameTV = itemView.findViewById(R.id.colorName);
            colorTimeTV = itemView.findViewById(R.id.timeOfScan);
            cardColor = itemView.findViewById(R.id.colorRectangle);
            colorLayout = itemView.findViewById(R.id.colorLayout);
        }
    }

}
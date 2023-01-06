package com.example.iot_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaletteRVAdapter extends RecyclerView.Adapter<PaletteRVAdapter.ViewHolder> {

    private final ArrayList<PaletteModal> paletteModalArrayList;
    private final Context context;
    private ItemClickListener mItemClickListener, likeClickListener;


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
        holder.paletteLayout.setOnClickListener(view -> {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });

        // Like buttons for items behaviour
        if (modal.getLiked() ==  1) {
            holder.likeButton.setImageResource(R.drawable.like_full);
            holder.likeButton.setTag("like_full");
        }

        holder.likeButton.setOnClickListener(view -> {
            String tag = (String) holder.likeButton.getTag();
            if(tag.equals("ic_action_likebutton")){
                holder.likeButton.setImageResource(R.drawable.like_full);
                holder.likeButton.setTag("like_full");
            } else if (tag.equals("like_full")) {
                holder.likeButton.setImageResource(R.drawable.ic_action_likebutton);
                holder.likeButton.setTag("ic_action_likebutton");
            }

            if (likeClickListener != null) {
                likeClickListener.onLikeClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return paletteModalArrayList.size();
    }

    // Interface for on click events
    public interface ItemClickListener {
        void onItemClick(int position);
        void onLikeClick(int position);
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void addLikeClickListener(ItemClickListener listener) {
        likeClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final CardView cardColor1;
        private final CardView cardColor2;
        private final CardView cardColor3;
        private final CardView cardColor4;
        private final CardView cardColor5;
        private final ImageButton likeButton;

        private final LinearLayout paletteLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            cardColor1 = itemView.findViewById(R.id.paletteColorOne);
            cardColor2 = itemView.findViewById(R.id.paletteColorTwo);
            cardColor3 = itemView.findViewById(R.id.paletteColorThree);
            cardColor4 = itemView.findViewById(R.id.paletteColorFour);
            cardColor5 = itemView.findViewById(R.id.paletteColorFive);
            paletteLayout = itemView.findViewById(R.id.paletteLinearLayoutVertical);
            likeButton = itemView.findViewById(R.id.likeButtonPalette);
        }
    }
}

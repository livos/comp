package com.livos.companionplants.ui.plants.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livos.companionplants.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvPlantName;
    private ImageView ivPlant;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        tvPlantName = itemView.findViewById(R.id.tv_plant_name);
        ivPlant = itemView.findViewById(R.id.iv_plant);
    }

    @Override
    public void onClick(View view) {
    }

    public TextView getTvPlantName() {
        return tvPlantName;
    }

    public ImageView getIvPlant() {
        return ivPlant;
    }
}

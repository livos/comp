package com.livos.companionplants.ui.plants.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.events.PlantSelectedEvent;
import com.livos.companionplants.ui.events.PlantSelectedEventImpl;
import com.livos.companionplants.ui.plants.AssociatedPlant;

import org.greenrobot.eventbus.EventBus;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvPlantName;
    private ImageView ivPlant;


    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        tvPlantName = itemView.findViewById(R.id.tv_plant_name);
        ivPlant = itemView.findViewById(R.id.iv_plant);
    }

    @Override
    public void onClick(View view) {
        PlantSelectedEvent plantSelectedEvent = new PlantSelectedEventImpl();
        plantSelectedEvent.setPlant(((AssociatedPlant)ivPlant.getTag()).getPlant());
        EventBus.getDefault().post(plantSelectedEvent);


    }

    public TextView getTvPlantName() {

        return tvPlantName;
    }

    public ImageView getIvPlant() {

        return ivPlant;
    }
}

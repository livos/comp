package com.livos.companionplants.plants.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.database.PlantAssociation;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.model.PlantDetail;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvPlantName;
    private ImageView ivPlant;
    private PlantDetail plantAssociation;
    private PlantSelectedEvent plantSelectedEvent;

    @Override
    public void onClick(View view) {
        // The img tag contains the id of the associated plant
        plantSelectedEvent.setPlantId(((PlantDetail)ivPlant.getTag()).getPlantId());
        plantSelectedEvent.setPlantName(((PlantDetail)ivPlant.getTag()).getDefinition());
        plantSelectedEvent.setImage(ivPlant.getDrawable());

        EventBus.getDefault().post(plantSelectedEvent);

    }

    public ImageView getIvPlant() {
        return ivPlant;
    }

    public void setIvPlant(ImageView ivPlant) {
        this.ivPlant = ivPlant;
    }

    public PlantDetail getPlantAssociation() {
        return plantAssociation;
    }

    public void setPlantAssociation(PlantDetail plantAssociation) {
        this.plantAssociation = plantAssociation;
    }

    public TextView getTvPlantName() {
        return tvPlantName;
    }

    public void setTvPlantName(TextView tvPlantName) {
        this.tvPlantName = tvPlantName;
    }

    public RecyclerViewHolder(View itemView, PlantSelectedEvent plantSelectedEvent) {
        super(itemView);
        itemView.setOnClickListener(this);
        tvPlantName = itemView.findViewById(R.id.tv_plant_name);
        ivPlant = itemView.findViewById(R.id.iv_plant);
        this.plantSelectedEvent = plantSelectedEvent;
    }
}

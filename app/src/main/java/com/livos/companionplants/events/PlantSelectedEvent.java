package com.livos.companionplants.events;

import android.graphics.drawable.Drawable;

import com.livos.companionplants.data.local.database.model.PlantDefinition;


public interface PlantSelectedEvent {
    Long getPlantId();
    void setPlantId(Long plantId);

    String getPlantName();
    void setPlantName(String plantName);

    Drawable getImage();
    void setImage(Drawable image);
}

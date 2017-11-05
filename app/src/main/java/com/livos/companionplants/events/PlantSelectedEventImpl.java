package com.livos.companionplants.events;

import android.graphics.drawable.Drawable;


/*
Fired when a plant is clicked in the list or in the grid
 */
public class PlantSelectedEventImpl implements PlantSelectedEvent {
    private Long plantId;
    private String plantName;
    private Drawable image;

    public PlantSelectedEventImpl() { }


    @Override
    public Long getPlantId() {
        return plantId;
    }

    @Override
    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    @Override
    public String getPlantName() {
        return plantName;
    }

    @Override
    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    @Override
    public Drawable getImage() {
        return image;
    }

    @Override
    public void setImage(Drawable image) {
        this.image = image;
    }

}

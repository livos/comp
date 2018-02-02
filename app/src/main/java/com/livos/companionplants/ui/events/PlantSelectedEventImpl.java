package com.livos.companionplants.ui.events;

import com.livos.companionplants.data.local.db.model.Plant;

/**
 * Created by laurent on 2/1/18.
 */

public class PlantSelectedEventImpl implements PlantSelectedEvent {
    private Plant plant;

    @Override
    public Plant getPlant() {
        return plant;
    }

    @Override
    public void setPlant(Plant plant) {
        this.plant =  plant;
    }
}

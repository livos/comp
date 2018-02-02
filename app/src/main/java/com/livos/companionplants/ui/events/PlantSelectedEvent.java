package com.livos.companionplants.ui.events;

import com.livos.companionplants.data.local.db.model.Plant;

/**
 * Created by laurent on 2/1/18.
 */

public interface PlantSelectedEvent {
    Plant getPlant();
    void setPlant(Plant plant);
}

package com.livos.companionplants.ui.events;

import com.livos.companionplants.data.local.db.model.Plant;


public interface PlantSelectedEvent {
    Plant getPlant();
    void setPlant(Plant plant);

    int getTabIdx();
    void setTabIdx(int tabIdx);

}

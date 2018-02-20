package com.livos.companionplants.ui.events;

import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.plants.AssociatedPlant;

/**
 * Created by laurent on 2/1/18.
 */

public class PlantSelectedEventImpl implements PlantSelectedEvent {
    private Plant plant;
    private int tabIdx;

    public int getTabIdx() {
        return tabIdx;
    }

    public void setTabIdx(int tabIdx) {
        this.tabIdx = tabIdx;
    }

    @Override
    public Plant getPlant() {
        return plant;
    }

    @Override
    public void setPlant(Plant plant) {
        this.plant =  plant;
    }
}

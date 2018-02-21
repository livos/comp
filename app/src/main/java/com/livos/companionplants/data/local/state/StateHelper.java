package com.livos.companionplants.data.local.state;

import com.livos.companionplants.data.local.db.model.Plant;


public interface StateHelper {
    void setSelectedPlant(Plant selectedPlant);
    Plant getSelectedPlant();
}

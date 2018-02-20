package com.livos.companionplants.data.local.state;

import com.livos.companionplants.data.local.db.model.Plant;

/**
 * Created by laurent on 1/31/18.
 */

public interface StateHelper {
    void setSelectedPlant(Plant selectedPlant);
    Plant getSelectedPlant();
}

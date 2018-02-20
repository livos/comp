package com.livos.companionplants.data.local.state;

import com.livos.companionplants.data.local.db.model.Plant;

import javax.inject.Inject;

/**
 * Created by laurent on 1/31/18.
 */

public class AppStateHelper implements StateHelper {
    private Plant selectedPlant;

    @Inject
    public AppStateHelper() {}

    @Override
    public void setSelectedPlant(Plant selectedPlant) {
        this.selectedPlant = selectedPlant;
    }

    @Override
    public Plant getSelectedPlant() {
        return this.selectedPlant;
    }
}

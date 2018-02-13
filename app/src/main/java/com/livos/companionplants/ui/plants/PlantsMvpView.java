package com.livos.companionplants.ui.plants;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.MvpView;

import java.util.List;

public interface PlantsMvpView extends MvpView {
    //void loadPlants(List<Plant> plants);
    void loadPlants(List<AssociatedPlant> plants);
    void loadPlants(List<AssociatedPlant> plants, boolean all);
}

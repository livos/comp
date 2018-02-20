package com.livos.companionplants.data.local.db;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.ui.plants.AssociatedPlant;

import java.util.List;

public interface DbHelper {
    List<AssociatedPlant> getAllPlants();
    List<PlantDefinition> getAllDefinitions();

    List<AssociatedPlant> getAssociatedPlants(long plantId);
    List<AssociatedPlant> getAssociatedPlantsHelps(long plantId);
    List<AssociatedPlant> getAssociatedPlantsHelpedBy(long plantId);
    List<AssociatedPlant> getAssociatedPlantsAvoid(long plantId);
    List<AssociatedPlant> getAssociatedPlantsNeutral(long plantId);
    List<AssociatedPlant> getAssociatedPlantsByFlag(long plantId, long flagId);

    Plant getPlantById(long plantId);
    Plant getPlantByName(String name);

}

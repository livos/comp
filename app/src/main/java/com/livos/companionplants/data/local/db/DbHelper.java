package com.livos.companionplants.data.local.db;


import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;

import java.util.List;

public interface DbHelper {
    List<Plant> getAllPlants();

    List<PlantDefinition> getAllDefinitions();


}

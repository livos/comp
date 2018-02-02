package com.livos.companionplants.ui.search;


import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.ui.base.MvpView;

import java.util.List;

public interface SearchMvpView extends MvpView {
    void loadDefinitions(List<PlantDefinition> definitions);
    void onSelectedPlantChanged();

}

package com.livos.companionplants.ui.plants;


import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class PlantsPresenter<V extends PlantsMvpView> extends BasePresenter<V>
    implements PlantsMvpPresenter<V> {

    @Inject
    public PlantsPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void onViewPrepared() {
        List<Plant> plants = getDataManager().getAllPlants();
        getMvpView().loadPlants(plants);
    }


}

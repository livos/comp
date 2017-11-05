package com.livos.companionplants.plants;

import android.content.Context;

import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.events.PlantSelectedEventImpl;
import com.livos.companionplants.util.PlantsScope;
import com.livos.companionplants.util.SearchScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlantsModule {

//    @Provides
//    @PlantsScope
//    Context provideContext(Context context)
//    {
//        return  context;
//    }

    @Provides
    @PlantsScope
    PlantsContract.Model providePlantsModel(DatabaseHelper databaseHelper) {
        return  new PlantsModel(databaseHelper);
    }

    @Provides
    @PlantsScope
    PlantSelectedEvent providePlantSelectedEvent() {
        return new PlantSelectedEventImpl();
    }

    @Provides
    @PlantsScope
    PlantsContract.Presenter providePlantsPresenter(PlantsContract.Model plantsModel, PlantSelectedEvent plantSelectedEvent) {
        return new PlantsPresenter(plantsModel, plantSelectedEvent);
    }
}

package com.livos.companionplants.plants;

import android.content.Context;

import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.events.PlantSelectedEvent;
import com.livos.companionplants.events.PlantSelectedEventImpl;
import com.livos.companionplants.util.PerFragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlantsModule {

//    @Provides
//    @PerFragmentScope
//    Context provideContext(Context context)
//    {
//        return  context;
//    }

    @Provides
    @PerFragmentScope
    PlantsContract.Model providePlantsModel(DatabaseHelper databaseHelper) {
        return  new PlantsModel(databaseHelper);
    }

    @Provides
    @PerFragmentScope
    PlantSelectedEvent providePlantSelectedEvent() {
        return new PlantSelectedEventImpl();
    }

    @Provides
    @PerFragmentScope
    PlantsContract.Presenter providePlantsPresenter(PlantsContract.Model plantsModel, PlantSelectedEvent plantSelectedEvent) {
        return new PlantsPresenter(plantsModel, plantSelectedEvent);
    }
}

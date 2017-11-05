package com.livos.companionplants.application;

import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.util.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component (modules =  {PlantsApplicationModule.class})
public interface PlantsApplicationComponent {
    DatabaseHelper databaseHelper();
}

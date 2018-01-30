package com.livos.companionplants.di.component;

import android.app.Application;
import android.content.Context;

import com.livos.companionplants.PlantsApp;
import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.di.module.ApplicationModule;
import com.livos.companionplants.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PlantsApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}

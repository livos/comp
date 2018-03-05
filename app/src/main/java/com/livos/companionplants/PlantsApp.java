package com.livos.companionplants;


import android.app.Application;

import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.di.component.ApplicationComponent;
import com.livos.companionplants.di.component.DaggerApplicationComponent;
import com.livos.companionplants.di.module.ApplicationModule;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class PlantsApp  extends Application {

    @Inject
    DataManager dataManager;

    @Inject
    CalligraphyConfig calligraphyConfig;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        initRealmConfiguration();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);
        CalligraphyConfig.initDefault(calligraphyConfig);

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        applicationComponent = applicationComponent;
    }

    private void initRealmConfiguration() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("plants.realm")
                .schemaVersion(1)
                .assetFile("database/plants.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }


}

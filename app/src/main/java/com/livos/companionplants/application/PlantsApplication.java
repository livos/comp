package com.livos.companionplants.application;


import android.app.Application;
import android.os.Build;

import com.facebook.stetho.Stetho;
import com.livos.companionplants.BuildConfig;
import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.main.DaggerMainComponent;
import com.livos.companionplants.main.MainComponent;
import com.livos.companionplants.main.MainModule;
import com.livos.companionplants.plants.DaggerPlantsComponent;
import com.livos.companionplants.plants.PlantsComponent;
import com.livos.companionplants.plants.PlantsModule;

import javax.inject.Inject;


public class PlantsApplication extends Application {
    private PlantsApplicationComponent component;
    private PlantsComponent plantsComponent;
    private MainComponent mainComponent;

    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();



        component = DaggerPlantsApplicationComponent.builder()
                .plantsApplicationModule(new PlantsApplicationModule(this))
                .build();

        plantsComponent = DaggerPlantsComponent.builder()
                .plantsApplicationComponent(component)
                .plantsModule(new PlantsModule())
                .build();

        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build();


        if (BuildConfig.DEBUG) {
            if (!isRoboUnitTest()) {
                initializeStetho();
            }
        }

    }

    private static boolean isRoboUnitTest() {
        return "robolectric".equals(Build.FINGERPRINT);
    }

    private void initializeStetho() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        // Enable command line interface
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    public DaoSession getDaoSession() {
        if (BuildConfig.DEBUG && daoSession != null) {
            daoSession.clear();
        }

        return daoSession;
    }

    public  PlantsApplicationComponent getComponent() {

        return component;
    }

    public PlantsComponent getPlantsComponent() {

        return plantsComponent;
    }

    public MainComponent getMainComponent() {
        return  mainComponent;
    }

}

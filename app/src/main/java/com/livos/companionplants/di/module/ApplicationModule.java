package com.livos.companionplants.di.module;

import android.app.Application;
import android.content.Context;

import com.livos.companionplants.R;
import com.livos.companionplants.data.AppDataManager;
import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.AppDbHelper;
import com.livos.companionplants.data.local.db.DbHelper;
import com.livos.companionplants.data.local.prefs.AppPreferencesHelper;
import com.livos.companionplants.data.local.prefs.PreferencesHelper;
import com.livos.companionplants.di.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("plants.realm")
                .schemaVersion(1)
                .assetFile("database/plants.realm")
                .build();

        Realm realm = Realm.getInstance(config);

        return realm;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return  appPreferencesHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}

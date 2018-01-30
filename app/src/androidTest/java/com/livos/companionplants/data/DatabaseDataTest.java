package com.livos.companionplants.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.livos.companionplants.data.local.db.AppDbHelper;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static org.junit.Assert.*;

public class DatabaseDataTest {

    private static final int BD_PLANT_COUNT = 133;
    private static final int BD_PLANT_DEFINITION_COUNT = 133;

    private static Context context;
    private static AppDbHelper appDbHelper;
    private static Realm realm;

    @BeforeClass
    public static void onlyOnceSetup() {
        context = InstrumentationRegistry.getTargetContext();
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("plants.realm")
                .schemaVersion(1)
                .assetFile("database/plants.realm")
                .build();

        realm = Realm.getInstance(config);
        appDbHelper = new AppDbHelper(realm);
    }


    @Before
    public void setUp() {

    }


    @Test
    public void plantsCountInDbIsCorrect() throws Exception {
        List<Plant> plants = appDbHelper.getAllPlants();
        assertEquals(BD_PLANT_COUNT, plants.size());
    }

    @Test
    public void plantDefinitionsCountInDbIsCorrect() throws Exception {
        List<PlantDefinition> plantDefinitions = appDbHelper.getAllDefinitions();
        assertEquals(BD_PLANT_DEFINITION_COUNT, plantDefinitions.size());
    }

    @AfterClass
    public static void onlyOnceTearDown() throws Exception {
        realm.close();
    }
}
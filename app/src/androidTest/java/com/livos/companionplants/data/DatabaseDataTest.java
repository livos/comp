package com.livos.companionplants.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.livos.companionplants.data.local.db.AppDbHelper;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.data.local.db.model.PlantDefinition;
import com.livos.companionplants.data.local.db.model.SpaceAssociation;
import com.livos.companionplants.ui.plants.AssociatedPlant;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<AssociatedPlant> plants = appDbHelper.getAllPlants();
        assertEquals(BD_PLANT_COUNT, plants.size());
    }

    @Test
    public void plantDefinitionsCountInDbIsCorrect() throws Exception {
        List<PlantDefinition> plantDefinitions = appDbHelper.getAllDefinitions();
        assertEquals(BD_PLANT_DEFINITION_COUNT, plantDefinitions.size());
    }

    @Test
    public void plantFlagsAreCorrect() throws Exception {
        List<String> avoidArray = Arrays.asList("onion", "garlic","potato","gladiodus");
        List<String> helpsArray = Arrays.asList("tomato", "parsley");
        List<String> helpedByArray = Arrays.asList("sparciflora", "dahlia",  "dahlia", "aster", "thistle", "sunflower", "dill", "coriander",  "parsley", "basil", "comfrey", "marigold", "nasturtium");

        Plant plant = appDbHelper.getPlantByName("asparagus");
        List<AssociatedPlant> aps = appDbHelper.getAssociatedPlants(plant.getId());
        for(AssociatedPlant ap : aps) {

            String plantDef = ap.getPlant().getDefinitions().get(0).getDefinition();
            String flagDef = ap.getFlag().getDefinitions().get(0).getDefinition();
            Log.d("test",plantDef + " " + flagDef);


        }
    }

    @AfterClass
    public static void onlyOnceTearDown() throws Exception {
        realm.close();
    }
}
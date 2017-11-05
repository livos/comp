package com.livos.companionplants;

import android.content.Context;
import com.livos.companionplants.data.local.database.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16, manifest = "/src/main/AndroidManifest.xml")
public class DatabaseTest {
    private Context context;
    private DatabaseHelper databaseHelper;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;

//        //region open database
//        String databaseName = "plants.db";
//        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(context, databaseName, null);
//
//        SQLiteDatabase sqLiteDatabase =  databaseOpenHelper.getReadableDatabase();
//        DaoMaster daoMaster = new  DaoMaster(sqLiteDatabase);
//
//        DaoSession daoSession = daoMaster.newSession();
//        databaseHelper = new AppDatabaseHelper(daoSession);
        //endregion



    }

    @Test
    public void associatedPlantsListIsPlantViewModelList() {
        ///List<PlantsViewModel> plants = databaseHelper.getAssociatedPlants(1L);
        //assertThat(plants.get(0), instanceOf(PlantsViewModel.class));
    }

    @Test
    public void associatedPlantsListIsNotEmpty() {
      //  List<PlantsViewModel> plants = databaseHelper.getAssociatedPlants(1L);
       // assertFalse(plants.isEmpty());
    }



}

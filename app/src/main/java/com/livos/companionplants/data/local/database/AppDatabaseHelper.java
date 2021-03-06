package com.livos.companionplants.data.local.database;

import android.database.Cursor;

import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.data.local.database.model.PlantDao;
import com.livos.companionplants.data.local.database.model.PlantDefinition;
import com.livos.companionplants.model.PlantDetail;
import com.livos.companionplants.model.PlantDetailImpl;
import com.livos.companionplants.util.ApplicationScope;

import org.greenrobot.greendao.database.Database;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

@ApplicationScope
public class AppDatabaseHelper implements DatabaseHelper {
    private DaoSession daoSession;
    private Database db;

    @Inject
    public AppDatabaseHelper(DaoSession daoSession) {

        this.daoSession = daoSession;
        this.db = daoSession.getDatabase();
    }

    @Override
    public PlantDefinition getPlantDefinition(Long id) {
        return daoSession.getPlantDefinitionDao().load(id);
    }

    @Override
    public List<PlantDefinition> getAllPlantsDefinitions() {
       //List<PlantDefinition> plants = daoSession.getPlantDefinitionDao().loadAll();

       return daoSession.getPlantDefinitionDao().loadAll();
    }

    @Override
    public List<PlantDetail> getAllPlants(String localeCode) {
        ArrayList<PlantDetail> plants = new ArrayList<>();


        Cursor cursor = db.rawQuery(String.format("select p._id,\n" +
                                                "pd.definition,\n" +
                                                "pi.picture\n" +
                                                "from plant p\n" +
                                                     "inner join plant_definition  pd\n" +
                                                        "on p._id = pd.plant_id\n" +
                                                        "inner join picture pi\n" +
                                                            "on p.picture_id = pi._id\n" +
                                                "where language = '%s'",localeCode),null);

        while (cursor.moveToNext()) {
            plants.add(new PlantDetailImpl(cursor.getLong(0), cursor.getString(1), cursor.getString(2), 0));
        }

        cursor.close();
        return  plants;
    }

    @Override
    public List<PlantDetail> getAssociatedPlants(Long plantId, String localeCode) {
        ArrayList<PlantDetail> associatedPlants = new ArrayList<>();

        Cursor cursor = db.rawQuery(String.format("select sa.plant_id_2,\n" +
                        "pd2.definition,\n" +
                        "pi2.picture,\n" +
                        "fd._id\n" +
                        "from plant p1\n" +
                        "    inner join space_association sa\n" +
                        "        on p1._id = sa.plant_id_1\n" +
                        "        inner join plant p2\n" +
                        "            on p2._id = sa.plant_id_2\n" +
                        "            inner join plant_definition pd2\n" +
                        "                on p2._id = pd2.plant_id\n" +
                        "            inner join picture pi2\n" +
                        "                on p2.picture_id = pi2._id\n" +
                        "        inner join flag_definition fd\n" +
                        "            on sa.flag_definition_id = fd._id\n" +
                        "where p1._id = %s and pd2.language = '%s'\n" +
                        "order by fd._id",plantId, localeCode),null);

        while (cursor.moveToNext()) {
            associatedPlants.add(new PlantDetailImpl(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
        }

        cursor.close();
        return  associatedPlants;
    }


    @Override
    public String getPictureName(Long plantId) {

        String pictureName = daoSession.getPlantDao().queryBuilder()
                    .where(PlantDao.Properties.Id.eq(plantId))
                    .unique()
                    .getPicture()
                    .getPicture();
        return pictureName;
    }
}

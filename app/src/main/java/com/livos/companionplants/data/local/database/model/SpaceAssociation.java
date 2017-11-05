package com.livos.companionplants.data.local.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "space_association")
public class SpaceAssociation {

    @NotNull
    @Id(autoincrement = true)
    @Property(nameInDb = "_id")
    private long id;

    @NotNull
    @Property(nameInDb = "plant_id_1")
    private long plantId1;

    @NotNull
    @Property(nameInDb = "plant_id_2")
    private long plantId2;

    @NotNull
    @Property(nameInDb = "flag_definition_id")
    private long flagDefinitionId;

    @Generated(hash = 1088720652)
    public SpaceAssociation(long id, long plantId1, long plantId2,
            long flagDefinitionId) {
        this.id = id;
        this.plantId1 = plantId1;
        this.plantId2 = plantId2;
        this.flagDefinitionId = flagDefinitionId;
    }

    @Generated(hash = 2042184735)
    public SpaceAssociation() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlantId1() {
        return this.plantId1;
    }

    public void setPlantId1(long plantId1) {
        this.plantId1 = plantId1;
    }

    public long getPlantId2() {
        return this.plantId2;
    }

    public void setPlantId2(long plantId2) {
        this.plantId2 = plantId2;
    }

    public long getFlagDefinitionId() {
        return this.flagDefinitionId;
    }

    public void setFlagDefinitionId(long flagDefinitionId) {
        this.flagDefinitionId = flagDefinitionId;
    }
}
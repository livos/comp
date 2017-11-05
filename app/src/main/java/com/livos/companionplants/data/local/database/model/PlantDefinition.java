package com.livos.companionplants.data.local.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "plant_definition")
public class PlantDefinition {

    @NotNull
    @Id(autoincrement = true)
    @Property(nameInDb = "_id")
    private long id;

    @NotNull
    @Property(nameInDb = "plant_id")
    private long plantId;

    @NotNull
    @Property(nameInDb = "language")
    private String language;

    @NotNull
    @Property(nameInDb = "definition")
    private String definition;

    @NotNull
    @Property(nameInDb = "is_default")
    private int isDefault;

    @Override
    public String toString() {
        return this.definition;
    }

    @Generated(hash = 770491502)
    public PlantDefinition(long id, long plantId, @NotNull String language,
            @NotNull String definition, int isDefault) {
        this.id = id;
        this.plantId = plantId;
        this.language = language;
        this.definition = definition;
        this.isDefault = isDefault;
    }

    @Generated(hash = 309989786)
    public PlantDefinition() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlantId() {
        return this.plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
package com.livos.companionplants.data.local.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "flag_definition")
public class FlagDefinition {

    @NotNull
    @Id(autoincrement = true)
    @Property(nameInDb = "_id")
    private long id;

    @NotNull
    @Property(nameInDb = "language")
    private String language;

    @NotNull
    @Property(nameInDb = "definition")
    private String definition;

    @NotNull
    @Property(nameInDb = "is_default")
    private int isDefault;

    @Generated(hash = 1497493943)
    public FlagDefinition(long id, @NotNull String language,
            @NotNull String definition, int isDefault) {
        this.id = id;
        this.language = language;
        this.definition = definition;
        this.isDefault = isDefault;
    }

    @Generated(hash = 1409436902)
    public FlagDefinition() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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
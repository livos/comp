package com.livos.companionplants.data.local.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "picture")
public class Picture {

    @NotNull
    @Id(autoincrement = true)
    @Property(nameInDb = "_id")
    private long id;

    @NotNull
    @Property(nameInDb = "picture_type")
    private String pictureType;

    @NotNull
    @Property(nameInDb = "picture")
    private String picture;

    @Generated(hash = 186764147)
    public Picture(long id, @NotNull String pictureType, @NotNull String picture) {
        this.id = id;
        this.pictureType = pictureType;
        this.picture = picture;
    }

    @Generated(hash = 1602548376)
    public Picture() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPictureType() {
        return this.pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



}
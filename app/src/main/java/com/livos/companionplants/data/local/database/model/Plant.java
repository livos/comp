package com.livos.companionplants.data.local.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "plant")
public class Plant {

    @NotNull
    @Id(autoincrement = true)
    @Property(nameInDb = "_id")
    private long id;

    @Property(nameInDb = "picture_id")
    private long pictureId;

    @NotNull
    @Property(nameInDb = "date_added")
    private String dateAdded;

    @NotNull
    @Property(nameInDb = "last_update")
    private String lastUpdate;

    @ToOne(joinProperty = "pictureId")
    private Picture picture;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1784205783)
    private transient PlantDao myDao;

    @Generated(hash = 1881559660)
    public Plant(long id, long pictureId, @NotNull String dateAdded,
            @NotNull String lastUpdate) {
        this.id = id;
        this.pictureId = pictureId;
        this.dateAdded = dateAdded;
        this.lastUpdate = lastUpdate;
    }

    @Generated(hash = 878011190)
    public Plant() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Generated(hash = 1986840853)
    private transient Long picture__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 545923159)
    public Picture getPicture() {
        long __key = this.pictureId;
        if (picture__resolvedKey == null || !picture__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PictureDao targetDao = daoSession.getPictureDao();
            Picture pictureNew = targetDao.load(__key);
            synchronized (this) {
                picture = pictureNew;
                picture__resolvedKey = __key;
            }
        }
        return picture;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1581040998)
    public void setPicture(@NotNull Picture picture) {
        if (picture == null) {
            throw new DaoException(
                    "To-one property 'pictureId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.picture = picture;
            pictureId = picture.getId();
            picture__resolvedKey = pictureId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1347433290)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlantDao() : null;
    }

}
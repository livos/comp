package com.livos.companionplants.ui.events;

import com.livos.companionplants.data.local.db.model.Plant;

/**
 * Created by LVS on 2/22/18.
 */

public class EmptyTabEvent {
    private Plant plant;
    private int tabIdx;

    public int getTabIdx() {
        return tabIdx;
    }

    public void setTabIdx(int tabIdx) {
        this.tabIdx = tabIdx;
    }

}

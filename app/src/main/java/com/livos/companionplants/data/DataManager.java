package com.livos.companionplants.data;


import com.livos.companionplants.data.local.db.DbHelper;
import com.livos.companionplants.data.local.prefs.PreferencesHelper;
import com.livos.companionplants.data.local.state.StateHelper;

public interface DataManager extends DbHelper, PreferencesHelper, StateHelper {
}

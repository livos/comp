package com.livos.companionplants.data.local.prefs;


import android.content.Context;

import com.livos.companionplants.di.qualifier.ApplicationContext;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context) {

    }
}

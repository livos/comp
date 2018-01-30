package com.livos.companionplants.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.livos.companionplants.PlantsApp;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.di.component.DaggerActivityComponent;
import com.livos.companionplants.di.module.ActivityModule;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback  {
    Unbinder unbinder;

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((PlantsApp) getApplication()).getComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onDestroy() {
        if(unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}

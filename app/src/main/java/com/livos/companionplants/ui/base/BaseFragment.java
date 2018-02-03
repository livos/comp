package com.livos.companionplants.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.livos.companionplants.di.component.ActivityComponent;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements MvpView {
    private Unbinder unbinder;

    private BaseActivity activity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    public ActivityComponent getActivityComponent() {
        if (activity != null) {
            return activity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }


    protected abstract void setUp(View view);

    @Override
    public void onDestroy() {
        if(unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public void setUnBinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void hideKeyboard() {
        if (activity != null) {
            activity.hideKeyboard();
        }
    }

}

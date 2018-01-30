package com.livos.companionplants.ui.plants;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.db.model.Plant;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.ui.base.BaseFragment;
import com.livos.companionplants.ui.plants.adapters.PlantsAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlantsFragment extends BaseFragment implements PlantsMvpView {
    public static final String TAG = "PlantsFragment";
    private GridLayoutManager gridLayoutManager;

    @Inject
    PlantsMvpPresenter<PlantsMvpView> presenter;

    @BindView(R.id.rv_plants)
    RecyclerView grvPlants;

    public static PlantsFragment newInstance() {
        Bundle args = new Bundle();
        PlantsFragment fragment = new PlantsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants,container,false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        grvPlants.setLayoutManager(gridLayoutManager);


        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Plant event){
        Log.d(TAG,event.getScientificName());
    }


    @Override
    protected void setUp(View view) {
        presenter.onViewPrepared(); // get data from presenter
    }

    @Override
    public void loadPlants(List<Plant> plants) {
        PlantsAdapter recyclerViewAdapter = new PlantsAdapter(getContext(),plants);
        grvPlants.setAdapter(recyclerViewAdapter);
    }
}

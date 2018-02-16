package com.livos.companionplants.ui.plants;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livos.companionplants.R;
import com.livos.companionplants.di.component.ActivityComponent;
import com.livos.companionplants.ui.base.BaseFragment;
import com.livos.companionplants.ui.plants.adapters.RecyclerViewAdapter;
import com.livos.companionplants.ui.events.PlantSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlantsFragment extends BaseFragment implements PlantsMvpView {
    public static final String TAG = "PlantsFragment";
    private int tabIndex = -1;
    private  RecyclerViewAdapter adapter;
    boolean allPlantsAlreadySelected;

    List<AssociatedPlant> plants;
    PlantSelectedEvent plantSelectedEvent;
    private boolean allPlantsSelectionDone = false;

    @Inject
    PlantsMvpPresenter<PlantsMvpView> presenter;

    @BindView(R.id.rv_plants)
    RecyclerView rvPlants;

//    @BindView(R.id.rv_plants_all)
//    RecyclerView rvPlantsAll;

    @BindView(R.id.ll_tabs)
    LinearLayout llTabs;

//    @BindView(R.id.fl_welcome)
//    FrameLayout flWelcome;


    public static PlantsFragment newInstance() {
//        Bundle args = new Bundle();
//        args.putInt("tabNumber",-1); // Tabnumber set to -1 by default (no filter selected so no tabs in the view)
        PlantsFragment fragment = new PlantsFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            tabIndex = bundle.getInt("tabIndex");
        }
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

        readBundle(getArguments());

        rvPlants.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //rvPlantsAll.setLayoutManager(new GridLayoutManager(getContext(), 4));

        adapter = new RecyclerViewAdapter(getContext());


        return view;
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessage(Plant event){
//        Log.d(TAG,event.getScientificName());
//    }

    @Subscribe
    public void onEvent(PlantSelectedEvent plantSelectedEvent) {
        showTabs();
        this.plantSelectedEvent = plantSelectedEvent;
        this.plantSelectedEvent.setTabIdx(tabIndex);

        presenter.onSelectedPlantChanged(plantSelectedEvent, tabIndex); // LVS ADDED tabindex not correct !!! to fix
    }

    private void showTabs() {
        //flWelcome.setVisibility(View.GONE);
        llTabs.setVisibility(View.VISIBLE);
    }

    private void showAllPlants() {
        //flWelcome.setVisibility(View.VISIBLE);
    }


    @Override
    protected void setUp(View view) {
        presenter.onViewPrepared(tabIndex);

    }

    @Override
    public void loadPlants(List<AssociatedPlant> plants, boolean allPlants) {
        hideKeyboard();

        if(allPlants) {
            allPlantsAlreadySelected = true;
        }


        this.plants = plants;


        adapter.updateAssociatedPlants(plants);
        adapter.notifyDataSetChanged();


        // If no plants has already been selected
        //if(allPlantsAlreadySelected) {
           rvPlants.setAdapter(adapter);
        //} else {
        // todo : if tabindex == -1
            //rvPlantsAll.setAdapter(adapter);
        //}


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach();
    }
}

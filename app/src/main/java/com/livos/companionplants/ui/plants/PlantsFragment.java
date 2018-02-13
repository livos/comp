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
    private int tabNumber = -1;

    List<AssociatedPlant> plants;
    PlantSelectedEvent plantSelectedEvent;
    private boolean allPlantsSelectionDone = false;

    @Inject
    PlantsMvpPresenter<PlantsMvpView> presenter;

    @BindView(R.id.rv_plants)
    RecyclerView rvPlants;

//    @BindView(R.id.tv_helped_title)
//    TextView tvHelpedTitle;
//
//    @BindView(R.id.rv_plants_helps)
//    RecyclerView rvPlantsHelps;
//
//    @BindView(R.id.tv_helps_title)
//    TextView tvHelpsTitle;
//
//    @BindView(R.id.rv_plants_neutral)
//    RecyclerView rvPlantsNeutral;
//
//    @BindView(R.id.tv_neutral_title)
//    TextView tvNeutralTitle;
//
//    @BindView(R.id.rv_plants_avoid)
//    RecyclerView rvPlantsAvoid;
//
//    @BindView(R.id.tv_avoid_title)
//    TextView tvAvoidTitle;

    @BindView(R.id.rv_plants_all)
    RecyclerView rvPlantsAll;

    @BindView(R.id.ll_tabs)
    LinearLayout llTabs;

    @BindView(R.id.fl_welcome)
    FrameLayout flWelcome;


    public static PlantsFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt("tabNumber",-1); // Tabnumber set to -1 by default (no filter selected so no tabs in the view)
        PlantsFragment fragment = new PlantsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static PlantsFragment newInstance(int tabNumber) {
        Bundle args = new Bundle();
        args.putInt("tabNumber",tabNumber);
        PlantsFragment fragment = new PlantsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            tabNumber = bundle.getInt("tabNumber");
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
//        rvPlantsHelps.setLayoutManager(new GridLayoutManager(getContext(), 4));
//        rvPlantsNeutral.setLayoutManager(new GridLayoutManager(getContext(), 4));
//        rvPlantsAvoid.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvPlantsAll.setLayoutManager(new GridLayoutManager(getContext(), 4));



//        if(allPlantsSelectionDone) {
//            RecyclerViewAdapter adapter;
//            adapter = new RecyclerViewAdapter(getContext(), plants);
//            rvPlants.setAdapter(adapter);
//        }


        //setUp(view);


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
        presenter.onSelectedPlantChanged(plantSelectedEvent);
    }

    private void showTabs() {
        flWelcome.setVisibility(View.GONE);
        llTabs.setVisibility(View.VISIBLE);
    }

    private void showAllPlants() {
        flWelcome.setVisibility(View.VISIBLE);
        //llSections.setVisibility(View.GONE);
    }


    @Override
    protected void setUp(View view) {
        presenter.onViewPrepared(tabNumber); // get data from presenter
//        tvHelpedTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (rvPlantsHelped.getVisibility() == View.GONE) {
//                    rvPlantsHelped.setVisibility(View.VISIBLE);
//                } else {
//                    rvPlantsHelped.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        tvHelpsTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (rvPlantsHelps.getVisibility() == View.GONE) {
//                    rvPlantsHelps.setVisibility(View.VISIBLE);
//                } else {
//                    rvPlantsHelps.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        tvNeutralTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (rvPlantsNeutral.getVisibility() == View.GONE) {
//                    rvPlantsNeutral.setVisibility(View.VISIBLE);
//                } else {
//                    rvPlantsNeutral.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        tvAvoidTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (rvPlantsAvoid.getVisibility() == View.GONE) {
//                    rvPlantsAvoid.setVisibility(View.VISIBLE);
//                } else {
//                    rvPlantsAvoid.setVisibility(View.GONE);
//                }
//            }
//        });

    }

    @Override
    public void loadPlants(List<AssociatedPlant> plants) {
        hideKeyboard();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),plants);

        rvPlantsAll.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void loadPlants(List<AssociatedPlant> plants, boolean allPlants) {

        //allPlantsSelectionDone = true;

        hideKeyboard();

        this.plants = plants;

        RecyclerViewAdapter adapter;

        // If no plants has already been selected
        if(allPlants) {
            adapter = new RecyclerViewAdapter(getContext(),plants);
            rvPlants.setAdapter(adapter);
        }

//        RecyclerViewAdapter adapter;
//        switch (tabNumber) {
//            case 0:
//                adapter = new RecyclerViewAdapter(getContext(),plantsHelps);
//                break;
//            case 1:
//                adapter = new RecyclerViewAdapter(getContext(),plantsHelpedBy);
//                break;
//            case 2:
//                adapter = new RecyclerViewAdapter(getContext(),plantsAvoid);
//                break;
//            case 3:
//                adapter = new RecyclerViewAdapter(getContext(),plantsNeutral);
//                break;
//            default:
//                adapter = new RecyclerViewAdapter(getContext(),plantsNeutral);
//        }
//
//        rvPlants.setAdapter(adapter);



//        rvPlantsHelps.setAdapter(adapterHelps);
//        rvPlantsHelped.setAdapter(adapterHelpedBy);
//        rvPlantsAvoid.setAdapter(adapterAvoid);
//        rvPlantsNeutral.setAdapter(adapterNeutral);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetach();
    }
}

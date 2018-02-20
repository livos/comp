package com.livos.companionplants.ui.plants;

import com.livos.companionplants.BuildConfig;
import com.livos.companionplants.data.DataManager;
import com.livos.companionplants.data.local.db.model.Plant;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class PlantsPresenterTest {

    @Mock
    private PlantsMvpView mockPlantsMvpView;
    @Mock
    private DataManager mockDataManager;
    @Mock
    private static List<Plant> plants;

    private PlantsPresenter<PlantsMvpView> plantsPresenter;

    @BeforeClass
    public static void onlyOnce() throws Exception {
        plants = new ArrayList<>();
        plants.add(new Plant());
    }

    @Before
    public void setUp() throws Exception {
        plantsPresenter = new PlantsPresenter<>(mockDataManager);
        plantsPresenter.onAttach(mockPlantsMvpView);
    }

    @Test
    public void dataLoadedInView() {
        plantsPresenter.onViewPrepared();
        verify(mockPlantsMvpView).loadPlants(plants);
    }

    @After
    public void tearDown() throws Exception {
        plantsPresenter.onDetach();
    }
}
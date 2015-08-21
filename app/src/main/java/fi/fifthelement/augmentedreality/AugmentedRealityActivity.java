package fi.fifthelement.augmentedreality;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.fifthelement.augmentedreality.domain.AugmentedWorld;
import fi.fifthelement.augmentedreality.domain.Area;
import fi.fifthelement.augmentedreality.domain.Landmark;
import fi.fifthelement.augmentedreality.helper.AugmentedWorldBuilder;

public class AugmentedRealityActivity extends FragmentActivity {

    private AugmentedRealityFragment fragment;
    private AugmentedWorld world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);


        fragment = (AugmentedRealityFragment) getSupportFragmentManager().findFragmentById(
                R.id.augmentedRealityFragment);

        List<Landmark> landmarkList = new ArrayList<>(Arrays.asList(
                new Landmark(60.869558, 26.705222, 75.99990),
                new Landmark(60.869474, 26.703634, 75.99990),
                new Landmark(60.868664, 26.703828, 75.99990),
                new Landmark(60.868758, 26.705415, 75.99990)));
        Area a = new Area(landmarkList);

        world = AugmentedWorldBuilder.build(this, new ArrayList<>(Arrays.asList(a)));
        fragment.setWorld(world);
        fragment.showFPS(true);
    }

}

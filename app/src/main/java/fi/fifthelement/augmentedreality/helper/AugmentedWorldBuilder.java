package fi.fifthelement.augmentedreality.helper;


import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import fi.fifthelement.augmentedreality.R;
import fi.fifthelement.augmentedreality.domain.AugmentedWorld;
import fi.fifthelement.augmentedreality.domain.Area;
import fi.fifthelement.augmentedreality.domain.BoundaryPoint;

public class AugmentedWorldBuilder {

    private static AugmentedWorld world;
    private static int defaultMarker = R.drawable.poi_marker_blue;
    private static double defaultLatitude = 60.869132;//KOUVOLA :)
    private static double defaultLongitude = 26.704516;

    public AugmentedWorldBuilder() {
    }

    public AugmentedWorld getWorld() {
        return world;
    }

    public void setWorld(AugmentedWorld world) {
        this.world = world;
    }

    public static AugmentedWorld build(Context context, List<Area> areas) {
        if (world != null) {
            return world;
        }
        world = new AugmentedWorld(context);
        world.setDefaultImage(R.drawable.poi_marker_blue);
        world.setGeoPosition(defaultLatitude, defaultLongitude);

        for (Area a : areas) {
            world.addArea(a);
        }

        return world;
    }

}

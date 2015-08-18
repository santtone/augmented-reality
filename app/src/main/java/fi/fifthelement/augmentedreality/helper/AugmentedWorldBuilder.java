package fi.fifthelement.augmentedreality.helper;


import android.content.Context;

import java.util.List;

import fi.fifthelement.augmentedreality.R;
import fi.fifthelement.augmentedreality.domain.AugmentedWorld;
import fi.fifthelement.augmentedreality.domain.Area;

public class AugmentedWorldBuilder {

    private static AugmentedWorld world;
    private static int defaultMarker = R.drawable.poi_marker_blue;
    private static double defaultLatitude = 60.869423;//KOUVOLA :)
    private static double defaultLongitude = 26.704719;

    public AugmentedWorldBuilder(){}

    public AugmentedWorld getWorld() {
        return world;
    }

    public void setWorld(AugmentedWorld world) {
        this.world = world;
    }

    public static AugmentedWorld build(Context context, List<Area> areas){
        if(world != null){
            return world;
        }
        world = new AugmentedWorld(context);
        world.setDefaultImage(R.drawable.poi_marker_blue);
        world.setGeoPosition(defaultLatitude, defaultLongitude);

        for(Area a : areas){
            world.addArea(a);
        }

        return world;
    }
}

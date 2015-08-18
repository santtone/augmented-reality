package fi.fifthelement.augmentedreality.domain;

import com.beyondar.android.world.GeoObject;

import fi.fifthelement.augmentedreality.R;

public class BoundaryPoint extends GeoObject{

    private int marker = R.drawable.poi_marker_blue;

    public BoundaryPoint() {
        setImageResource(marker);
    }
}

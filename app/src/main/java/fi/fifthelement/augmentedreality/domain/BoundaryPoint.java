package fi.fifthelement.augmentedreality.domain;

import com.beyondar.android.world.GeoObject;

import fi.fifthelement.augmentedreality.R;

public class BoundaryPoint extends GeoObject{

    private int marker = R.drawable.boundary_point;

    public BoundaryPoint(double latitude, double longitude, long id) {
        super(id);
        super.setGeoPosition(latitude, longitude);
        super.setImageResource(marker);
        super.setName("Boundary");
    }
}

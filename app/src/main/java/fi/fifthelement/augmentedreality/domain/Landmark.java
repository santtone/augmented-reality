package fi.fifthelement.augmentedreality.domain;

import com.beyondar.android.world.GeoObject;

import fi.fifthelement.augmentedreality.R;

public class Landmark extends GeoObject {

    private int marker = R.drawable.poi_marker_blue;

    public Landmark(double latitude, double longitude, long id) {
        super(id);
        super.setGeoPosition(latitude, longitude);
        super.setImageResource(marker);
        super.setName("Landmark");
    }

}

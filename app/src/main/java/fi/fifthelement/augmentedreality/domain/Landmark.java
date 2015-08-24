package fi.fifthelement.augmentedreality.domain;

import com.beyondar.android.world.GeoObject;

import fi.fifthelement.augmentedreality.R;

public class Landmark extends GeoObject {

    private int marker = R.drawable.poi_marker_blue;

    public Landmark(double latitude, double longitude, double altitude, String name) {
        super(999);//Should we use some real and unique id???
        super.setName(name);
        super.setGeoPosition(latitude, longitude, altitude);
        super.setImageResource(marker);
    }

}

package fi.fifthelement.augmentedreality.domain;

import com.beyondar.android.opengl.texture.Texture;
import com.beyondar.android.world.GeoObject;

import fi.fifthelement.augmentedreality.R;

public class BoundaryPoint extends GeoObject{

    private int marker = R.drawable.boundary_point;

    public BoundaryPoint(double latitude, double longitude, double altitude) {
        super(99);//Should we use some real and unique id???
        super.setGeoPosition(latitude, longitude, altitude);
        super.setImageResource(marker);
        super.setName("Boundary");
        Texture t = super.getTexture();
    }
}

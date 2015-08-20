package fi.fifthelement.augmentedreality.domain;

import android.content.Context;
import com.beyondar.android.world.World;
import java.util.List;

import fi.fifthelement.augmentedreality.helper.BoundaryHelper;

public class AugmentedWorld extends World {

    public AugmentedWorld(Context context) {
        super(context);
    }

    public void addArea(Area area) {
        for (Landmark l : area.getLandmarks()) {
            super.addBeyondarObject(l);
        }
        //TODO: instead of points we should draw real lines between landmarks (not supported by beyondAR framework)
        List<BoundaryPoint> points = BoundaryHelper.findAreaBoundaries(area);
        for (BoundaryPoint p : points) {
            super.addBeyondarObject(p);
        }
    }

}

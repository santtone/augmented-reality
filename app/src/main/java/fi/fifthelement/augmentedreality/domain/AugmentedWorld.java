package fi.fifthelement.augmentedreality.domain;

import android.content.Context;
import com.beyondar.android.world.World;

public class AugmentedWorld extends World {


    public AugmentedWorld(Context context) {
        super(context);
    }

    public void addArea(Area area){
        for(Landmark l : area.getLandmarks()){
            super.addBeyondarObject(l);
        }
    }
}

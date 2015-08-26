package fi.fifthelement.augmentedreality.helper;


import android.content.Context;
import android.graphics.Color;

import com.beyondar.android.plugin.radar.RadarView;
import com.beyondar.android.plugin.radar.RadarWorldPlugin;

public class RadarBuilder {


    public static RadarWorldPlugin build(Context context, RadarView radarView){
        RadarWorldPlugin  radar = new RadarWorldPlugin(context);
        radar.setRadarView(radarView);
        radar.setListColor(1, Color.RED);
        radar.setListDotRadius(1, 3);
        radar.setMaxDistance(100);
        return radar;

    }
}

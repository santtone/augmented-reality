package fi.fifthelement.augmentedreality;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.beyondar.android.opengl.util.LowPassFilter;
import com.beyondar.android.plugin.radar.RadarView;
import com.beyondar.android.plugin.radar.RadarWorldPlugin;
import com.beyondar.android.view.OnClickBeyondarObjectListener;
import com.beyondar.android.world.BeyondarObject;
import com.mikepenz.materialdrawer.Drawer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.fifthelement.augmentedreality.domain.AppSettings;
import fi.fifthelement.augmentedreality.domain.AugmentedWorld;
import fi.fifthelement.augmentedreality.domain.Area;
import fi.fifthelement.augmentedreality.domain.Landmark;
import fi.fifthelement.augmentedreality.helper.AugmentedWorldBuilder;
import fi.fifthelement.augmentedreality.helper.DrawerHandler;
import fi.fifthelement.augmentedreality.utils.Device;

public class AugmentedRealityActivity extends FragmentActivity implements OnClickBeyondarObjectListener {

    FragmentManager fragmentManager;
    private AugmentedRealityFragment fragment;
    private AugmentedWorld world;
    private Drawer drawer;
    private DrawerHandler drawerHandler;

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    private AppSettings appSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        fragmentManager = super.getSupportFragmentManager();
        fragment = (AugmentedRealityFragment) getSupportFragmentManager().findFragmentById(
                R.id.augmentedRealityFragment);

        List<Landmark> landmarkList = new ArrayList<>(Arrays.asList(
                new Landmark(60.869558, 26.705222, 75.99990, "Rajapyykki #1"),
                new Landmark(60.869474, 26.703634, 75.99990, "Rajapyykki #2"),
                new Landmark(60.868664, 26.703828, 75.99990, "Rajapyykki #3"),
                new Landmark(60.868758, 26.705415, 75.99990, "Rajapyykki #4")));
        Area a = new Area(landmarkList);

        world = AugmentedWorldBuilder.build(this, new ArrayList<>(Arrays.asList(a)));
        fragment.setWorld(world);
        fragment.setOnClickBeyondarObjectListener(this);
        drawerHandler = new DrawerHandler(this, world);
        this.drawer = drawerHandler.buildDrawer();
        appSettings = new AppSettings(false, LowPassFilter.ALPHA);
    }

    @Override
    public void onClickBeyondarObject(ArrayList<BeyondarObject> objects) {
        if (objects.size() > 0) {
            if (Landmark.class.isInstance(objects.get(0))) {
                Landmark l = (Landmark) objects.get(0);
                Device.vibrate(this);
                LandmarkDialog dialog = LandmarkDialog.newInstance(l);
                dialog.show(fragmentManager, "landmark_dialog");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!drawerHandler.closeDrawer()) {
            drawerHandler.removeActiveFragment();
        }
    }
}

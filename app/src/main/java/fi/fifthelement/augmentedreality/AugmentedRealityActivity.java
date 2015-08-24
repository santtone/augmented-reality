package fi.fifthelement.augmentedreality;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

import com.beyondar.android.view.BeyondarGLSurfaceView;
import com.beyondar.android.view.OnClickBeyondarObjectListener;
import com.beyondar.android.view.OnTouchBeyondarViewListener;
import com.beyondar.android.world.BeyondarObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.fifthelement.augmentedreality.domain.AugmentedWorld;
import fi.fifthelement.augmentedreality.domain.Area;
import fi.fifthelement.augmentedreality.domain.Landmark;
import fi.fifthelement.augmentedreality.helper.AugmentedWorldBuilder;
import fi.fifthelement.augmentedreality.utils.Device;

public class AugmentedRealityActivity extends FragmentActivity implements OnClickBeyondarObjectListener {

    private AugmentedRealityFragment fragment;
    private AugmentedWorld world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);


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
        fragment.showFPS(true);
        fragment.setOnClickBeyondarObjectListener(this);
    }

    @Override
    public void onClickBeyondarObject(ArrayList<BeyondarObject> objects) {
        if (objects.size() > 0) {
            if(Landmark.class.isInstance(objects.get(0))){
                Landmark l = (Landmark)objects.get(0);
                System.out.println(l.getName());
                Device.vibrate(this);
            }
        }
    }
}

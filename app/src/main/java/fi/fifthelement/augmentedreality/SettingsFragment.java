package fi.fifthelement.augmentedreality;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.beyondar.android.opengl.util.LowPassFilter;
import com.beyondar.android.plugin.radar.RadarView;
import com.beyondar.android.plugin.radar.RadarWorldPlugin;

import fi.fifthelement.augmentedreality.domain.AugmentedWorld;
import fi.fifthelement.augmentedreality.helper.RadarBuilder;

public class SettingsFragment extends Fragment {

    private SeekBar lowPassSeekBar;
    private CheckBox radarCheckBox;
    private AugmentedWorld world;
    private Context context;

    public static SettingsFragment newInstance(AugmentedWorld world, Context context) {
        SettingsFragment f = new SettingsFragment();
        f.setWorld(world);
        f.setContext(context);
        return f;
    }

    public void setWorld(AugmentedWorld world) {
        this.world = world;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings, container, false);
        initializeLowPassSeekBar(view);
        initializeRadar(view);
        return view;
    }

    private void initializeLowPassSeekBar(View view) {
        lowPassSeekBar = (SeekBar) view.findViewById(R.id.lowPassSeekBar);
        lowPassSeekBar.setProgress((int)(((AugmentedRealityActivity) context).getAppSettings().getLowPassFilterValue() * 1000));
        lowPassSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar == lowPassSeekBar) {
                    float value = ((float) progress / (float) 1000);
                    LowPassFilter.ALPHA = value;
                    ((AugmentedRealityActivity) context).getAppSettings().setLowPassFilterValue(value);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private RadarWorldPlugin radar;

    private void initializeRadar(final View view) {
        radarCheckBox = (CheckBox) view.findViewById(R.id.radarCheckBox);
        radarCheckBox.setChecked(((AugmentedRealityActivity) context).getAppSettings().getRadarVisibility());
        radarCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                radar = RadarBuilder.build(context, (RadarView) ((AugmentedRealityActivity) context).findViewById(R.id.radarView));
                if (isChecked) {
                    world.addPlugin(radar);
                    ((AugmentedRealityActivity) context)
                            .findViewById(R.id.radarContainer)
                            .setVisibility(View.VISIBLE);

                    ((AugmentedRealityActivity) context).getAppSettings().setRadarVisibility(isChecked);
                } else {
                    world.removePlugin(radar);
                    ((AugmentedRealityActivity) context)
                            .findViewById(R.id.radarContainer)
                            .setVisibility(View.GONE);
                    ((AugmentedRealityActivity) context).getAppSettings().setRadarVisibility(isChecked);
                }
            }
        });
    }
}

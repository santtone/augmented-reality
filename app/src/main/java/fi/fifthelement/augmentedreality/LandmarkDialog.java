package fi.fifthelement.augmentedreality;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fi.fifthelement.augmentedreality.domain.Landmark;

public class LandmarkDialog extends DialogFragment{

    private TextView lblDistance;
    private TextView lblLocation;
    private Landmark landmark;

    static LandmarkDialog newInstance(Landmark landmark) {
        LandmarkDialog f = new LandmarkDialog();
        f.setLandmark(landmark);
        return f;
    }
    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.landmark_dialog, container);
        getDialog().setTitle(landmark.getName());

        lblDistance = (TextView)view.findViewById(R.id.lbl_distance);
        lblDistance.setText("" + (int)landmark.getDistanceFromUser() + " m");

        lblLocation = (TextView)view.findViewById(R.id.lbl_location);
        lblLocation.setText(landmark.getLatitude() + ", " + landmark.getLongitude());

        return view;
    }
}

package fi.fifthelement.augmentedreality.domain;

import java.util.List;

public class Area{

    private List<Landmark> landmarks;

    public Area(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }
}

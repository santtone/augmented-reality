package fi.fifthelement.augmentedreality.domain;


public class AppSettings {
    private boolean radarVisibility;
    private float lowPassFilterValue;

    public AppSettings(boolean radarVisibility, float lowPassFilterValue){
        this.radarVisibility = radarVisibility;
        this.lowPassFilterValue = lowPassFilterValue;
    }

    public boolean getRadarVisibility() {
        return radarVisibility;
    }

    public void setRadarVisibility(boolean radarVisibility) {
        this.radarVisibility = radarVisibility;
    }

    public float getLowPassFilterValue() {
        return lowPassFilterValue;
    }

    public void setLowPassFilterValue(float lowPassFilterValue) {
        this.lowPassFilterValue = lowPassFilterValue;
    }
}

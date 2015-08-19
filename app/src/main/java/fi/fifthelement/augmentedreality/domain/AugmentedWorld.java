package fi.fifthelement.augmentedreality.domain;

import android.content.Context;
import android.location.Location;

import com.beyondar.android.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AugmentedWorld extends World {


    public AugmentedWorld(Context context) {
        super(context);
    }

    public void addArea(Area area) {
        for (Landmark l : area.getLandmarks()) {
            super.addBeyondarObject(l);
        }
        List<BoundaryPoint> points = findBoundaries(area);
        for (BoundaryPoint p : points) {
            super.addBeyondarObject(p);
        }
    }

    private List<BoundaryPoint> findBoundaries(Area area) {
        List<BoundaryPoint> points = new ArrayList<>();
        double lat1 = area.getLandmarks().get(1).getLatitude();
        double lon1 = area.getLandmarks().get(1).getLongitude();
        double lat2 = area.getLandmarks().get(2).getLatitude();
        double lon2 = area.getLandmarks().get(2).getLongitude();

        HashMap<Integer, BoundaryPoint> data = new HashMap<Integer, BoundaryPoint>();
        data.put(1, new BoundaryPoint(lat1, lon1, 87));
        Location mid = midPoint(lat1, lon1, lat2, lon2);
        data.put(3,new BoundaryPoint(mid.getLatitude(), mid.getLongitude(), 54));
        data.put(2, new BoundaryPoint(lat2, lon2, 87));


        return new ArrayList<>(data.values());
    }


    private Location midPoint(double lat1, double lon1, double lat2, double lon2) {

        double dLon = Math.toRadians(lon2 - lon1);

        //convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        Location l = new Location("");
        l.setLatitude(Math.toDegrees(lat3));
        l.setLongitude(Math.toDegrees(lon3));
        System.out.println(Math.toDegrees(lat3) + " , " + Math.toDegrees(lon3));
        return l;
    }




  /*  private List<BoundaryPoint> findBoundaries(Area area) {
        double y1 = area.getLandmarks().get(1).getLatitude();
        double x1 = area.getLandmarks().get(1).getLongitude();
        double y2 = area.getLandmarks().get(2).getLatitude();
        double x2 = area.getLandmarks().get(2).getLongitude();
        double k = (y2 - y1) / (x2 - x1);

        Location p1 = new Location("");
        Location p2 = new Location("");
        p1.setLatitude(y1);
        p1.setLongitude(x1);
        p2.setLatitude(y2);
        p2.setLongitude(x2);
        double d = p1.distanceTo(p2);
        int count = (int) d / 2;

        List<BoundaryPoint> points = new ArrayList<>();
        for (int i = 1; i < count; i++) {

            points.add(new BoundaryPoint(y, x, i + 100));
        }

        return points;
    }*/
}

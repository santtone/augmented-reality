package fi.fifthelement.augmentedreality.helper;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import fi.fifthelement.augmentedreality.domain.Area;
import fi.fifthelement.augmentedreality.domain.BoundaryPoint;
import fi.fifthelement.augmentedreality.domain.Landmark;


public class BoundaryHelper {

    public BoundaryHelper(){}

    public static double calculateDistanceBetweenLandmarks(Landmark first, Landmark second) {
        Location l1 = new Location("");
        l1.setLatitude(first.getLatitude());
        l1.setLongitude(first.getLongitude());
        Location l2 = new Location("");
        l2.setLatitude(second.getLatitude());
        l2.setLongitude(second.getLongitude());
        return l1.distanceTo(l2);
    }

    public static List<BoundaryPoint> findAreaBoundaries(Area area) {
        List<BoundaryPoint> boundaries = new ArrayList<>();
        for (int i = 0; i < area.getLandmarks().size(); i++) {
            if (i < area.getLandmarks().size() - 1) {
                boundaries.addAll(findPointsBetweenLandmarks(area.getLandmarks().get(i), area.getLandmarks().get(i + 1)));
            } else {
                boundaries.addAll(findPointsBetweenLandmarks(area.getLandmarks().get(i), area.getLandmarks().get(0)));
            }
        }
        return boundaries;
    }

    public static List<BoundaryPoint> findPointsBetweenLandmarks(Landmark first, Landmark second) {
        List<BoundaryPoint> points = new ArrayList<>();

        points.add(new BoundaryPoint(first.getLatitude(), first.getLongitude()));
        points.add(new BoundaryPoint(second.getLatitude(), second.getLongitude()));

        int pointCount = (int) calculateDistanceBetweenLandmarks(first, second) / 2 / 2;
        while (points.size() < pointCount) {
            List<BoundaryPoint> pointsToCompare = new ArrayList<>(points);
            for (int i = 0; i < pointsToCompare.size() - 1; i++) {
                points.add(2 * i + 1, findMiddlePoint(pointsToCompare.get(i), pointsToCompare.get(i + 1)));
            }
        }
        return new ArrayList<>(points);
    }

    public static BoundaryPoint findMiddlePoint(BoundaryPoint firstPoint, BoundaryPoint secondPoint) {
        double dLongitude = Math.toRadians(secondPoint.getLongitude() - firstPoint.getLongitude());
        double latitude1 = Math.toRadians(firstPoint.getLatitude());
        double longitude1 = Math.toRadians(firstPoint.getLongitude());
        double latitude2 = Math.toRadians(secondPoint.getLatitude());
        double bx = Math.cos(latitude2) * Math.cos(dLongitude);
        double by = Math.cos(latitude2) * Math.sin(dLongitude);
        double latitude = Math.atan2(
                Math.sin(latitude1) + Math.sin(latitude2),
                Math.sqrt((Math.cos(latitude1) + bx) * (Math.cos(latitude1) + bx) + by * by));
        double longitude = longitude1 + Math.atan2(by, Math.cos(latitude1) + bx);
        return new BoundaryPoint(Math.toDegrees(latitude), Math.toDegrees(longitude));
    }
}

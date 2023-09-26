package pt.ipp.isep.esinf.data;

import java.util.Objects;

public class GPS {

    private static final double R = 6371e3;

    private double latitude;
    private double longitude;

    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static GPS fromStringPair(String gps) {
        String[] coords = gps.trim().split(",");
        return new GPS(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPS gps = (GPS) o;
        return Double.compare(latitude, gps.latitude) == 0 && Double.compare(longitude, gps.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    public double distance(GPS other) {
        double phi1 = latitude * Math.PI / 180;
        double phi2 = other.latitude * Math.PI / 180;
        double deltaPhi = (other.latitude - latitude) * Math.PI / 180;
        double deltaLambda = (other.longitude - longitude) * Math.PI / 180;
        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

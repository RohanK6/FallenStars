/**
 * This class represents the location (latitude, longitude) of a Meteorite object
 * @author Rohan Khanderia
 */
package project2;

public class Location {
    protected double latitude;
    protected double longitude;

    /**
     * The Location constructor that initializes the Location object
     * with a latitude of type double and a longitude of type double
     * @param latitude Latitude for the Location object
     * @param longitude Longitude for the Location object
     * @throws IllegalArgumentException if the latitude is not between -90 and 90 or
     *                                  if the longitude is not between -180 and 180
     */
    public Location(double latitude, double longitude) {
        if (latitude >= -90 && latitude <= 90) {
            this.latitude = latitude;
        }
        else {
            throw new IllegalArgumentException("Latitude must be within -90 and 90, inclusive!");
        }
        if (longitude >= -180 && longitude <= 180) {
            this.longitude = longitude;
        }
        else {
            throw new IllegalArgumentException("Longitude must be within -180 and 180, inclusive!");
        }
    }

    /**
     * Computes and returns distance between this location and the one provided as the parameter using the Haversine formula.
     * Credits to Geek for Geeks for providing the code for the formula
     * @param loc The location object that is called on another location object
     * @return the distance between the two Location objects
     * @throws IllegalArgumentException if Location parameter is null
     */
    public double getDistance(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }

        double dLat = Math.toRadians(loc.latitude - this.latitude);
        double dLon = Math.toRadians(loc.longitude - this.longitude);

        double lat2 = Math.toRadians(loc.latitude);
        double lat1 = Math.toRadians(this.latitude);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double radius = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        return radius * c;
    }

    /**
     * Gets the latitude and returns it
     * @return latitude of the Location object
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude and returns it
     * @return longitude of the Location object
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Overrides equals method from Object class
     * @param obj the reference object with which to compare.
     * @return true if the difference between the two Location's latitudes and longitudes are both less than 0.00001,
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Location) &&
                (Math.abs(this.latitude - ((Location) obj).latitude) < 0.00001) &&
                (Math.abs(this.longitude - ((Location) obj).longitude) < 0.00001)) {
            return true;
        }
        return false;
    }

}

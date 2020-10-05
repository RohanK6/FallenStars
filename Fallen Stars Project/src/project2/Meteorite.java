/**
 * This class represents a meteorite with all of its metadata (name, id, mass, year, and location)
 * @author Rohan Khanderia
 */
package project2;
import java.util.Calendar;

public class Meteorite implements Comparable<Meteorite> {
    protected String name;
    protected int id;
    protected int mass;
    protected int year;
    protected Location loc;

    /**
     * Meteorite constructor that constructs and validates a new Meteorite object
     * @param name name of the Meteorite object
     * @param id id of the Meteorite object
     * @throws IllegalArgumentException if the String is non-empty or id greater than 0
     */
    public Meteorite(String name, int id) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        else if (!name.isBlank()) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("A valid name must be non-empty.");
        }
        if (id > 0) {
            this.id = id;
        }
        else {
            throw new IllegalArgumentException("A valid id must be greater than 0");
        }
    }

    /**
     * Sets the mass of the Meteorite object
     * @param mass the mass for the Meteorite object
     * @throws IllegalArgumentException if mass is less or equal to 0
     */
    public void setMass(int mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("A valid mass must be a positive integer.");
        }
        this.mass = mass;
    }

    /**
     * Sets the year of the Meteorite object
     * @param year the year for the Meteorite object
     * @throws IllegalArgumentException if year is greater than the current year or less than 0
     */
    public void setYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year > currentYear || year < 0) {
            throw new IllegalArgumentException("A valid year should be a positive integer smaller than the current year");
        }
        this.year = year;
    }

    /**
     * Sets the location of the Meteorite object
     * @param loc the location for the Meteorite object
     * @throws IllegalArgumentException if the Location is null
     */
    public void setLocation(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("A valid location cannot be null.");
        }
        this.loc = loc;
    }

    /**
     * Gets the mass of the Meteorite object
     * @return mass - the mass of the Meteorite object
     */
    public int getMass() {
        return mass;
    }

    /**
     * Gets the year of the Meteorite object
     * @return year - the year of the Meteorite object
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the Location of the Meteorite object
     * @return loc - the Location of the Meteorite object
     */
    public Location getLocation() {
        return loc;
    }

    /**
     * Implements Comparable<Meteorite> interface. Compares two meteorite objects.
     * Objects are compared based on their alphanumeric names ("aaaba" is smaller, or comes before, "bacd").
     * If two names are the same, the comparison is performed based on the numeric id's.
     * The comparison by names are case insensitive.
     * @param meteor the Meteorite object being compared to the Meteorite object compareTo is called upon
     * @return -1 if the Meteorite object comes before the Meteorite object calling compareTo(),
     *          1 if the Meteorite object comes after the Meteorite object calling compareTo(),
     *          0 if the Meteorite objects have the same name and id
     */
    @Override
    public int compareTo(Meteorite meteor) {
        if (this.name.toLowerCase().compareTo(meteor.name.toLowerCase()) < 0) {
            return -1;
        }
        if (this.name.toLowerCase().compareTo(meteor.name.toLowerCase()) > 0) {
            return 1;
        }
        if (this.name.toLowerCase().compareTo(meteor.name.toLowerCase()) == 0) {
            if (this.id > meteor.id) {
                return 1;
            }
            if (this.id < meteor.id) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Checks if the two Meteorite objects are the same, if they have identical names and ids
     * @param obj the reference object with which to compare
     * @return true if the object is the same as the obj argument,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Meteorite) &&
                (this.name.toUpperCase().equals(((Meteorite) obj).name.toUpperCase())) &&
                (this.id == ((Meteorite) obj).id)) {
            return true;
        }
        return false;
    }

    /**
     * Overrides toString() method from the Object class.
     * @return a String matching the following pattern:
     *         NAME ID YEAR MASS LATITUDE LONGITUDE
     */
    @Override
    public String toString() {
        String oName, oID, oMass, oYear, oLat, oLong;

        oName = this.name;
        oID = this.id + "";

        if (this.year > 0 && year < Calendar.getInstance().get(Calendar.YEAR)) {
            oYear = this.year + "";
        }
        else {
            oYear = " ";
        }
        if (this.mass > 0) {
            oMass = this.mass + "";
        }
        else {
            oMass = " ";
        }
        if (this.loc != null) {
            oLat = String.format("%10.5f", this.loc.latitude);
            oLong = String.format("%10.5f", this.loc.longitude);
        }
        else {
            oLat = " ";
            oLong = " ";
        }
        return String.format("%-20s %4s %4s %6s %s %s", oName, oID, oYear, oMass, oLat, oLong);
    }
}
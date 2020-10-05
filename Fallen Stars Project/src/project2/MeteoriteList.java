/**
 * This class should is used to store all the Meteorite objects.
 * The class inherits from the ArrayList<Meteorite> class
 * @author Rohan Khanderia
 */
package project2;

import java.util.ArrayList;

public class MeteoriteList extends ArrayList<Meteorite> {
    protected MeteoriteList mList;
    protected MeteoriteLinkedList mLinkedList;

    /**
     * Default constructor that creates an empty MeteoriteList object
     */
    public MeteoriteList() {
        mList = this;
    }

    /**
     * Finds all Meteorite objects with mass within delta grams of the specified mass.
     * @param mass mass requested of the Meteorite objects
     * @param delta the range allowed plus or minus the mass parameter for a Meteorite object to be
     *              added to the MeteoriteLinkedList
     * @return MeteoriteLinkedList that contains all Meteorite objects that have a mass within
     *         the mass parameter plus or minus delta, null if no Meteorite objects are found
     * @throws IllegalArgumentException if either parameter is less than 0
     */
    public MeteoriteLinkedList getByMass (int mass, int delta) {
        bubbleSort(mList);
        mLinkedList = new MeteoriteLinkedList();
        if (mass < 0 || delta < 0) {
            throw new IllegalArgumentException("Parameters cannot be 0.");
        }
        boolean found = false;
        for (Meteorite m : mList) {
            if (m.mass >= mass - delta && m.mass <= mass + delta) {
                found = true;
                mLinkedList.add(m);
            }
        }
        if (!found) {
            return null;
        }
        return mLinkedList;
    }

    /**
     * Finds the Meteorite object whose landing site is nearest to the specified location
     * @param loc the specified Location to compare all Meteorite objects to
     * @return the Meteorite object that landed closest to the Location parameter
     * @throws IllegalArgumentException if the Location is null
     */
    public Meteorite getByLocation (Location loc) {
        bubbleSort(mList);
        if (loc == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        if (mList.size() == 0) {
            return null;
        }
        Meteorite closest = mList.get(0);
        for (Meteorite m : mList) {
            try {
                if (loc.getDistance(m.loc) < loc.getDistance(closest.loc)) {
                    closest = m;
                }
            } catch (IllegalArgumentException e) {

            }
        }
        return closest;
    }

    /**
     * Finds all Meteorite objects that landed during the the specified year
     * @param year the year that the Meteorite objects need to have landed to be added to the MeteoriteLinkedList
     * @return a MeteoriteLinkedList object that contains all Meteorite objects that landed during the year specified
     * @throws IllegalArgumentException if the year specified is less than 0
     */
    public MeteoriteLinkedList getByYear (int year) {
        bubbleSort(mList);
        mLinkedList = new MeteoriteLinkedList();
        if (year < 0) {
            throw new IllegalArgumentException("Parameter cannot be 0.");
        }
        boolean found = false;
        for (Meteorite m : mList) {
            if (m.year == year) {
                found = true;
                mLinkedList.add(m);
            }
        }
        if (!found) {
            return null;
        }
        return mLinkedList;
    }

    /**
     * Sorts the MeteoriteList using a Bubble sorting algorithm that calls compareTo() from the Meteorite class
     * to determine whether or not the Meteorite objects are sorted correctly
     * @param list the ArrayList with Meteorite objects to sort
     */
    public static void bubbleSort(ArrayList<Meteorite> list) {
        Meteorite temp;
        if (list.size() > 1) {
            for (int i = 0; i < list.size()-1; i++) {
                for (int j = 0; j < list.size()-i-1; j++) {
                    if (list.get(j).compareTo(list.get(j+1)) > 0) {
                        temp = list.get(j);
                        list.set(j, list.get(j+1));
                        list.set(j+1, temp);
                    }
                }
            }
        }
    }


}
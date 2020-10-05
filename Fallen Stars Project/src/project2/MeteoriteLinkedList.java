/**
 * This class provides an implementation of a simple sorted singly linked list.
 * @author Rohan Khanderia
 */
package project2;

public class MeteoriteLinkedList {
    protected MeteoriteLinkedList mLinkedList;
    protected Node head;
    protected int listCounter;

    /**
     * Default constructor that instantiates an empty list
     */
    public MeteoriteLinkedList() {
        mLinkedList = this;
    }

    /**
     * Single parameter constructor that given a MeteoriteList objects produces a MeteoriteLinkedList object with the same elements
     * @param list MeteoriteList that contains all the Meteorite objects to be copied
     * @throws IllegalArgumentException if the MeteoriteList passed in is null
     */
    public MeteoriteLinkedList(MeteoriteList list) {
        mLinkedList = this;
        if (list == null) {
            throw new IllegalArgumentException("MeteoriteList parameter cannot be null.");
        }
        for (Meteorite m : list) {
            mLinkedList.add(m);
        }
    }

    /**
     * This method adds a new element to the list, maintaining a sorted order
     * @param m Meteorite object to be added into the list
     * @return true if the object was successfully added, false if it already is in or failed to be added
     * @throws IllegalArgumentException if the Meteorite object is null
     */
    public boolean add(Meteorite m) {
        if (m == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }

        if (head == null) {
            head = new Node(m);
        }

        Node current = head;
        Node value = new Node(m);

        while(current.next != null) {
            if (current.next.data.equals(m)) {
                return false;
            }
            if (m.compareTo(current.next.data) < 0) {
                value.next = current.next;
                current.next = value;
                listCounter++;
                return true;
            }
            current = current.next;
        }

        current.next = value;
        listCounter++;

        return true;
    }

    /**
     * Removes and returns an element matching the name and id specified
     * @param name name of Meteorite object to be removed
     * @param id id of Meteorite object to be removed
     * @return the Meteorite object that was removed from the list, null if no Meteorite object was found in the list
     */
    public Meteorite remove(String name, int id) {
        Node tracingNode = head;
        try {
            while (tracingNode.next != null) {
                if ((tracingNode.next.data.name.equals(name)) && (tracingNode.next.data.id == id)) {
                    Meteorite removed = new Meteorite(tracingNode.next.data.name, tracingNode.next.data.id);
                    listCounter--;
                    tracingNode.next = tracingNode.next.next;
                    return removed;
                }
                tracingNode = tracingNode.next;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }


    /**
     * Overrides toString() method from the Object class.
     * Returns a String object that represents this list.
     * The string contains all the elements of the list in order one per line.
     * @return formatted output of the linked list containing the Meteorite objects
     */
    @Override
    public String toString() {
        String linkedListOutput = "";
        if (head != null) {
            Node current = head.next;
            while(current.next != null) {
                linkedListOutput += current.data.toString() + "\n";
                current = current.next;
            }
            linkedListOutput += current.data.toString();
        }
        return linkedListOutput;
    }

    /**
     * Gets the number of elements in the MeteoriteLinkedList
     * @return the size of the MeteoriteLinkedList (how many elements are in it)
     */
    public int size() {
        return listCounter;
    }

    /**
     * Private class to define the Node. Used to implement a custom Linked List.
     * The code was provided by Professor Joanna Klukowska in the Project guidelines.
     */
    private class Node implements Comparable<Node> {
        Meteorite data;
        Node next;

        Node (Meteorite data) {
            this.data = data;
        }

        public String toString () {
            return data.toString();
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) {
                return false;
            }
            Node other = (Node) o;
            if (!this.data.equals(other.data)) {
                return false;
            }
            return true;
        }

        public int compareTo (Node n) {
            return data.compareTo(n.data);
        }

    }

}

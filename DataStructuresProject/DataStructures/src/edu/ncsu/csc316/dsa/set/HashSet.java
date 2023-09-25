package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The HashSet is implemented as a linear probing hash table data structure to
 * support efficient set abstract data type behaviors.
 * 
 * Using the linear probing hash table ensures expected runtime of O(1) for
 * {@link Set#add}, {@link Set#remove}, and {@link Set#contains}; O(n) expected
 * runtime for {@link Set#addAll}, {@link Set#removeAll}, and
 * {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size} and
 * {@link Set#isEmpty}.
 * 
 * The HashSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the set
 */
public class HashSet<E> extends AbstractSet<E> {
    // Since our hash map uses linear probing, the entries are not ordered.
    // As a result, we do not restrict our hash set to use Comparable elements.
    // This also gives you an option if you need a set to manage elements
    // that are *NOT* Comparable (versus a TreeSet)
    /**The map for the set*/
    private Map<E, E> map;

    /**
     * Constructs a new HashSet
     */
    public HashSet() {
        // This constructor will use our "production version" of our hash map
        // meaning random values for alpha and beta will be used
        this(false);
    }

    /**
     * Constructs a new HashSet to be used ONLY when testing the data structure
     * @param isTesting Whether or not the set is used in testing.
     */
    public HashSet(boolean isTesting) {
        // If isTesting is true, this constructor will use our "development version" of
        // our hash map
        // meaning alpha=1, beta=1, and prime=7
        map = new LinearProbingHashMap<E, E>(isTesting);
    }
    /**
     * The iterator over this set's entries.
     * @return The iterator for this set's entries.
     */
    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }
    /**
     * This adds the given value to the set.
     * @param value The value to add to the set.
     */
    @Override
    public void add(E value) {
        map.put(value, value);
    }
    /**
     * This checks for a value in the set.
     * @param value The value to check for in the set.
     * @return Whether or not the value is in the set.
     */
    @Override
    public boolean contains(E value) {
        return map.get(value) != null;
    }
    /**
     * This removes a given value from the set.
     * @param value The value to remove from the set.
     * @return The value removed from the set.
     */
    @Override
    public E remove(E value) {
        return map.remove(value);
    }
    /**
     * This returns the size of the set.
     * @return The size of the set.
     */
    @Override
    public int size() {
        return map.size();
    }
}

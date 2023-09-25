package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

/**
 * The TreeSet is implemented as a RedBlackTreeMap
 * data structure to support efficient set abstract data type behaviors.
 * 
 * Using a RedBlack tree ensures worst-case runtime of
 * O(logn) for {@link Set#add}, {@link Set#remove}, and {@link Set#contains};
 * O(nlogn) worst-case runtime for {@link Set#addAll}, {@link Set#removeAll},
 * and {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size}
 * and {@link Set#isEmpty}.
 * 
 * The TreeSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the set
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {
    // Since we will delegate to an existing balanced search tree, the entries will
    // be ordered.
    // As a result, we must also restrict our tree set to use Comparable elements.
    /**The tree for the set*/
    private Map<E, E> tree;

    /**
     * Constructs a new TreeSet
     */
    public TreeSet() {
        tree = new RedBlackTreeMap<E, E>(); // USE AND EFFICIENT, BALANCED SEARCH TREE HERE
    }
    /**
     * The iterator over this set's entries.
     * @return The iterator for this set's entries.
     */
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }
    /**
     * This adds the given value to the set.
     * @param value The value to add to the set.
     */
    @Override
    public void add(E value) {
        tree.put(value, value);
    }
    /**
     * This checks for a value in the set.
     * @param value The value to check for in the set.
     * @return Whether or not the value is in the set.
     */
    @Override
    public boolean contains(E value) {
        return tree.get(value) != null;
    }
    /**
     * This removes a given value from the set.
     * @param value The value to remove from the set.
     * @return The value removed from the set.
     */
    @Override
    public E remove(E value) {
        return tree.remove(value);
    }
    /**
     * This returns the size of the set.
     * @return The size of the set.
     */
    @Override
    public int size() {
        return tree.size();
    }
}
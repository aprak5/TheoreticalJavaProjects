package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type. Specifically, this abstraction allows the underlying data
 * structure to store entries in a sorted order based on the keys to help
 * improve the efficiency of lookUp behaviors.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractOrderedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
	/**This is the comparator used for ordering.*/
    private Comparator<K> compare;

    /**
     * Constructs a new AbstractOrderedMap using a custom comparator
     * 
     * @param compare the custom Comparator to use when comparing keys
     */
    public AbstractOrderedMap(Comparator<K> compare) {
        if (compare == null) {
            this.compare = new NaturalOrder();
        } else {
            this.compare = compare;
        }
    }

    /**
     * Compare keys to determine their relative ordering. Return a negative number
     * to indicate key1 should appear before key2. Return a positive number to
     * indicate key1 should appear after key2. Return 0 to indicate key1 and key2
     * are considered equal.
     * 
     * @param key1 a key to compare
     * @param key2 a key to compare
     * @return a number to indicate the relative ordering of key1 and key2: a
     *         negative number indicates key1 should appear before key2; a positive
     *         number indicates key1 should appear after key2; 0 indicates the two
     *         keys are considered equal
     */
    public int compare(K key1, K key2) {
        return compare.compare(key1, key2);
    }

    /**
     * Delegates to the {@link Comparable#compareTo) implementation that defines the
     * natural ordering of the keys
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    private class NaturalOrder implements Comparator<K> {
    	/**
    	 * This is the compare method from Comparator, which ensures both objects can be compared (by casting).
    	 * @param first The first key to be compared.
    	 * @param second The second key to be compared.
    	 * @return The return value determines the ordering of the objects.
    	 */
        public int compare(K first, K second) {
            return ((Comparable<K>) first).compareTo(second);
        }
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {
    	/**The list interally stores all entries in a array based list.*/
        private ArrayBasedList<Entry<K, V>> list;

        /**
         * Makes a new instance of EntryCollection() by initializing the list.
         */
		public EntryCollection() {
            list = new ArrayBasedList<Entry<K, V>>();
        }

		/**
		 * This adds a given entry to the list.
		 * @param entry The entry to add to the list.
		 */
		public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

		/**
		 * This creates an iterator for the object.
		 * @return An iterator for the object.
		 */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }

        /**
         * EntryCollectionIterator implements the {@link Iterator} interface to allow traversing
         * through the entries stored in the map. EntryCollection does not allow removal
         * operations
         * 
         * @author Dr. King and Amit Prakash
         *
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {
        	/**The EntryIterator iterator.*/
            private Iterator<Entry<K, V>> it;

            /**
             * This creates the object by initalizing the Iterator.
             */
            public EntryCollectionIterator() {
                it = list.iterator();
            }

            /**
        	 * Checks whether there is another entry.
        	 * @returns A boolean value indicating the presence of another entry.
        	 */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
    		 * Returns the next entry in the object.
    		 * @return The next entry of the object.
    		 */
            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            /**
    		 * This operation is not yet supported, so it throws an exception if called.
    		 * @throws java.lang.UnsupportedOperationException If called, this exception is thrown.
    		 */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    } 
}
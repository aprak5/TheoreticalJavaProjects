package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    /**
     *  Helper method to determine if an entry is one of the sentinel -INFINITY or +INFINITY nodes (containing a null key).
     * @param node The node to check.
     * @return Whether or not a node is a sentinel.
     */
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * This searches for a key in the list.
     * @param key The key to search for.
     * @return The node with the given key or null if not found.
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        } 
        while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
            current = current.next;
        }
        return current;
    }

    /**
     * Retrieves a key's corresponding value from the map.
     * @param key The key of the given value to look for.
     * @return The value of the given key.
     */
    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if(temp.getEntry() == null)
        	return null;
        if(temp.getEntry().getKey().compareTo(key) < 0)
        	return null;
        return temp.getEntry().getValue();
    }

    /**
     * This is a helper method for put() to help add a given node to all levels of the map.
     * @param prev The previous reference of the given node.
     * @param down The down reference of the given node.
     * @param entry The entry in the given node.
     * @return The node reference constructed at all levels of the map.
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        if(prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        if(newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        if(down != null) {
        	down.setAbove(newNode);
        }
        return newNode;
    }

    /**
     * This adds a given entry with a given key and value into the map.
     * @param key The key of the entry to add.
     * @param value The value of the entry to add.
     * @return The value added to the map or null if unable to add.
     */
    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> temp = lookUp(key);
        if(temp != null && temp.getEntry() != null && temp.getEntry().getKey() == key) {
        		V originalValue = temp.getEntry().getValue();
        		while(temp != null) {
        			((MapEntry<K, V>) temp.getEntry()).setValue(value);
        			temp = temp.getAbove();
        		}
        		return originalValue;
        }
        SkipListNode<K, V> q = null;
        int currentLevel = -1;
        do {
        	currentLevel++;
        	if(currentLevel >= height) {
        		height++;
        		SkipListNode<K, V> tail = start.next;
        		start = insertAfterAbove(null, start, null);
        		insertAfterAbove(start, tail, null);
        	}
        	q = insertAfterAbove(temp, q, new MapEntry<K, V>(key, value));
        	while(temp.getAbove() == null) {
        		temp = temp.getPrevious();
        	}
        	temp = temp.getAbove();
        } while(coinToss.nextBoolean());
        
        size++;
        return null;	
    }

    /**
     * This removes a given entry with a given key from the map.
     * @param key The given key of the given entry to remove.
     * @return The value removed from the list.
     */
    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if(temp.getEntry() == null)
        	return null;
        if(temp.getEntry().getKey() != key)
        	return null;
        else {
        	V orgVal = temp.getEntry().getValue();
        	while(temp.getBelow() != null) {
        		temp = temp.getBelow();
        	}
        	while(temp != null) {
        		SkipListNode<K, V> previous = temp.getPrevious();
    			SkipListNode<K, V> next = temp.getNext();
    			if(previous != null)
    				previous.setNext(next);
    			if(next != null)
    				next.setPrevious(previous);
    			temp = temp.getAbove();
        	}
        	while(start.getNext() == null && start.getBelow() != null) {
        		start = start.getBelow();
        		start.setAbove(null);
        	}
        	size--;
        	return orgVal;
        }
    }

    /**
     * Returns the size of the map.
     * @return The size of the map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This creates a set of iterable entries for the given map.
     * @return An iterable object of the given map.
     */
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    /**
     * Returns a string representation of the map.
     * @return A string representation of the map.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    
    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
//    public String toFullString() {
//        StringBuilder sb = new StringBuilder("SkipListMap[\n");
//        SkipListNode<K, V> cursor = start;
//        SkipListNode<K, V> firstInList = start;
//        while (cursor != null) {
//            firstInList = cursor;
//            sb.append("-INF -> ");
//            cursor = cursor.next;
//            while (cursor != null && !isSentinel(cursor)) {
//                sb.append(cursor.getEntry().getKey() + " -> ");
//                cursor = cursor.next;
//            }
//            sb.append("+INF\n");
//            cursor = firstInList.below;
//        }
//        sb.append("]");
//        return sb.toString();
//    }

    /**
     * This class defines the node used in the SkipListMap for the array-based list.
     * @author Amit Prakash
     *
     * @param <K> This is the key used to sort the entries.
     * @param <V> This is the value or element stored in the entries.
     */
    private static class SkipListNode<K, V> {
    	/**The element entry of the node*/
        private Entry<K, V> entry;
        /**The next higher level reference*/
        private SkipListNode<K, V> above;
        /**The next lower level reference*/
        private SkipListNode<K, V> below;
        /**The previous reference*/
        private SkipListNode<K, V> prev;
        /**The next reference*/
        private SkipListNode<K, V> next;

        /**
         * The constructor for the node, which initializes every field using setters.
         * @param entry The entry to be stored in the node.
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        /**
         * This gets the above reference for the node.
         * @return above The above reference for the node.
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * This gets the entry for the node.
         * @return entry The entry for the node.
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * This gets the next reference for the node.
         * @return next The next reference for the node.
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * This gets the previous reference for the node.
         * @return prev The previous reference for the node.
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }
        
        /**
         * This gets the below reference for the node.
         * @return below The below reference for the node.
         */
        public SkipListNode<K, V> getBelow() {
            return below;
        }

        /**
         * This sets the above reference of the node to the parameter given.
         * @param up The above reference to be set for the node.
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * This sets the below reference of the node to the parameter given.
         * @param down The below reference to be set for the node.
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * This sets the entry of the node to the parameter given.
         * @param entry The entry reference to be set for the node.
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * This sets the next reference of the node to the parameter given.
         * @param next The next reference to be set for the node.
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * This sets the previous reference of the node to the parameter given.
         * @param prev The previous reference to be set for the node.
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}
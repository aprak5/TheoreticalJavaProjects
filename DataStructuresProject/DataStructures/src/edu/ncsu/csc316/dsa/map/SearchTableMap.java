package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     * 
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * This searches for a key in the list using binarySearchHelper().
     * @param key The key to search for.
     * @return The index with the given key.
     */
    private int lookUp(K key) {
		return binarySearchHelper(0, list.size() - 1, key);
    }

    /**
     * This helps lookUp() search through the list using the binary search algorithm.
     * @param min The minimum value to look at.
     * @param max The maximum value to look at.
     * @param key The key to look for.
     * @return The index of the key.
     */
    private int binarySearchHelper(int min, int max, K key) {
		if(max < min)
			return max + 1;
		int mid = (max + min) / 2;
		int comp = compare(key, list.get(mid).getKey());
		if(comp == 0)
			return mid;
		else if(comp < 0)
			return binarySearchHelper(min, mid - 1, key);
		else
			return binarySearchHelper(mid + 1, max, key);
    }

    /**
     * Returns the size of the map.
     * @return The size of the map.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Retrieves a key's corresponding value from the map.
     * @param key The key of the given value to look for.
     * @return The value of the given key.
     */
    @Override
    public V get(K key) {
        int index = lookUp(key);
        if(index == size() || compare(key, list.get(index).getKey()) != 0)
        	return null;
        return list.get(index).getValue();
    }

    /**
     * This creates a set of iterable entries for the given map.
     * @return An iterable object of the given map.
     */
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    /**
     * This adds a given entry with a given key and value into the map.
     * @param key The key of the entry to add.
     * @param value The value of the entry to add.
     * @return The value added to the map or null if unable to add.
     */
    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        if(index < size() && compare(key, list.get(index).getKey()) == 0)
        	return list.set(index, new MapEntry<K, V>(key, value)).getValue();
        list.add(index, new MapEntry<K, V>(key, value));
    	return null;
    }

    /**
     * This removes a given entry with a given key from the map.
     * @param key The given key of the given entry to remove.
     * @return The value removed from the list.
     */
    @Override
    public V remove(K key) {
    	int index = lookUp(key);
        if(index == size() || compare(key, list.get(index).getKey()) != 0)
        	return null;
        return list.remove(index).getValue();
    }
    
    /**
     * Returns a string representation of the map.
     * @return A string representation of the map.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
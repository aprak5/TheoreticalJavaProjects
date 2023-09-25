package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.AVLTreeMap;

/**
 * The SeparateChainingHashMap is implemented as a hash table that uses separate
 * chaining for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * Map.put(), Map.get(), and Map.remove().
 * 
 * The secondary map that appears within each bucket (with separate chaining)
 * supports worst-case O(logn) runtime for Map.put(), Map.get(), and Map.remove()
 * within each bucket.
 * 
 * The SeparateChainingHashMap class is based on the implementation developed
 * for use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {
	/**This is the entries in the map*/
    private Map<K, V>[] table;
    /**This is the map's size*/
    private int size;

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new separate chaining hash map that uses natural ordering of
     * keys when performing comparisons. The created hash table is initialized to
     * have the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new separate chaining hash map that
     * uses natural ordering of keys when performing comparisons. The created hash
     * table is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    /**
     * This returns an iterable instance over the entries in the map.
     * @return This is the iterable instance over the entries in the map.
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                // Each bucket contains a map, so include
                // all entries in the entrySet for the map
                // at the current bucket
                for (Entry<K, V> entry : table[i].entrySet()) {
                    collection.add(entry);
                }
            }
        }
        return collection;
    }

    /**
     * This creates the table with the given capacity.
     * @param capacity This is the table with the given capacity.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // You can choose to use any EFFICIENT secondary map.
        // UnorderedLinkedMap, SearchTableMap, and BinarySearchTreeMap are NOT the most
        // efficient maps we have discussed this semester since UnorderedLinkedMap has
        // O(n) put, get, and remove; SearchTableMap has O(n) put and remove; and
        // BinarySearchTreeMap has O(n) put, get, and remove. Therefore, use a
        // SkipListMap with expected O(logn) runtime, or a balanced binary search tree
        // for guaranteed O(logn) worst-case runtime.
        table = new AVLTreeMap[capacity];
        size = 0;
    }

    /**
     * This gets a given bucket with the given hash and key.
     * @param hash This is the hash of the bucket to get in the given map.
     * @param key This is the key of the bucket to get in the given map.
     * @return The bucket with the given key and hash.
     */
    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist
        if (bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value
        return bucket.get(key);
    }

    /**
     * This puts a given bucket with the given key, hash, and value.
     * @param hash This is the hash of the bucket to put in the given map.
     * @param key This is the key of the bucket to put in the given map.
     * @param value This is the value of the bucket to put in the given map.
     * @return The bucket's value with the given key, hash, and value.
     */
    @Override
    public V bucketPut(int hash, K key, V value) {
		Map<K, V> bucket = table[hash];
		if(bucket == null) {
			table[hash] = new AVLTreeMap<K, V>();
			bucket = table[hash];
		}	
		int oldSize = bucket.size();
		V answer = bucket.put(key, value);
		size += (bucket.size() - oldSize);
		return answer;
    }

    /**
     * This removes a given bucket with the given hash and key.
     * @param hash This is the hash of the bucket to remove in the given map.
     * @param key This is the key of the bucket to remove in the given map.
     * @return The removed bucket's orignal value with the given key and hash.
     */
    @Override
    public V bucketRemove(int hash, K key) {
    	Map<K, V> bucket = table[hash];
		if(bucket == null)
			return null;
		int oldSize = bucket.size();
		V answer = bucket.remove(key);
		size -= (oldSize - bucket.size());
		return answer;
    }

    /**
     * This returns the map's size.
     * @return This is the map's size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This returns the map's capacity.
     * @return This is the map's capacity.
     */
    @Override
    protected int capacity() {
        return table.length;
    }
}
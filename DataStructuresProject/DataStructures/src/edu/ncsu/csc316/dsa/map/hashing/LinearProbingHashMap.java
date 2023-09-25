package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * Map.put(), Map.get(), and Map.remove().
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/**This is the entries in the map*/
    private TableEntry<K, V>[] table;
    /**This is the map's size*/
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    /**
     * This returns an iterable instance over the entries in the map.
     * @return This is the iterable instance over the entries in the map.
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayBasedList<Entry<K, V>> buffer = new ArrayBasedList<>();
        for(int h = 0; h < capacity(); h++) {
        	if(!isAvailable(h))
        		buffer.addLast(table[h]);
        }
        return buffer;
        
//        EntryCollection collection = new EntryCollection();
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] != null) {
//                // Each bucket contains a map, so include
//                // all entries in the entrySet for the map
//                // at the current bucket
//                for (TableEntry<K, V> entry : table) {
//                    collection.add(entry);
//                }
//            }
//        }
//        return collection;
    }

    /**
     * This creates the table with the given capacity.
     * @param capacity This is the table with the given capacity.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    /**
     * This checks a bucket whether it is free or not.
     * @param index The index of the bucket to check.
     * @return True if the bucket is free, otherwise false. 
     */
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    /**
     * This gets a given bucket with the given hash and key.
     * @param hash This is the hash of the bucket to get in the given map.
     * @param key This is the key of the bucket to get in the given map.
     * @return The bucket with the given key and hash.
     */
    @Override
    public V bucketGet(int hash, K key) {
    	int j = findBucket(hash, key);
    	if(j < 0)
    		return null;
    	return table[j].getValue();
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
        int j = findBucket(hash, key);
        if(j >= 0) {
        	V orgVal = table[j].getValue();
        	table[j].setValue(value);
        	return orgVal;
        }	
        table[-(j + 1)] = new TableEntry<K, V>(key, value);
        size++;
        return null;
    }
    
    /**
     * This finds a bucket with a given index and key.
     * @param index This is the index for the given bucket.
     * @param key This is the key for the given bucket.
     * @return This is the index for the bucket.
     */
    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	do {
    		if(isAvailable(j)) {
    			if(avail == -1)
    				avail = j;
    			if(table[j] == null)
    				break;
    		}
    		else if(table[j].getKey().equals(key))
    			return j;
    		j = (j + 1) % capacity();
    	} while(j != index);
    	return -(avail + 1);
    }

    /**
     * This removes a given bucket with the given hash and key.
     * @param hash This is the hash of the bucket to remove in the given map.
     * @param key This is the key of the bucket to remove in the given map.
     * @return The removed bucket's orignal value with the given key and hash.
     */
    @Override
    public V bucketRemove(int hash, K key) {
        int j = findBucket(hash, key);
    	if(j < 0)
    		return null;
    	V answer = table[j].getValue();
    	table[j] = new TableEntry<K, V>(null, null);
    	table[j].setDeleted(true);
    	size--;
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

    /**
     * This is the object for the entries in the table map.
     * @author Amit Prakash
     *
     * @param <K> This is the element for the key.
     * @param <V> This is the element for the value.
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/**This is whether the entry has been deleted or not.*/
        private boolean isDeleted;

        /**
         * This is the key and value for constructing the key and value.
         * @param key This is the key for constructing the entry.
         * @param value This is the value for constructing the entry.
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * This is returns whether the entry has been deleted or not.
         * @return Whether the entry has been deleted or not.
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * This sets the entry isDeleted to the passed-in parameter.
         * @param deleted The new status for isDeleted.
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}

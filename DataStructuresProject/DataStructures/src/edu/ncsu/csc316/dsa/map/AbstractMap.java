package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {
	/**
	 * Checks whether a list is empty.
	 * @return The boolean value of whether the size of a list is zero is returned.
	 */
	@Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King and Amit Prakash
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {

    	/**The key of the map entry (according to which it will be ordered).*/
        private K key;
        /**The value of the map entry (the value associated with the key).*/
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        /**
         * This retrieves the key of the given map entry.
         * @return key The key of the given map entry.
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * This retrieves the value of the given map entry.
         * @return value The value of the given map entry.
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        /**
         * Compares two entries and returns a value indicating order.
         * @param o This is the entry to be compare the given entry instance against.
         * @return An integer indicated the order of the entry relative to this entry.
         */
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    } 
    
   
    /**
     * Creates a new KeyIterator of this object.
     * @return A KeyIterator object for this object.
     */
    @Override
    public Iterator<K> iterator() {
    	return new KeyIterator();
    }


    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    protected class ValueIterator implements Iterator<V> {
    	/**The entry set for this object.*/
    	private Iterator<Entry<K, V>> entries = entrySet().iterator();

    	/**
    	 * Checks whether there is another entry.
    	 * @returns A boolean value indicating the presence of another entry.
    	 */
		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		/**
		 * Returns the next value in the object.
		 * @return The next value of the object.
		 */
		@Override
		public V next() {
			return entries.next().getValue();
		}
       
		/**
		 * This operation is not yet supported, so it throws an exception if called.
		 * @throws java.lang.UnsupportedOperationException If called, this exception is thrown.
		 */
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
    }
    
    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    protected class KeyIterator implements Iterator<K> {

    	/**The entry iterator for this object.*/
        private Iterator<Entry<K, V>> it;
        
        /**
         * This constructor initializes this object by calling the iterator() method.
         */
        public KeyIterator() {
            it = entrySet().iterator();
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
		 * Returns the next key in the object.
		 * @return The next key of the object.
		 */
        @Override
        public K next() {
            return it.next().getKey();
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

    /**
     * ValueIterable implements the {@link Iterable} interface to allow traversing
     * through the values stored in the map and creating the ValueIterator.
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    private class ValueIterable implements Iterable<V> {
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
    }
    
    /**
     * Creates a ValueIterable object to allow the creation of a ValueIterator.
     * @return A ValueIterable object to allow the creation of a ValueIterator.
     */
    public Iterable<V> values() {
		return new ValueIterable();
	}
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {
    	/**The list interally stores all entries in a singly linked list.*/
        private SinglyLinkedList<Entry<K, V>> list;

        /**
         * Makes a new instance of EntryCollection() by initializing the list.
         */
		public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
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
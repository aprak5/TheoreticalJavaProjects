package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/**The positional list to keep track of the entries.*/
    private PositionalList<Entry<K, V>> list;
    
    /**
     * This constructs the UnorderedLinkedMap() object and initializes the positional list.
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * This searches for a key in the list using binarySearchHelper().
     * @param key The key to search for.
     * @return The position with the given key or null if not found.
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		if(list.size() == 0) {
			return null;
		}	
		if(list.first().getElement().getKey().equals(key)) {
			return list.first();
		}
		else if(list.last().getElement().getKey().equals(key)) {
			return list.last();
		}
		while(it.hasNext()) {
			Position<Entry<K, V>> pos = it.next();
			if(pos.getElement().getKey().equals(key))
				return list.after(list.before(pos));
		}
		return null;
    }

    /**
     * Retrieves a key's corresponding value from the map.
     * @param key The key of the given value to look for.
     * @return The value of the given key or null if not found.
     */
    @Override
    public V get(K key) {
    	try {
    		Position<Entry<K, V>> p = lookUp(key);
    		moveToFront(p);
    		p = lookUp(key);
    		return p.getElement().getValue();
    	} catch (IllegalArgumentException iae) {
    		return null;
    	}
    }
    
    /**
     * Moves the given position node to the front of the list.
     * @param position The position of the node to move to the front of the list.
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	list.addFirst(list.remove(position));
    }

    /**
     * This adds a given entry with a given key and value into the map.
     * @param key The key of the entry to add.
     * @param value The value of the entry to add.
     * @return The value added to the map or null if unable to add.
     */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p == null) {
        	list.addFirst(new MapEntry<>(key, value));
        	return null;
        } else {
        	final V orgVal = p.getElement().getValue();
        	list.set(p, new MapEntry<>(key, value));
        	moveToFront(p);
        	return orgVal; 
        }
    }
    
    /**
     * This removes a given entry with a given key from the map.
     * @param key The given key of the given entry to remove.
     * @return The value removed from the list.
     */
    @Override
    public V remove(K key) {
    	Position<Entry<K, V>> p = null;
        try {
    	   p = lookUp(key);
       } catch(IllegalArgumentException iae) {
    	   //nothing
       }
       if(p == null) {
    	   return null;
       }
       V answer = p.getElement().getValue();
       list.remove(p);
	   return answer;
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
     * This creates a set of iterable entries for the given map.
     * @return An iterable object of the given map.
     */
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    /**
     * Returns a string representation of the map.
     * @return A string representation of the map.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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
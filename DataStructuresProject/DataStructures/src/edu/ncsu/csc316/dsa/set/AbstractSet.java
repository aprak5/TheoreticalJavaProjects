package edu.ncsu.csc316.dsa.set;

/**
 * A skeletal implementation of the Set abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the set
 * abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the set
 */
public abstract class AbstractSet<E> implements Set<E> {
	/**
	 * Adds all instances of a given set to this set.
	 * @param other The set to unify with this set.
	 */
    @Override
    public void addAll(Set<E> other) {
        for(E element : other) {
            add(element);
        }
    }
    /**
     * Retains only the instances of a given set in this set.
     * @param other The set to intersect with this set.
     */
    @Override
    public void retainAll(Set<E> other) {
        for(E element : this) {
            if(!other.contains(element)) {
                remove(element);
            }
        }
    }
    /**
     * Removes all instances of a given set from this set.
     * @param other The set to remove from this set.
     */
    @Override
    public void removeAll(Set<E> other) {
        for(E element : other) {
            remove(element);
        }
    }
    /**
     * This sees if the set is empty.
     * @return This returns true if the set is of size 0.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
}
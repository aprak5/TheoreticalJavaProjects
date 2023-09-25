package edu.ncsu.csc316.dsa.list;

/**
 * A skeletal implementation of the List abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the list
 * abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the list
 */
public abstract class AbstractList<E> implements List<E> {
	/**
	 * Implemented in the concrete classes with the singular add method.
	 * @param element This is the element to be added.
	 */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }
    /**
	 * Implemented in the concrete classes with the singular add method.
	 * @param element This is the element to be added.
	 */
    @Override
    public void addLast(E element) {
        add(size(), element);
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when accessing any specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when adding elements at specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }
    /**
	 * Implemented in the concrete classes with the singular get method.
	 * @return The first element in the list.
	 */
    @Override
    public E first() {
        return get(0);
    }
    /**
	 * Implemented in the concrete classes with the singular size method.
	 * @return Whether or not the list has a size of 0.
	 */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
	 * Implemented in the concrete classes with the singular get method.
	 * @return The last element in the list.
	 */
    @Override
    public E last() {
        return get(size() - 1);
    }
    /**
	 * Implemented in the concrete classes with the singular remove method.
	 * @return The element removed from the list.
	 */
    @Override
    public E removeFirst() {
        return remove(0);
    }
    /**
	 * Implemented in the concrete classes with the singular remove method.
	 * @return The element removed from the list.
	 */
    @Override
    public E removeLast() {
        return remove(size() - 1);
    }
}

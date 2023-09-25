/**
 * 
 */
package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    /**
	 * This method adds an element to the Array list, so that it is at the specified index in the ArrayList.
	 * @param index This is the desired index of the element in the ArrayList.
	 * @param element This is the desired element to be added into the ArrayList.
	 * @throws java.lang.IndexOutOfBoundsException This exception is thrown if the given index is outside of the range of indices for the given ArrayList.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index > size || size == data.length) {
			ensureCapacity(index);
			ensureCapacity(size());
			if(size() == 0)
				ensureCapacity(1);
		}
		
		// Check valid index
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		} 

		if (index == size()) {
			ensureCapacity(size() + 1);
			data[index] = (E) element;
		} else {
			ensureCapacity(index);
			ensureCapacity(size() + 1);
			for (int k = size(); k > index; k--) {
				data[k] = data[k - 1];
			}
			data[index] = (E) element;
		}
		size++;
	}

	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
	 * This returns the element of list at the given index for the ArrayList.
	 * This is overridden for specific implementation for this instance of ArrayList, specific to the PackScheduler.
	 * @return This returns the element of the Array (list) at the given index for the ArrayList.
	 * @throws java.lang.IndexOutOfBoundsException This exception is thrown if the given index is outside of the range of indices for the given ArrayList.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.size())
			throw new IndexOutOfBoundsException();
		return data[index];
	}

	/**
	 * This method removes a desired element from the desired index in the ArrayList.
	 * @param index This is the desired index of the element in the ArrayList.
	 *  @return e: This is the desired element to be removed in the ArrayList.
	 * @throws java.lang.IndexOutOfBoundsException This exception is thrown if the given index is outside of the range of indices for the given ArrayList.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		E removed = data[index];

		if (index == size() - 1) {
			data[index] = null;
			size--;
			return removed;
		} else {

			for (int k = index; k < size(); k++) {
				data[k] = data[k + 1];
			}
		}
		data[size() - 1] = null;
		size--;
		return removed;
	}

	/**
	 * This method sets the element at a specific index to the desired element passed in.
	 * @param index This is the desired index of the element in the ArrayList.
	 * @param element This is the desired element to be set in the ArrayList.
	 * @return this.get(index): This is the desired element to be set in the ArrayList.
	 * @throws java.lang.IndexOutOfBoundsException This exception is thrown if the given index is outside of the range of indices for the given ArrayList.
	 */
	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		E oldElement = data[index];
		data[index] = element;
		return oldElement;
	}
	
	/**
	 * This returns the size of the ArrayList.
	 * This is overridden for specific implementation for this instance of ArrayList, specific to the PackScheduler.
	 * @return size: This is the size of the ArrayList.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This is method that runs the iterator for this list.
	 * @return ElementIterator(): for this list.
	 */
	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
    
	/**
	 * Private inner class for the element iterator.
	 * Helps traverse through the list.
	 * @author Amit Prakash
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/**Position of the Iterator*/
	    private int position;
	    /**If next() has been called yet.*/
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        position = 0;
	    }

	    /**
	     * If the iterator has a next element.
	     * @return If the Iterator's position is within the list.
	     */
	    @Override
	    public boolean hasNext() {
	    	return position < size;		
	    }

	    /**
	     * Determines the next element of the iterator.
	     * @return The next element of the list.
	     * @throws java.lang.IndexOutOfBoundsException This exception is thrown if the given index is outside of the range of indices for the given ArrayList.
		 */
	    @Override
	    public E next() throws NoSuchElementException {
	    	if(position == size)
	    		throw new NoSuchElementException();
	    	removeOK = true;
			return data[position++];
	    }
	        
	    /**
	     * Removes the element at the given position of the iterator.
	     * @throws java.lang.IllegalStateException() If the next() has not been called yet or if the list does not have a next element.
	     */
	    @Override
	    public void remove() throws IllegalStateException {
	        if(!removeOK || !this.hasNext())
	        	throw new IllegalStateException();
	        ArrayBasedList.this.remove(position);
	    }
	}
}
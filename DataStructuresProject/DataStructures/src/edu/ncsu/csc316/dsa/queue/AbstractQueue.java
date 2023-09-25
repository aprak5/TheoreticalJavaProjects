package edu.ncsu.csc316.dsa.queue;

/**
 * A skeletal implementation of the Queue abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the queue abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the queue
 */
public abstract class AbstractQueue<E> implements Queue<E> {
	/**
	 * This returns whether or not the list is empty.
	 * @return This is a boolean value indicating whether or not the size is 0.
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}

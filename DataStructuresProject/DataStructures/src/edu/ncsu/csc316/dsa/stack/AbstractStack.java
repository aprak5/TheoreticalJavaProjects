	

package edu.ncsu.csc316.dsa.stack;

/**
 * A skeletal implementation of the Stack abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the stack abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the stack
 */
public abstract class AbstractStack<E> implements Stack<E> {
	/**
	 * This returns whether or not the list is empty.
	 * @return This is a boolean value indicating whether or not the size is 0.
	 */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}


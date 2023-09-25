package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }

    /**
     * This method adds the given element into list (stack).
     * @param element This is the element added into the list.
     */
	@Override
	public void push(E element) {
		list.addLast(element);
	}

	/**
     * This method removes the given element from list (stack).
     * @return This is the element removed from the list.
     * @throws java.util.EmptyStackException If the size is of size 0 or null, an EmptyStackException is thrown.
     */
	@Override
	public E pop() throws EmptyStackException {
		if(list.size() == 0 || list == null)
			throw new EmptyStackException();
		return list.removeLast();
	}

	/**
	 * This is the most recent element in the list (stack).
	 * @return The most recent element in the list (stack).
	 * @throws java.util.EmptyStackException If the size is of size 0 or null, an EmptyStackException is thrown.
	 */
	@Override
	public E top() throws EmptyStackException {
		if(list.size() == 0 || list == null)
			throw new EmptyStackException();
		return list.last();
	}

	/**
	 * This returns the size of the list (stack).
	 * @return The size of the list (stack). 
	 */
	@Override
	public int size() {
		return list.size();
	}
}
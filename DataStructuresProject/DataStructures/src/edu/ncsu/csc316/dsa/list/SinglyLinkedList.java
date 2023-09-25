package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list.
     * Initializes fields.
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null, null);
        tail = null;
        size = 0;
    }
    
    /**
     * This adds an element at a specified index.
     * @param index Where to add the element.
     * @param element The element to be added.
     */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			front.data = element;
			size++;
			return;
		} else if(index == 1) {
			front.setNext(new LinkedListNode<E>(element, null));
			size++;
			return;
		}
		
		
		LinkedListNode<E> previous = front;
		
		try {
		for(int i = 0; i < index && previous.next != null; i++) {
			previous = previous.next;
		}
		} catch (Exception e) {
			//nothing
		}
		
		LinkedListNode<E> newNode = new LinkedListNode<E>(element, null);
		newNode.next = previous.next;
		previous.next = newNode;
		
		if(tail == null)
			tail = newNode;
		size++;
	}

	/**
	 * This retrieves an element from a given index.
	 * @param index This is the index from which the element is retrieved from.
	 * @return This is the element at the given index.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		LinkedListNode<E> previous = front;
		if(index == 0) {
			if(size == 1)
				return front.getElement();
			return front.next.getElement();
		}	
		for(int i = 0; i < index + 1; i++) {
			previous = previous.getNext();
		}
		return previous.data;
	}

	/**
	 * This removes an element from a given index.
	 * @param index This is the index from which the element is removed from.
	 * @return This is the previous element at the given index.
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		LinkedListNode<E> temp = front;
		LinkedListNode<E> previous = front;
		
		if(index == 0) {
			front = temp.next;
		}
		
		for(int i = 0; temp != null && i < index; i++) {
			temp = temp.getNext();
			if(i == index - 2 && temp != null)
				previous = temp;
			if(index - 2 == -1 && size == 2 && i == index - 1) {
					previous = temp;
					break;
			}
		}
		
		E removed = null;
		
		if(index == this.size() - 1) {
			tail = new LinkedListNode<E>(previous.data, null);
			removed = temp.data;
			previous.next = null;
			if(size == 2) {
				front.next = null;
				tail = front;
			}	
		}
		
		
		if(temp != null && temp.next != null)
		{
			removed = temp.data;
			LinkedListNode<E> next = temp.next.next;
			temp.next = next;
		}

		size--;
		return removed;
	}

	/**
	 * This sets an element at a given index.
	 * @param index This is the index at which the element is set at.
	 * @param element This is the element to set at the given index.
	 * @return This is the previous element at the given index.
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		LinkedListNode<E> previous = front;
		for(int i = 0; i < index; i++) {
			previous.setNext(previous.next);
		}
		E prevElement = previous.getElement();
		previous.data = element;
		return prevElement;
	}

	/**
	 * This returns the size of the list.
	 * @return This is size of the list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.data;
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
    	if(isEmpty())
    		front = new LinkedListNode<E>(element, null);
    	else {
    		add(size, element);
    		size--;
    	}
    	tail = new LinkedListNode<E>(element, null);
    	size++;
    }
    
    /**
     * This is the method which sets the iterator for this list, uses the inner class.
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     * This creates a custom node object for the list nodes.
     * Simplifies implementation for the main class/list.
     * @author Amit Prakash
     *
     * @param <E> This is the element type used in the main class/list.
     */
	private static class LinkedListNode<E> {
        
		/**This is the data stored in the node.*/
        private E data;
        /**This is the reference of the next node in the list.*/
        private LinkedListNode<E> next;
        /**
         * This constructs an object of the node by a given element and the next reference.
         * @param element This is the data to be stored in the object.
         * @param l This is the reference of the next node in the list.
         */
		public LinkedListNode(E element, LinkedListNode<E> l) {
			data = element;
			next = l;
		}
		/**
		 * This gets the data from the node object.
		 * @return data: This is the data of the node object.
		 */
		public E getElement() {
			return data;
		}
		/**
		 * This gets the next node from the node object's list.
		 * @return next: This is the next reference of the node in the list.
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}
		
		/**
		 * This sets the next reference to a given node.
		 * @param l The node to be set as the next reference.
		 */
		public void setNext(LinkedListNode<E> l) {
			next = l;
		}
    }
	
	/**
	 * This iterator helps traverse the list.
	 * Has fields and methods to help with traversal of the list.
	 * @author Amit Prakash
	 */
	private class ElementIterator implements Iterator<E> {
	    /**
	     * Keep track of the next node that will be processed
	     */
	    private LinkedListNode<E> current;
	    
	    /** 
	     * Keep track of the node that was processed on the last call to 'next'
	     */
	    private LinkedListNode<E> previous;
	    
	    /** 
	     * Keep track of the previous-previous node that was processed
	     * so that we can update 'next' links when removing
	     */
	    @SuppressWarnings("unused")
		private LinkedListNode<E> previousPrevious;
	    
	    /**
	     * Keep track of whether it's ok to remove an element (based on whether
	     * next() has been called immediately before remove())
	     */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        current = new LinkedListNode<E>(null,  front);
	        previous = new LinkedListNode<E>(null,  current);
	        previousPrevious = new LinkedListNode<E>(null,  previous);
	        removeOK = false;
	    }

	    /**
	     * This verifies whether or not the iterator can go on.
	     * @return This is whether or not the list has another object.
	     */
	    @Override
	    public boolean hasNext() {
	    	if(size != 0)
	    		return current.next != null;
	    	else
	    		return current.next.next != null;
	    }

	    /**
	     * This is the next element in the list.
	     * @throws java.util.NoSuchElementException This exception is thrown if the list has no next object.
	     * @return The next element in the list is returned.
	     */
	    @Override
	    public E next() throws NoSuchElementException {
	    	previousPrevious = previous;
	    	previous = current;
			current = current.next;
			removeOK = true;
			try {
				return previous.next.getElement();
			} catch(Exception e) {
				throw new NoSuchElementException();
			}
	    }
	     
	    /**
	     * This removes the current element in the list.
	     * @throws java.lang.IllegalStateException If the next method has not been called or if the list has no next element this exception is thrown.
	     */
	    @Override    
	    public void remove() throws IllegalStateException {
	    	if(!removeOK || !this.hasNext())
	        	throw new IllegalStateException();
	    	current.next = current.next.next;
	    	previous.next.next = previous.next.next.next;
	    }
	}
}
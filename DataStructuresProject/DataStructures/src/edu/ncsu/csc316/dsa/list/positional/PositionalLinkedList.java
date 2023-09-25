/**
 * 
 */
package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import java.util.NoSuchElementException;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {
	
	/**
     * This creates a custom node object for the list nodes.
     * Simplifies implementation for the main class/list.
     * @author Amit Prakash
     *
     * @param <E> This is the element type used in the main class/list.
     */
	 private static class PositionalNode<E> implements Position<E> {
		 	/**The element of the node*/
	        private E element;
	        /**The next node reference*/
	        private PositionalNode<E> next;
	        /**The previous node reference*/
	        private PositionalNode<E> previous;

	        /**
	         * Constructs a node with a given element.
	         * @param value The element for the node.
	         */
	        public PositionalNode(E value) {
	            this(value, null);
	        }

	        /**
	         * Constructs a node with a given element and next position.
	         * @param value The element for the node.
	         * @param next The next reference for the next node in the list.
	         */
	        public PositionalNode(E value, PositionalNode<E> next) {
	            this(value, null, next);
	        }
	        
	        /**
	         * Constructs the node with back and forward references and with a given element.
	         * @param value The element for the node.
	         * @param next The next reference for the next node in the list.
	         * @param prev The previous reference for the previous node in the list.
	         */
	        public PositionalNode(E value, PositionalNode<E> prev, PositionalNode<E> next) {
	            setElement(value);
	            setNext(next);
	            setPrevious(prev);
	        }

	        /**
	         * Sets the previous node in the current node.
	         * @param prev The previous node reference for the current node.
	         */
	        public void setPrevious(PositionalNode<E> prev) {
	            this.previous = prev;
	        }

	        /**
	         * Gets the previous node in the current node.
	         * @return previous: The previous node reference for the current node.
	         */
	        public PositionalNode<E> getPrevious() {
	            return previous;
	        }
	        
	        /**
	         * Sets the next node in the current node.
	         * @param next The next node reference for the current node.
	         */
	        public void setNext(PositionalNode<E> next) {
	            this.next = next;
	        }

	        /**
	         * Gets the next node in the current node.
	         * @return next: The next node reference for the current node.
	         */
	        public PositionalNode<E> getNext() {
	            return next;
	        }

	        /**
	         * Gets the element in the current node.
	         * @return element: The element for the current node.
	         */
	        @Override
	        public E getElement() {
	            return element;
	        }
	        
	        /**
	         * Sets the element in the current node.
	         * @param element: The element to be set for the current node.
	         */
	        public void setElement(E element) {
	            this.element = element;
	        }
	    }

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list.
     * Initializes fields.
     */
    public PositionalLinkedList() {
    	front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, front);
        front = new PositionalNode<E>(null, null, null);
        tail = new PositionalNode<E>(null, front, null);
        front.setNext(tail);
        size = 0;
    }

	 /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
	 * This returns the position of a node/element given another node/element.
	 * Used in before and after.
	 * @param node The node to be referenced.
	 * @return The position of the node.
	 */
	private Position<E> position(PositionalNode<E> node) {
		if (node == front || node == tail)
			return null; // do not expose user to the sentinels
		return node;
	}

	/**
	 * Returns the size of the list.
	 * @return size: The size of the list.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * This checks to see if the size of the list is zero.
	 * @return If the size == 0.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
     * This returns an element at a specified position (relative to other positions).
     * This uses the position method for implementation.
     * @return The position of the element.
     */
	@Override
	public Position<E> first() {
		return position(front.getNext());
	}

	/**
     * This returns an element at a specified position (relative to other positions).
     * This uses the position method for implementation.
     * @return The position of the element.
     */
	@Override
	public Position<E> last() {
		return position(tail.getPrevious());
	}

	/**
     * This returns an element at a specified position (relative to other positions).
     * This uses the position method for implementation.
     * @param p This position of the element to be accessed before.
     * @return The position of the element.
     */
	public Position<E> before(Position<E> p) {
		PositionalNode<E> position = validate(p);
		return position(position.getPrevious());
	}
	
	/**
     * This returns an element at a specified position (relative to other positions).
     * This uses the position method for implementation.
     * @param p This position of the element to be accessed after.
     * @return The position of the element.
     */
	public Position<E> after(Position<E> p) {
		PositionalNode<E> position = validate(p);
		return position(position.getNext());
	}
	
	 /**
     * This adds an element at a specified position (relative to other positions).
     * @param element The element to be added.
     * @param next The next position after the specified element.
     * @param prev The previous position before the specified element.
     * @return The position of the element.
     */
    private Position<E> addBetween(E element, PositionalNode<E> prev, PositionalNode<E> next) {
		PositionalNode<E> newest = new PositionalNode<E>(element, prev, next);
		prev.setNext(newest);
		next.setPrevious(newest);
		size++;
		return position(newest);
    }
	
    /**
     * This adds an element at a specified position (relative to other positions).
     * This uses the addBetween method for implementation.
     * @param element The element to be added.
     * @return The position of the element.
     */
	public Position<E> addFirst(E element) {
		return addBetween(element, front, front.getNext());
	}

	/**
     * This adds an element at a specified position (relative to other positions).
     * This uses the addBetween method for implementation.
     * @param element The element to be added.
     * @return The position of the element.
     */
	public Position<E> addLast(E element) {
		return addBetween(element, tail.getPrevious(), tail);
	}
    
	/**
     * This adds an element at a specified position (relative to other positions).
     * This uses the addBetween method for implementation.
     * @param p This position of the element to be added before.
     * @param element The element to be added.
     * @return The position of the element.
     */
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> position = validate(p);
		return addBetween(element, position.getPrevious(), position);
	}
	
    /**
     * This adds an element at a specified position (relative to other positions).
     * This uses the addBetween method for implementation.
     * @param p This position of the element to be added after.
     * @param element The element to be added.
     * @return The position of the element.
     */
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> position = validate(p);
		return addBetween(element, position, position.getNext());
	}
	
	/**
	 * Sets a given position to a given element.
	 * @param p The position to be set.
	 * @param element The element to be set as.
	 * @return The old element at the position.
	 */
	public E set(Position<E> p, E element) {
		PositionalNode<E> node = validate(p);
		E answer = node.getElement( );
		node.setElement(element);
	    return answer;
	}

	/**
	 * Removes a given position from the list.
	 * @param p The position of the element to be removed.
	 * @return The element removed from the list.
	 */
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> predecessor = node.getPrevious( );
		PositionalNode<E> successor = node.getNext( );
		predecessor.setNext(successor);
		successor.setPrevious(predecessor);
		size--;
		E answer = node.getElement( );
		node.setElement(null); // help with garbage collection
		node.setNext(null); // and convention for defunct node
		node.setPrevious(null);
		return answer;
	}

	/**
     * This is the method which sets the iterator for this list, uses the inner class.
     * @return This method returns the element iterator instance.
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
	
	/**
	 * Creates a new iterable instance over the positions of the list.
	 * @return The position iterator for the current instance.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}
	    
	    /**
		 * This iterator helps traverse the list.
		 * Has fields and methods to help with traversal of the list.
		 * @author Amit Prakash
	     *
	     */
	    private class PositionIterator implements Iterator<Position<E>> {

	    	/**The current node*/
	        private Position<E> current;
	        /**The previous (most recent) node*/
	        private Position<E> recent;
	        /**Whether or not remove can be called*/
	        private boolean removeOK;
	        
	        /**
	         * Constructs the iterator object.
	         * Initializes the fields.
	         */
	        public PositionIterator() {
	        	current = first();
	        	recent = null;
	        }

	        /**
		     * This verifies whether or not the iterator can go on.
		     * @return This is whether or not the list has another object.
		     */
	        @Override
	        public boolean hasNext() {
	        	return (current != null);
	        }

	        /**
		     * This is the next element in the list.
		     * @throws java.util.NoSuchElementException This exception is thrown if the list has no next object.
		     * @return The next element in the list is returned.
		     */
	        @Override
	        public Position<E> next() throws NoSuchElementException {
	        	if (current == null) 
	        		throw new NoSuchElementException("nothing left");
	        	recent = current; // element at this position might later be removed
	        	current = after(current);
	        	removeOK = true;
	        	return recent;
	        }

	        /**
		     * This removes the current element in the list.
		     * @throws java.lang.IllegalStateException If the next method has not been called or if the list has no next element this exception is thrown.
		     */
	        @Override
	        public void remove() throws IllegalStateException {
	        	if (recent == null || !removeOK) 
	        		throw new IllegalStateException("nothing to remove");
	        	PositionalLinkedList.this.remove(recent); // remove from outer list
	        	recent = null;
	        }
	    }
	    
	    /**
		 * This iterator helps traverse the list.
		 * Has fields and methods to help with traversal of the list.
		 * @author Amit Prakash
		 */
	    private class ElementIterator implements Iterator<E> {
	    	/**The iterator object for the elements*/
	        private Iterator<Position<E>> it;
	        
	        /**
	         * Constructs the iterator for the positions.
	         * Initializes the iterator object field.
	         */
	        public ElementIterator() {
	            it = new PositionIterator();
	        }

	        /**
		     * This verifies whether or not the iterator can go on.
		     * @return This is whether or not the list has another object.
		     */
	        @Override
	        public boolean hasNext() {
	            return it.hasNext();
	        }
	        
	        /**
		     * This is the next element in the list.
		     * @return The next element in the list is returned.
		     */
	        @Override
	        public E next() {
	            return it.next().getElement();
	        }

	        /**
		     * This removes the current element in the list.
		     */
	        @Override
	        public void remove() {
	            it.remove();
	        }
	    }
	    
	    /**
	     * Provides an iterable object, so the positions can be iterated over within the list itself.
	     * @author Amit Prakash
	     */
	    private class PositionIterable implements Iterable<Position<E>> {
	    	
	        /**
	         * Provides an iterator for the positions.
	         * @return An iterator for the positions.
	         */
	        @Override
	        public Iterator<Position<E>> iterator() {
	            return new PositionIterator();
	        }
	    }
}

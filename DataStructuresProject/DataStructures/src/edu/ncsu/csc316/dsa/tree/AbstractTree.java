package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    /**
     * This checks whether a given node is an internal node.
     * @param p This is the position node to look at.
     * @return This returns true if the node is internal, otherwise false.
     */
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    /**
     * This checks whether a given node is a leaf node.
     * @param p This is the position node to look at.
     * @return This returns true if the node is a leaf, otherwise false.
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    /**
     * This checks whether a given node is a root node.
     * @param p This is the position node to look at.
     * @return This returns true if the node is a root, otherwise false.
     */
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    /**
     * This is true if the tree is empty.
     * @return If the tree is of size 0.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * This sets the position to a given value.
     * @param p This is the position to set the given value at.
     * @param value This is the value to set the position at.
     * @return This is the original value at the position.
     */
    @Override
    public E set(Position<E> p, E value) {
    	E orgVal = validate(p).getElement();
        validate(p).setElement(value);
        return orgVal;
    }   
    
    /**
	 * This traverses through the tree with pre-order.
	 * @return traversal The traversal made through the tree beginning to end.
	 */
    @Override
    public Iterable<Position<E>> preOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }   
    
    /**
     * This helps preOrder() by choosing between trees.
     * @param p This is the position of the traversal.
     * @param traversal This is the traversal to add traversing positions in.
     */
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        traversal.add(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    } 
    
    /**
	 * This traverses through the tree with post-order.
	 * @return traversal The traversal made through the tree beginning to end.
	 */
    @Override
    public Iterable<Position<E>> postOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            postOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * This helps postOrder() by choosing between trees.
     * @param p This is the position of the traversal.
     * @param traversal This is the traversal to add traversing positions in.
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
        for (Position<E> c : children(p)) {
            postOrderHelper(c, traversal);
        }
        traversal.add(p);
    }

    /**
	 * This traverses through the tree with level-order.
	 * @return traversal The traversal made through the tree beginning to end.
	 */
	@Override
    public Iterable<Position<E>> levelOrder() {
		PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new ArrayBasedQueue<>();
            fringe.enqueue(root());
            while(!fringe.isEmpty()) {
            	Position<E> p = fringe.dequeue();
            	traversal.add(p);
            	for (Position<E> c : children(p)) {
                    fringe.enqueue(c);
                }
            }
        }
        return traversal;
	}    

	/**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     * 
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);
    
    /**
     * This is the tree node for this abstract tree.
     * @author Amit Prakash
     *
     * @param <E> This is the element in the node.
     */
    protected abstract static class AbstractTreeNode<E> implements Position<E> {
    	/**This is the element in the node*/
        private E element;
        
        /**
         * This is the constructor for the class. This sets the element to the given element.
         * @param element This is the given element.
         */
        public AbstractTreeNode(E element) {
            setElement(element);
        }
        
        /**
         * This gives the element for this node.
         * @return element The element for this node.
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * This sets the element to the given element.
         * @param element This is the given element.
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * This displays the tree as a string using toStringHelper() to build the display string.
     * @return This is the tree as a string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * This helps build the toString().
     * @param sb This is the string builder used.
     * @param indent This is the string used to indent in the building.
     * @param root This is the root of the tree.
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {
    	/**This is the list of positions*/
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions or positions with null
         * elements are not added to the collection
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null && position.getElement() != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection.
         * @return The iterator for the position set or tree.
         */
        public Iterator<Position<E>> iterator() {
            return new PositionSetIterator();
        }
        /**
         * This gives an iterator and related methods over a position set.
         * @author Amit Prakash
         *
         */
        private class PositionSetIterator implements Iterator<Position<E>> {
        	/**This is the iterator for the position set given*/
            private Iterator<Position<E>> it;
            
            /**
             * This constructs the object and initializes it as the iterator for list.
             */
            public PositionSetIterator() {
                it = list.iterator();
            }

            /**
             * Checks whether the iterator has another object.
             * @return If the iterator has another object, true is returned, otherwise false.
             */
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            /**
             * This returns the next position.
             * @return The next position.
             */
            @Override
            public Position<E> next() {
                return it.next();
            }

            /**
             * This operation is not yet supported.
             * @throws UnsupportedOperationException This operation is not supported yet.
             */
            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
}
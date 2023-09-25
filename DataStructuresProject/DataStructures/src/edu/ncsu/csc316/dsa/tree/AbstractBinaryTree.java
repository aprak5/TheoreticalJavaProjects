package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
	/**
	 * This traverses through the tree with in-order.
	 * @return traversal The traversal made through the tree beginning to end.
	 */
    @Override
    public Iterable<Position<E>> inOrder() {
    	PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * This helps inOrder() by choosing between trees.
     * @param p This is the position of the traversal.
     * @param traversal This is the traversal to add traversing positions in.
     */
    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
        if(left(p) != null)
        	inOrderHelper(left(p), traversal);
        traversal.add(p);
        if(right(p) != null)
        	inOrderHelper(right(p), traversal);
    } 
    
    /**
     * This is the number of children at a given position.
     * @param p This is the position to find the number of children for.
     * @return This is the number of children of the given node.
     */
    @Override
    public int numChildren(Position<E> p) {
        int count = 0;
        if(left(p) != null)
        	count++;
        if(right(p) != null)
        	count++;
        return count;
    }
    
    /**
     * This is the sibling of a given position.
     * @param p This is the position to find the sibling for.
     * @return This is the sibling of the given node.
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if(parent == null)
        	return null;
        if(p == left(parent))
        	return right(parent);
        else
        	return left(parent);
    }
    
    /**
     * These are the children of the given position.
     * @param p This is the position to find the children for.
     * @return These are the children of the given node.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}
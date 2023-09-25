package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class LinkedBinaryTreeTest {

	/**The tree for all tests*/
    private LinkedBinaryTree<String> tree;
    /**The first position*/
    private Position<String> one;
    /**The second position*/
    private Position<String> two;
    /**The third position*/
    private Position<String> three;
    /**The fourth position*/
    private Position<String> four;
    /**The fifth position*/
    private Position<String> five;
    /**The sixth position*/
    private Position<String> six;
    /**The seventh position*/
    private Position<String> seven;
    /**The eighth position*/
    private Position<String> eight;
    /**The ninth position*/
    private Position<String> nine;
    /**The tenth position*/
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * 
     * @author Amit Prakash
     * @param <E> The element for the node, which is used in constructing and in methods.
     */
    private class InvalidPosition<E> implements Position<E> {

    	/**
    	 * This returns the element from the node.
    	 * @return null The element for the node.
    	 */
        @Override
        public E getElement() {
            return null;
        }
        
    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        tree.set(one, "1");
        assertEquals("1", tree.root().getElement());
        tree.set(one, "one");
        assertEquals("one", tree.root().getElement());
        tree.setRoot(two);
        assertEquals("two", tree.root().getElement());
        tree.setRoot(one);
        assertEquals("one", tree.root().getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(2, tree.numChildren(ten));
    }

    /**
     * Test the output of the parent(p) behavior 
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(null, tree.parent(one));
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(three, tree.parent(four));
        assertEquals(ten, tree.parent(five));
        assertEquals(two, tree.parent(six));
        assertEquals(ten, tree.parent(seven));
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
        assertEquals(two, tree.parent(ten));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(null, tree.sibling(one));
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        assertEquals(null, tree.sibling(four));
        assertEquals(seven, tree.sibling(five));
        assertEquals(ten, tree.sibling(six));
        assertEquals(five, tree.sibling(seven));
        assertEquals(nine, tree.sibling(eight));
        assertEquals(eight, tree.sibling(nine));
        assertEquals(six, tree.sibling(ten));
    }

    /**
     * Test the output of the isInternal behavior 
     */          
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(six));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
        assertTrue(tree.isInternal(ten));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertFalse(tree.isLeaf(four));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        assertFalse(tree.isLeaf(ten));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
        assertFalse(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(three, it.next());
        assertEquals(four, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertEquals(six, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(ten, it.next());
        assertEquals(two, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertEquals(four, it.next());
        assertEquals(three, it.next());
        assertEquals(one, it.next());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(six, it.next());
        assertEquals(two, it.next());
        assertEquals(seven, it.next());
        assertEquals(ten, it.next());
        assertEquals(five, it.next());
        assertEquals(one, it.next());
        assertEquals(eight, it.next());
        assertEquals(four, it.next());
        assertEquals(nine, it.next());
        assertEquals(three, it.next());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	assertEquals(0, tree.size());
    }
    
    /**
     * Test the output of the levelOrder traversal behavior 
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions, visually shown:
     *                   one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *         /     /   \      /     \
     *     eleven seven  five  eight nine 
     */      
    @Test
    public void testAddLeft() {
    	createTree();
    	Position<String> eleven = tree.addLeft(six, "eleven");
    	Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(eleven, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions, visually shown:
     *                      one
     *                /             \
     *             two             three
     *            /   \            /    \
     *         six   ten          four  eleven
     *               /   \      /     \
     *            seven  five  eight nine 
     */      
    @Test
    public void testAddRight() {
    	createTree();
    	Position<String> eleven = tree.addRight(three, "eleven");
    	Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(eleven, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        InvalidPosition<String> invalidPosition = new InvalidPosition<String>();
        try {
        	tree.addRight(invalidPosition, "invalid");
        	fail();
        } catch(IllegalArgumentException iae) {
        	assertEquals("Position is not a valid linked binary tree node", iae.getMessage());
        	assertNull(invalidPosition.getElement());
        }
        try {
        	it.remove();
        } catch(UnsupportedOperationException iae) {
        	assertEquals("The remove operation is not supported yet.", iae.getMessage());
        }
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        Position<String> eleven = tree.addRight(three, "eleven");
    	Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(eleven, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        tree.remove(eleven);
        Iterator<Position<String>> it2 = tree.levelOrder().iterator();
        assertEquals(one, it2.next());
        assertEquals(two, it2.next());
        assertEquals(three, it2.next());
        assertEquals(six, it2.next());
        assertEquals(ten, it2.next());
        assertEquals(four, it2.next());
        assertEquals(seven, it2.next());
        assertEquals(five, it2.next());
        assertEquals(eight, it2.next());
        assertEquals(nine, it2.next());
        assertEquals("LinkedBinaryTree[\n"
        		+ "one\n"
        		+ " two\n"
        		+ "  six\n"
        		+ "  ten\n"
        		+ "   seven\n"
        		+ "   five\n"
        		+ " three\n"
        		+ "  four\n"
        		+ "   eight\n"
        		+ "   nine\n"
        		+ "]", tree.toString());
    }
}
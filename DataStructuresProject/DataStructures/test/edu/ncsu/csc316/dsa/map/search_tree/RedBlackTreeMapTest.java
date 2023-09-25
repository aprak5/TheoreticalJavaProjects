package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class RedBlackTreeMapTest {
	/**Test tree*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
        BinarySearchTreeMap<Student, String> tree2 = new RedBlackTreeMap<Student, String>(new StudentGPAComparator());
        assertNotNull(tree2);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertTrue(tree.isRoot(tree.root()));
        assertEquals(1, (int)tree.root().getElement().getKey());
        tree.put(2, "two");
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(0, "zero");
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals("one", tree.put(1, "one- modified"));
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("one- modified", tree.root().getElement().getValue());
        tree.put(3, "three");
        assertEquals(3, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(4, "four");
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(5, "five");
        assertEquals(5, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        tree.put(6, "six");
        assertEquals(6, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(7, tree.size());
        assertNotNull(tree.toString());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertTrue(tree.isRoot(tree.root()));
        assertEquals(1, (int)tree.root().getElement().getKey());
        tree.put(2, "two");
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(0, "zero");
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals("one", tree.put(1, "one- modified"));
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("one- modified", tree.root().getElement().getValue());
        tree.put(3, "three");
        assertEquals(3, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(4, "four");
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(5, "five");
        assertEquals(5, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        tree.put(6, "six");
        assertEquals(6, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals("zero", tree.get(0));
        assertEquals("one- modified", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals("six", tree.get(6));
        assertNull(tree.get(7));
        assertEquals(7, tree.size());
        assertNotNull(tree.toString());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertTrue(tree.isRoot(tree.root()));
        assertEquals(1, (int)tree.root().getElement().getKey());
        tree.put(2, "two");
        assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
        tree.put(0, "zero");
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        tree.put(-1, "negative one");
        assertEquals(-1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals("one", tree.put(1, "one- modified"));
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals("one- modified", tree.root().getElement().getValue());
        tree.put(3, "three");
        assertEquals(3, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(4, "four");
        assertEquals(4, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        tree.put(5, "five");
        assertEquals(5, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(7, tree.size());
        assertEquals("zero", tree.remove(0));
        assertEquals(6, tree.size());
        assertEquals("three", tree.remove(3));
        assertEquals(5, tree.size());
        assertEquals("one- modified", tree.remove(1));
        assertEquals(4, tree.size());
        assertEquals("negative one", tree.remove(-1));
        assertEquals(3, tree.size());
        assertEquals("five", tree.remove(5));
        assertEquals(2, tree.size());
        assertEquals("two", tree.remove(2));
        assertEquals(1, tree.size());
        assertEquals("four", tree.remove(4));
        assertEquals(0, tree.size());       
    }
}
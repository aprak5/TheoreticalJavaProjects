package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class TreeSetTest {

    private Set<Integer> set;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(10);
        assertEquals(2, set.size());
        assertFalse(set.isEmpty());
        
        set.add(2);
        assertEquals(3, set.size());
        assertFalse(set.isEmpty());
        
        set.add(3);
        assertEquals(4, set.size());
        assertFalse(set.isEmpty());
        
        set.add(9);
        assertEquals(5, set.size());
        assertFalse(set.isEmpty());
        
        set.add(11);
        assertEquals(6, set.size());
        assertFalse(set.isEmpty());
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
        assertFalse(set.contains(30));
        set.add(30);
        assertTrue(set.contains(30));
        set.remove(30);
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        set.remove(5);
        assertEquals(4, set.size());
        assertFalse(set.isEmpty());
        
        set.remove(15);
        assertEquals(3, set.size());
        assertFalse(set.isEmpty());
        
        set.remove(25);
        assertEquals(2, set.size());
        assertFalse(set.isEmpty());
        
        set.remove(10);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.remove(20);
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.retainAll(other);
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertFalse(set.contains(5));
        assertFalse(set.contains(15));
        assertFalse(set.contains(25));
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the removeAll behavior
     */     
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertFalse(set.contains(10));
        assertFalse(set.contains(20));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        assertFalse(set.contains(30));
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        Iterator<Integer> it = set.iterator();
        assertEquals(5, it.next().intValue());
        assertEquals(10, it.next().intValue());
        assertEquals(15, it.next().intValue());
        assertEquals(20, it.next().intValue());
        assertEquals(25, it.next().intValue());
    }
}
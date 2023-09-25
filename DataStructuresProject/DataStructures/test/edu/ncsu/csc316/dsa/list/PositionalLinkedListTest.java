package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class PositionalLinkedListTest {

    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one"); 
        assertEquals(1, list.size());
        assertEquals(first, list.first());
       
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        Position<String> four = list.addLast("four");
        Position<String> second = list.addAfter(first, "two");
        Position<String> zero = list.addBefore(first, "zero");
        assertEquals(4, list.size());
        assertEquals(zero, list.first());
        assertEquals(four, list.last());
        assertEquals(zero, list.before(first));
        assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(first.getElement(), "one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(first.getElement(), "one");
        assertEquals(1, list.size());
    }
    
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(first.getElement(), "one");
        assertEquals(1, list.size());
        list.set(first, "first");
        assertEquals(first.getElement(), "first");
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        Position<String> four = list.addLast("four");
        Position<String> second = list.addAfter(first, "two");
        Position<String> zero = list.addBefore(first, "zero");
        assertEquals(4, list.size());
        assertEquals(zero, list.first());
        assertEquals(four, list.last());
        assertEquals(zero, list.before(first));
        assertEquals(second, list.after(first));
        list.remove(zero);
        assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
    	// Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.first().getElement());
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        it.remove();
        assertFalse(it.hasNext());
        assertEquals(0, list.size());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
    }

}

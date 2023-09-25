package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class ArrayBasedListTest {

    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        list.addLast("Bye");
        assertEquals("Bye", list.last());
    }


    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	list.addLast("Hi");
        assertEquals("Hi", list.first());
    }


    /**
     * Test the iterator behaviors, including expected exceptions
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
        assertEquals("one", list.get(0));
        
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
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	list.addFirst("a");
    	list.addLast("b");
    	list.addLast("c");
    	list.addLast("d");
    	list.removeLast();
        assertEquals(list.last(), "c");
        list.removeFirst();
        assertEquals(list.first(), "b");
        list.remove(1);
        assertEquals(list.size(), 1);
    }


    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
        list.add(0, "h");
        list.set(0, "Hi!");
        assertEquals("Hi!", list.get(0));
    }
}


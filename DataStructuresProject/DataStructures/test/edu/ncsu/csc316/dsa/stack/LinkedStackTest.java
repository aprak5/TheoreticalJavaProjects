package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for LinkedStack.
 * Checks the expected outputs of the Stack abstract data type behaviors when using
 * a singly-linked list data structure
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class LinkedStackTest {

    private Stack<String> stack;
    
    /**
     * Create a new instance of a linked list-based stack before each test case executes
     */
    @Before
    public void setUp() {
        stack = new LinkedStack<String>();
    }
    
    /**
     * Test the output of the push(e) behavior
     */ 
    @Test
    public void testPush() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        
        stack.push("two");
        stack.push("three");
        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
    }

    /**
     * Test the output of the pop() behavior, including expected exceptions
     */
    @Test
    public void testPop() {
        assertEquals(0, stack.size());
        
        try {
            stack.pop();
            fail("EmptyStackException should have been thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyStackException);
        }
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        
        stack.push("two");
        stack.push("three");
        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
        
        assertEquals(stack.pop(), "three");
        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());
        
        stack.pop();
        stack.pop();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    /**
     * Test the output of the top() behavior, including expected exceptions
     */
    @Test
    public void testTop() { 
        assertEquals(0, stack.size());
        
        stack.push("one");
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        
        stack.push("two");
        stack.push("three");
        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
        
        assertEquals(stack.top(), "three");
        
        stack.pop();
        assertEquals(stack.top(), "two");
        
        stack.pop();
        assertEquals(stack.top(), "one");
    }

}


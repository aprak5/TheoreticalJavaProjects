package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class LinearProbingHashMapTest {

    private Map<Integer, String> map;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        map = new LinearProbingHashMap<Integer, String>(7, true);
        Map<Integer, String> map2 = new LinearProbingHashMap<Integer, String>(7);
        Map<Integer, String> map3 = new LinearProbingHashMap<Integer, String>(false);
        Map<Integer, String> map4 = new LinearProbingHashMap<Integer, String>();
        assertNotNull(map2);
        assertNotNull(map3);
        assertNotNull(map4);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        assertEquals(7, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); 
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(9, (int)it.next().getKey()); 
        assertEquals(3, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey()); 
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        assertEquals(7, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); 
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(9, (int)it.next().getKey()); 
        assertEquals(3, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey()); 
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        assertEquals("string6", map.get(6));
        assertEquals("string7", map.get(7));
        assertEquals("string8", map.get(8));
        assertEquals("string9", map.get(9));
        assertNull(map.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
    	assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        assertNull(map.put(5, "string5"));
        assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        assertEquals(7, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); 
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(9, (int)it.next().getKey()); 
        assertEquals(3, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey());
        assertEquals("string9", map.remove(9));
        assertEquals(6, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(6, (int)it.next().getKey()); 
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(3, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey()); 
        assertEquals("string6", map.remove(6));
        assertEquals(5, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(3, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey());         
        assertEquals("string3", map.remove(3));
        assertEquals(4, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(4, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey());         
        assertEquals("string4", map.remove(4));
        assertEquals(3, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(5, (int)it.next().getKey());         
        assertEquals("string5", map.remove(5));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals("string7", map.remove(7));
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(8, (int)it.next().getKey());          
        assertEquals("string8", map.remove(8));
        assertEquals(0, map.size());
        assertTrue(map.isEmpty()); 
        assertNull(map.remove(5));
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        Iterator<Integer> it = map.iterator();
        assertEquals(6, (int)it.next()); 
        assertEquals(7, (int)it.next());
        assertEquals(8, (int)it.next());
        assertEquals(9, (int)it.next());
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertEquals(6, (int)it.next().getKey()); 
        assertEquals(7, (int)it.next().getKey()); 
        assertEquals(8, (int)it.next().getKey()); 
        assertEquals(9, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertNull(map.put(6, "string6"));
        assertNull(map.put(7, "string7"));
        assertNull(map.put(8, "string8"));
        assertNull(map.put(9, "string9"));
        Iterator<String> it = map.values().iterator();
        assertEquals("string6", it.next()); 
        assertEquals("string7", it.next());
        assertEquals("string8", it.next());
        assertEquals("string9", it.next());
    }
}
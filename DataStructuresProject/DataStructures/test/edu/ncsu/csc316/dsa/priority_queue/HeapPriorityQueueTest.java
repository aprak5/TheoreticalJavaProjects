package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class HeapPriorityQueueTest {
	/**Test heap priority queue of strings and integers*/
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(6, "six");
        assertEquals(2, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(6, (int)heap.min().getKey());
        
        heap.insert(5, "five");
        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(5, (int)heap.min().getKey());
        
        heap.insert(4, "four");
        assertEquals(4, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(5, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(3, (int)heap.min().getKey());
        
        heap.insert(1, "one");
        assertEquals(6, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(1, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        assertNull(heap.min());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(6, "six");
        assertEquals(2, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(6, (int)heap.min().getKey());
        
        heap.insert(5, "five");
        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(5, (int)heap.min().getKey());
        
        heap.insert(4, "four");
        assertEquals(4, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(5, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(3, (int)heap.min().getKey());
        
        heap.insert(1, "one");
        assertEquals(6, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(1, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(6, "six");
        assertEquals(2, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(6, (int)heap.min().getKey());
        
        heap.insert(5, "five");
        assertEquals(3, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(5, (int)heap.min().getKey());
        
        heap.insert(4, "four");
        assertEquals(4, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(4, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(5, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(3, (int)heap.min().getKey());
        
        heap.insert(1, "one");
        assertEquals(6, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(1, (int)heap.min().getKey());
        
        heap.deleteMin();
        assertEquals(3, (int)heap.min().getKey());
        heap.deleteMin();
        assertEquals(4, (int)heap.min().getKey());
        heap.deleteMin();
        assertEquals(5, (int)heap.min().getKey());
        heap.deleteMin();
        assertEquals(6, (int)heap.min().getKey());
        heap.deleteMin();
        assertEquals(8, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        sHeap.insert(s1, "student one");
        sHeap.insert(s2, "student two");
        sHeap.insert(s3, "student three");
        sHeap.insert(s4, "student four");
        sHeap.insert(s5, "student five");
        assertEquals(5, sHeap.size());
    }
}
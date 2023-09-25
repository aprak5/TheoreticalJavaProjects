package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class HeapAdaptablePriorityQueueTest {
	/**Test heap adaptable priority queue of strings and integers*/
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e7,  -6);
        assertEquals(-6, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e6,  -7);
        assertEquals(-7, (int)heap.min().getKey());
        assertEquals("six", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e5,  -8);
        assertEquals(-8, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e4,  -9);
        assertEquals(-9, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e3,  -10);
        assertEquals(-10, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e2,  -11);
        assertEquals(-11, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e1,  -12);
        assertEquals(-12, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e0,  -13);
        assertEquals(-13, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        assertEquals("eight",  e8.getValue());
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals("one",  e1.getValue());
        assertEquals("two",  e2.getValue());
        assertEquals("three",  e3.getValue());
        assertEquals("four",  e4.getValue());
        assertEquals("five",  e5.getValue());
        assertEquals("six",  e6.getValue());
        assertEquals("seven",  e7.getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        heap.replaceValue(e7,  "SEVEN");
        heap.replaceValue(e6,  "SIX");
        heap.replaceValue(e5,  "FIVE");
        heap.replaceValue(e4,  "FOUR");
        heap.replaceValue(e3,  "THREE");
        heap.replaceValue(e2,  "TWO");
        heap.replaceValue(e1,  "ONE");
        heap.replaceValue(e0,  "ZERO");
        assertEquals("SEVEN",  e7.getValue());
        assertEquals("SIX",  e6.getValue());
        assertEquals("FIVE",  e5.getValue());
        assertEquals("FOUR",  e4.getValue());
        assertEquals("THREE",  e3.getValue());
        assertEquals("TWO",  e2.getValue());
        assertEquals("ONE",  e1.getValue());
        assertEquals("ZERO",  e0.getValue());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.remove(e1);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(7, heap.size());
        
        heap.remove(e2);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(6, heap.size());
        
        heap.remove(e3);
        assertEquals(4, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
        assertEquals(5, heap.size());
        
        heap.remove(e4);
        assertEquals(5, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(4, heap.size());
        
        heap.remove(e5);
        assertEquals(6, (int)heap.min().getKey());
        assertEquals("six", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.remove(e6);
        assertEquals(7, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(2, heap.size());
        
        heap.remove(e7);
        assertEquals(8, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(1, heap.size());
        
        heap.remove(e8);
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        Entry<Student, String> e1 = sHeap.insert(s1, "student one");
        Entry<Student, String> e2 = sHeap.insert(s2, "student two");
        Entry<Student, String> e3 = sHeap.insert(s3, "student three");
        Entry<Student, String> e4 = sHeap.insert(s4, "student four");
        Entry<Student, String> e5 = sHeap.insert(s5, "student five");
        assertEquals(5, sHeap.size());
        sHeap.remove(e1);
        sHeap.remove(e2);
        sHeap.remove(e3);
        sHeap.remove(e4);
        sHeap.remove(e5);
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
    }
}
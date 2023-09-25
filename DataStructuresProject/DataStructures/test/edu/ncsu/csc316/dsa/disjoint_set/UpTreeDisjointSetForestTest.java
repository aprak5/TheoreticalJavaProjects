package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class UpTreeDisjointSetForestTest {
	/**Test set*/
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        Position<String> four = set.makeSet("four");
        assertEquals("four", four.getElement());
        Position<String> five = set.makeSet("five");
        assertEquals("five", five.getElement());
        Position<String> six = set.makeSet("six");
        assertEquals("six", six.getElement());
        Position<String> seven = set.makeSet("seven");
        assertEquals("seven", seven.getElement());
        Position<String> eight = set.makeSet("eight");
        assertEquals("eight", eight.getElement());
        Position<String> nine = set.makeSet("nine");
        assertEquals("nine", nine.getElement());
        Position<String> ten = set.makeSet("ten");
        assertEquals("ten", ten.getElement());
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(three, set.find("three"));
        assertEquals(four, set.find("four"));
        assertEquals(five, set.find("five"));
        assertEquals(six, set.find("six"));
        assertEquals(seven, set.find("seven"));
        assertEquals(eight, set.find("eight"));
        assertEquals(nine, set.find("nine"));
        assertEquals(ten, set.find("ten"));
        
        set.union(set.find("one"), set.find("two"));
        set.union(set.find("two"), set.find("three"));
        set.union(set.find("three"), set.find("four"));
        set.union(set.find("one"), set.find("five"));
        set.union(set.find("seven"), set.find("six"));
        set.union(set.find("two"), set.find("six"));
        set.union(set.find("eight"), set.find("nine"));
        set.union(set.find("nine"), set.find("ten"));
        set.union(set.find("eight"), set.find("two"));
    }
}
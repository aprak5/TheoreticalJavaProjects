package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * This class tests {@link StudentGPAComparator}.
 * @author Amit Prakash
 */
public class StudentGPAComparatorTest {
	/**Test student one.*/
	private Student sOne;
	/**Test student two.*/
	private Student sTwo;
	/**Test student three.*/
	private Student sThree;
	/**Test comparator.*/
	private StudentGPAComparator comparator;
	/**
	 * This sets up (initializes) the objects and the comparator.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = sOne;
		comparator = new StudentGPAComparator();
	}
	/**
	 * This tests the compare() method to make sure the correct order is achieved. Done by confirming right output by method output.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sOne, sTwo) > 0);
		assertFalse(comparator.compare(sTwo, sOne) > 0);
		assertTrue(comparator.compare(sOne, sThree) == 0);
	}
}

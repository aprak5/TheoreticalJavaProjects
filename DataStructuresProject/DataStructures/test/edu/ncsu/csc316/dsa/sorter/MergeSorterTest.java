/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Tests the class {@link MergeSorter}.
 * @author Amit Prakash
 */
public class MergeSorterTest {

	/**Test student one.*/
	private Student sOne;
	/**Test student two.*/
	private Student sTwo;
	/**Test student three.*/
	private Student sThree;
	/**Test student four.*/
	private Student sFour;
	/**Test student five.*/
	private Student sFive;
	/**The test counting sorter object for students.*/
	private MergeSorter<Student> sorter;
	/**
	 * This initializes the student sorter for the string sorting.
	 * This also initializes the test students.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new MergeSorter<Student>(new StudentIDComparator());
	}
	/**
	 * Makes sure the correct data is given on all three student arrays when sorted, by comparing expected and output values.
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
}

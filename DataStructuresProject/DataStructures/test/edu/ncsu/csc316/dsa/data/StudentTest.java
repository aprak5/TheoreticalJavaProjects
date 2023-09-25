package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * This class tests {@link Student}.
 * @author Amit Prakash
 */
public class StudentTest {
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
	/**Test student six.*/
	private Student sSix;
	/**
	 * This initializes the test students.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "OneLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "OneLast", 4, 4, 4.0, "fourUnityID");
		sFive = sOne;  
		sSix = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
	}
	/**
	 * This tests the setter for the first name, by setting the first name and checking the value.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}
	/**
	 * This tests the setter for the last name, by setting the last name and checking the value.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}
	/**
	 * This tests the setter for the number ID, by setting the number ID and checking the value.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}
	/**
	 * This tests the setter for the GPA, by setting the GPA and checking the value.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	/**
	 * This tests the setter for the unity ID, by setting the unity ID and checking the value.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}
	/**
	 * This tests the compareTo() method of the student by checking output with expected input output values.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
		assertTrue(sOne.compareTo(sThree) < 0);
		assertTrue(sOne.compareTo(sFour) > 0);
	}
	/**
	 * This tests whether the correct equality can be established between two students, given they have matching names and number IDs.
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.hashCode() == (sFive.hashCode()));
		assertTrue(sOne.equals(sFive));
		Object obj = new Object();
		assertFalse(sOne.equals(obj));
		assertTrue(sOne.equals(sSix));
	}
	/**
	 * Tests the toString() method by calling to String and checking the format of the returning string.
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), "Student [first=" + sOne.getFirst() 
		+ ", last=" + sOne.getLast() + ", id=" + sOne.getId() + ", creditHours=" + sOne.getCreditHours() + ", gpa="
				+ sOne.getGpa() + ", unityID=" + sOne.getUnityID() + "]");
	}	
}

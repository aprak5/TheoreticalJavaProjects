package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.io.StudentReader;
/**
 * Tests the class {@link InsertionSorter}.
 * @author Amit Prakash
 */
public class InsertionSorterTest {
	/**An ascending test array of strings.*/
	private String[] dataAscending = { "1", "2", "3", "4", "5" };
	/**A descending test array of strings.*/
	private String[] dataDescending = { "5", "4", "3", "2", "1" };
	/**A random order test array of strings.*/
	private String[] dataRandom = { "4", "1", "5", "3", "2" };
	/**An ascending test array of students.*/
	private Student[] studentsAscending = StudentReader.readInputAsArray("input/student_ascendingID.csv");
	/**A descending test array of students.*/
	private Student[] studentsDescending = StudentReader.readInputAsArray("input/student_descendingID.csv");
	/**A random order test array of students.*/
	private Student[] studentsRandom = StudentReader.readInputAsArray("input/student_randomOrder.csv");
	/**The test insertion sorter object for strings.*/
	private InsertionSorter<Student> studentSorter = new InsertionSorter<Student>(new StudentGPAComparator());
	/**The test insertion sorter object for students.*/
	private InsertionSorter<String> stringSorter;
	/**
	 * This initializes the string sorter for the string sorting.
	 */
	@Before
	public void setUp() {
		stringSorter = new InsertionSorter<String>();
	}
	/**
	 * Makes sure the correct data is given on all three string arrays when sorted, by comparing expected and output values.
	 */
	@Test
	public void testSortIntegers() {
		stringSorter.sort(dataAscending);
		assertEquals("1", dataAscending[0]);
		assertEquals("2", dataAscending[1]);
		assertEquals("3", dataAscending[2]);
		assertEquals("4", dataAscending[3]);
		assertEquals("5", dataAscending[4]);

		stringSorter.sort(dataDescending);
		assertEquals("1", dataDescending[0]);
		assertEquals("2", dataDescending[1]);
		assertEquals("3", dataDescending[2]);
		assertEquals("4", dataDescending[3]);
		assertEquals("5", dataDescending[4]);

		stringSorter.sort(dataRandom);
		assertEquals("1", dataRandom[0]);
		assertEquals("2", dataRandom[1]);
		assertEquals("3", dataRandom[2]);
		assertEquals("4", dataRandom[3]);
		assertEquals("5", dataRandom[4]);
	}
	/**
	 * Makes sure the correct data is given on all three student arrays when sorted, by comparing expected and output values.
	 */
	@Test
	public void testSortStudents() {
		studentSorter.sort(studentsAscending);
		studentSorter.sort(studentsDescending);
		studentSorter.sort(studentsRandom);
		
		assertEquals("3.63", String.valueOf(studentsAscending[0].getGpa()));
		assertEquals("3.49", String.valueOf(studentsAscending[1].getGpa()));
		assertEquals("3.34", String.valueOf(studentsAscending[2].getGpa()));
		assertEquals("3.11", String.valueOf(studentsAscending[3].getGpa()));
		assertEquals("2.95", String.valueOf(studentsAscending[4].getGpa()));
		assertEquals("2.94", String.valueOf(studentsAscending[5].getGpa()));
		assertEquals("2.72", String.valueOf(studentsAscending[6].getGpa()));
		assertEquals("2.25", String.valueOf(studentsAscending[7].getGpa()));
		assertEquals("1.57", String.valueOf(studentsAscending[8].getGpa()));
		assertEquals("1.23", String.valueOf(studentsAscending[9].getGpa()));
		assertEquals("1.1", String.valueOf(studentsAscending[10].getGpa()));
		assertEquals("0.9", String.valueOf(studentsAscending[11].getGpa()));
		assertEquals("0.62", String.valueOf(studentsAscending[12].getGpa()));
		assertEquals("0.6", String.valueOf(studentsAscending[13].getGpa()));
		assertEquals("0.56", String.valueOf(studentsAscending[14].getGpa()));
		assertEquals("0.4", String.valueOf(studentsAscending[15].getGpa()));
		
		assertEquals("3.63", String.valueOf(studentsDescending[0].getGpa()));
		assertEquals("3.49", String.valueOf(studentsDescending[1].getGpa()));
		assertEquals("3.34", String.valueOf(studentsDescending[2].getGpa()));
		assertEquals("3.11", String.valueOf(studentsDescending[3].getGpa()));
		assertEquals("2.95", String.valueOf(studentsDescending[4].getGpa()));
		assertEquals("2.94", String.valueOf(studentsDescending[5].getGpa()));
		assertEquals("2.72", String.valueOf(studentsDescending[6].getGpa()));
		assertEquals("2.25", String.valueOf(studentsDescending[7].getGpa()));
		assertEquals("1.57", String.valueOf(studentsDescending[8].getGpa()));
		assertEquals("1.23", String.valueOf(studentsDescending[9].getGpa()));
		assertEquals("1.1", String.valueOf(studentsDescending[10].getGpa()));
		assertEquals("0.9", String.valueOf(studentsDescending[11].getGpa()));
		assertEquals("0.62", String.valueOf(studentsDescending[12].getGpa()));
		assertEquals("0.6", String.valueOf(studentsDescending[13].getGpa()));
		assertEquals("0.56", String.valueOf(studentsDescending[14].getGpa()));
		assertEquals("0.4", String.valueOf(studentsDescending[15].getGpa()));
		
		assertEquals("3.63", String.valueOf(studentsRandom[0].getGpa()));
		assertEquals("3.49", String.valueOf(studentsRandom[1].getGpa()));
		assertEquals("3.34", String.valueOf(studentsRandom[2].getGpa()));
		assertEquals("3.11", String.valueOf(studentsRandom[3].getGpa()));
		assertEquals("2.95", String.valueOf(studentsRandom[4].getGpa()));
		assertEquals("2.94", String.valueOf(studentsRandom[5].getGpa()));
		assertEquals("2.72", String.valueOf(studentsRandom[6].getGpa()));
		assertEquals("2.25", String.valueOf(studentsRandom[7].getGpa()));
		assertEquals("1.57", String.valueOf(studentsRandom[8].getGpa()));
		assertEquals("1.23", String.valueOf(studentsRandom[9].getGpa()));
		assertEquals("1.1", String.valueOf(studentsRandom[10].getGpa()));
		assertEquals("0.9", String.valueOf(studentsRandom[11].getGpa()));
		assertEquals("0.62", String.valueOf(studentsRandom[12].getGpa()));
		assertEquals("0.6", String.valueOf(studentsRandom[13].getGpa()));
		assertEquals("0.56", String.valueOf(studentsRandom[14].getGpa()));
		assertEquals("0.4", String.valueOf(studentsRandom[15].getGpa()));
	}
}	

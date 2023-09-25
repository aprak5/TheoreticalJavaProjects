package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
/**
 * Tests the class {@link StudentReader}.
 * @author Amit Prakash
 */
public class StudentReaderTest {
	/**
	 * Tests whether the correct output students are given from a given file, by checking that each student is present from the file in the output.
	 */
	@Test
	public void testReadFile() {
		StudentReader sr = new StudentReader();
		assertNotNull(sr);
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	/**
	 * Tests that a student cannot by constructed in the case of missing fields.
	 */
	@Test
	public void testReadIncorrectFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID_IncorrectV1.csv");
		assertNull(contents[0]);
		assertNull(contents[1]);
		assertNull(contents[2]);
		assertNull(contents[3]);
		assertNull(contents[4]);
		assertNull(contents[5]);
	}
}

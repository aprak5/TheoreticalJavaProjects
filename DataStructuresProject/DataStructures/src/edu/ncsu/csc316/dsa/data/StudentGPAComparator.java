package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on GPA.
 * Used in the AbstractComparisonSorter concrete classes to specify how to compare/sort the students.
 * @author Dr. King and Amit Prakash
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 * Uses Double.compare() to compare the GPAs of both the students and return a result.
	 * Overridden to have a custom implementation of compare() where GPA is prioritized instead of first and last name or id.
	 * @return result: The resulting integer describes where student one is in relation to student two (sequentially).
	 */
	@Override
	public int compare(Student one, Student two) {
		return Double.compare(two.getGpa(), one.getGpa());
	}
}
package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number.
 * Used in the AbstractComparisonSorter concrete classes to specify how to compare/sort the students.
 * @author Dr. King and Amit Prakash
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * Uses Integer.compare() to compare the ids of both the students and return a result.
	 * Overridden to have a custom implementation of compare() where id is prioritized instead of first or last name.
	 * @return result: The resulting integer describes where student one is in relation to student two (sequentially).
	 */
	@Override
	public int compare(Student one, Student two) {
		return Integer.compare(one.getId(), two.getId());
	}
}
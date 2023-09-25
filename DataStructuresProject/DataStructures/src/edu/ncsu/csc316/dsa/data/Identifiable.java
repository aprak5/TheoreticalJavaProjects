package edu.ncsu.csc316.dsa.data;

/**
 * An identifiable object that has an ID number (integer).
 * Creates a common platform to identify and compare objects (via ID number).
 * @author Dr. King and Amit Prakash
 */
public interface Identifiable {
	/**
	 * Retrieves the ID number for the given object.
	 * @return id: The returned integer is the ID number of the object.
	 */
	int getId();
}
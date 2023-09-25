package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior.
 * @author Dr. King and Amit Prakash
 * @param <E> This is the generic element parameter.
 */
public interface Sorter<E> {
	
	/**
	 * This is a public abstract method that is implemented in CountingSorter, RadixSorter, and AbstractComparisionSorter.
	 * This takes an array of numbers and sorts them, and it changes the contents.
	 * @param data The integer array of numbers to be sorted.
	 */
	void sort(E[] data);
}

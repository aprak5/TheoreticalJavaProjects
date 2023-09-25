package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
*  This class also is a concrete class of {@link AbstractComparisonSorter}.
 * @author Dr. King and Amit Prakash
 * @param <E> Only comparable elements can be used in this class.
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * This uses the constructor of {@link AbstractComparisonSorter} with a parameter of null to use NaturalOrder sorting.
	 */
    public InsertionSorter() {
        super(null);
    }
    /**
     * This uses the constructor of {@link AbstractComparisonSorter} with a given comparator for custom ordering.
     * @param comparator The comparator to use to order elements.
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    /**
     * This sorts the array of elements according to the insertion sorting algorithm.
     * @param data This is the array of elements to sort.
     */
    public void sort(E[] data) {
		for(int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while(j >= 0 && super.compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = x;
		}
	}
}

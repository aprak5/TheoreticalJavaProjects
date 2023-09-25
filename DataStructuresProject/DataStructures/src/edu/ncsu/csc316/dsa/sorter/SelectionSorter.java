package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data.
 * This class also is a concrete class of {@link AbstractComparisonSorter}.
 * @author Dr. King and Amit Prakash
 *
 * @param <E> Only comparable elements can be used in this class.
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * This uses the constructor of {@link AbstractComparisonSorter} with a parameter of null to use NaturalOrder sorting.
	 */
    public SelectionSorter() {
    	super(null);
    }
    /**
     * This uses the constructor of {@link AbstractComparisonSorter} with a given comparator for custom ordering.
     * @param comparator The comparator to use to order elements.
     */
	public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
	/**
     * This sorts the array of elements according to the selection sorting algorithm.
     * @param data This is the array of elements to sort.
     */
    public void sort(E[] data) {
    	for(int i = 0; i < data.length; i++) {
    		int min = i;
    		for(int j = i + 1; j < data.length; j++) {
    			if(super.compare(data[j], data[min]) < 0)
    				min = j;
    		}
    		if(i != min) {
    			E x = data[i];
    			data[i] = data[min];
    			data[min] = x;
    		}
    	}	
    }
}
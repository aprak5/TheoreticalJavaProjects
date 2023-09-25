package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class uses the bubble sorting algorithm to sort a given array of elements.
 * This class also is a concrete class of {@link AbstractComparisonSorter}.
 * @author Amit Prakash
 * @param <E> Only comparable elements can be used in this class.
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * This uses the constructor of {@link AbstractComparisonSorter} with a parameter of null to use NaturalOrder sorting.
	 */
	public BubbleSorter() {
        super(null);
    }
    /**
     * This uses the constructor of {@link AbstractComparisonSorter} with a given comparator for custom ordering.
     * @param comparator The comparator to use to order elements.
     */
    public BubbleSorter(Comparator<E> comparator) {
        super(comparator);
    }
    /**
     * This sorts the array of elements according to the bubble sorting algorithm.
     * @param data This is the array of elements to sort.
     */
    public void sort(E[] data) {	
    	boolean r = true;
    	while(r) {
    		r = false;
    		for(int i = 1; i < data.length; i++) {
    			if(super.compare(data[i], data[i - 1]) < 0) {
    				E x = data[i - 1];
    				data[i - 1] = data[i];
    				data[i] = x;
    				r = true;
    			}
    		}
    	}
	}
}
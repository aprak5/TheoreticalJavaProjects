package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(n logn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering.
     */ 
    public MergeSorter() {
        this(null);
    }

    /**
     * This sorts the given array of elements, using the merge sort algorithm.
     * @param data This is the array of elements to be sorted.
     */
	@Override
	public void sort(E[] data) {
		if(data.length < 2)
			return;
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
	}
    
	/**
	 * This merges the given arrays of elements.
	 * @param left The left array to merge.
	 * @param right The right array to merge.
	 * @param data The original array of elements.
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIndex = 0;
		int rightIndex = 0;
		
		while(leftIndex + rightIndex < data.length) {
			if(rightIndex == right.length || (leftIndex < left.length && super.compare(left[leftIndex], right[rightIndex]) < 0)) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			}	
			else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}
}
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

    /**Tracks the client's chosen PivotSelector*/
    private PivotSelector selector;
    
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * This method selects the pivot selector, based on the given and default selector.
     * @param selector This is the given pivot selector.
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }
    
    /**
     * This sorts the given array of elements, using quickSort().
     * @param data This is the array of elements to be sorted.
     */
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}

	/**
	 * This sorts the given range of indices in the given array of elements.
	 * @param data The given array of elements.
	 * @param low The lowest index in the array of elements.
	 * @param high The highest index in the array of elements. 
	 */
	private void quickSort(E[] data, int low, int high) {
		if(low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
		    quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * This partitions the given range of indices in the given array of elements, using the chosen PivotSelector.
	 * @param data The given array of elements.
	 * @param low The lowest index in the array of elements.
	 * @param high The highest index in the array of elements. 
	 * @return The index of the pivot.
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(data, pivotIndex, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * This helps partition the given range of indices in the given array of elements, using the compare method.
	 * @param data The given array of elements.
	 * @param low The lowest index in the array of elements.
	 * @param high The highest index in the array of elements. 
	 * @return The index of the pivot.
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int index = low;
		for(int j = low; j < high; j++) {
			if(super.compare(data[j], pivot) <= 0) {
				swap(data, index, j);
				index++;
			}
		}
		swap(data, index, high);
		return index;
	}
	
	/**
	 * This swaps two given indices in the given array of elements.
	 * @param data The given array of elements. 
	 * @param indexOne The index of one element to swap.
	 * @param indexTwo The index of two element to swap.
	 */
	private void swap(E[] data, int indexOne, int indexTwo) {
		if(indexOne == data.length || indexTwo == data.length)
			return;
		E copyIndexOne = data[indexOne];
		data[indexOne] = data[indexTwo];
		data[indexTwo] = copyIndexOne;
	}
	
	
	 /**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    public static class FirstElementSelector implements PivotSelector {
        /**
         * This selects the pivot based on the first index.
         * @param low This is the lowest index given.
         * @param high This is the highest index given.
         * @return low: This is the lowest index returned.
         */
        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Amit Prakash
     *
     */
    public static class LastElementSelector implements PivotSelector {
    	/**
         * This selects the pivot based on the last index.
         * @param low This is the lowest index given.
         * @param high This is the highest index given.
         * @return high: This is the highest index returned.
         */
        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Amit Prakash
     *
     */
    public static class MiddleElementSelector implements PivotSelector {
    	/**
         * This selects the pivot based on the middle index.
         * @param low This is the lowest index given.
         * @param high This is the highest index given.
         * @return This is the middle index returned.
         */
        @Override
        public int selectPivot(int low, int high) {
            return (low + high) / 2;
        }
    }
    
    /**
     * RandomElementSelector chooses a random index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Amit Prakash
     *
     */
    public static class RandomElementSelector implements PivotSelector {
    	/**
         * This selects the pivot based on a random index.
         * @param low This is the lowest index given.
         * @param high This is the highest index given.
         * @return This is a random index returned.
         */
        @Override
        public int selectPivot(int low, int high) {
        	Random randomNumGenerator = new Random();
            return randomNumGenerator.nextInt((high - low) + 1) + low;
        }
    }
}

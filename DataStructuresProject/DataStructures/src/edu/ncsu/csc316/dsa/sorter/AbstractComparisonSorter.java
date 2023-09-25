package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class defines the compare() method for all the comparison-based sorting methods.
 * The child concrete classes for this class are InsertionSorter, SelectionSorter, BubbleSorter.
 * This class uses the comparator to define the order of the comparing of the given elements.
 * The class implements the sort() method from Sorter.
 * @author Amit Prakash
 * @param <E> The class uses a Wrapper of E, which is comparable.
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/**The comparator used to compare the elements.*/
    private Comparator<E> comparator;
    /**
     * This constructs the object and sets the comparator field to the given comparator. 
     * @param comparator The comparator to set.
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    /**
     * This sets the comparator for the object. 
     * If the comparator is null, the NaturalOrder comparator is used, otherwise the given comparator is used.
     * @param comparator The comparator to set.
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    /**
     * This inner class defines the natural (default) order as a default comparator for any comparison sorter.
     * This inner class implements the compare() method from Comparator<E>.
     * @author Amit Prakash
     */
    private class NaturalOrder implements Comparator<E> {
    	/**
    	 * This method overrides the compare() method from Comparator<E>.
    	 * This method defines a way to compare and therefore sort elements.
    	 * @param first The first element to be compared.
    	 * @param second The second element to be compared.
    	 * @return integer: The integer indicating the first element's relation to the second element.
    	 */
    	@Override
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    /**
     * This method defines a way to compare and therefore sort elements.
     * This method uses the compare() method of the comparator given in the class field comparator.
     * @param data1 The first element to be compared.
     * @param data2 The second element to be compared.
     * @return integer: The integer indicating the first element's relation to the second element.
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}

package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data.
 * This class implements the Sorter interface to implement the sort() method.
 * @author Dr. King and Amit Prakash
 *
 * @param <E> Only identifiable elements can be used in this class.
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
	/**
	 * This method uses the counting sort algorithm to sort data (an array of elements).
	 * This method overrides/implements the method from Sorter. 
	 * @param data An array of elements to sort.
	 */
	@Override
	public void sort(E[] data) {
        // Since we constrained E to be Identifiable,
        // we can now access the .getId() method of E objects
        // from within this sort method
		
		int min = data[0].getId();
		int max = data[0].getId();		
		for(int i = 0; i < data.length; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}		
		int k = max - min + 1;		
		int[] b = new int[k];		
		for(int i = 0; i < data.length; i++) {
			b[data[i].getId() - min] = b[(data[i].getId() - min)] + 1;
		}		
		for(int i = 1; i < k; i++) {
			b[i] = b[i - 1] + b[i];
		}		
		@SuppressWarnings("unchecked")
		E[] f = (E[])(new Identifiable[data.length]);
		for(int i = data.length - 1; i >= 0; i--) {
			f[b[data[i].getId() - min] - 1] = data[i];
			b[data[i].getId() - min] = b[data[i].getId() - min] - 1;
		}
		for(int i = 0; i < data.length; i++) {
			data[i] = f[i];
		}
    }
}

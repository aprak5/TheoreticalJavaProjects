package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A HeapPriorityQueue is an array-based min-heap implementation of the
 * {@link PriorityQueue} abstract data type. HeapPriorityQueue ensures a O(logn)
 * worst-case runtime for {@link PriorityQueue#insert} and
 * {@link PriorityQueue#deleteMin}. HeapPriorityQueue ensures a O(1) worst-case
 * runtime for {@link PriorityQueue#min}, {@link PriorityQueue#size}, and
 * {@link PriorityQueue#isEmpty}.
 * 
 * The HeapPriorityQueue class is based on an implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <K> the type of keys (priorities) stored in the priority queue
 * @param <V> the type of values that are associated with keys in the priority
 *            queue
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    /**
     * The internal array-based list used to model the heap
     */
    protected ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new HeapPriorityQueue using a custom comparator
     * 
     * @param comparator Compares the custom Comparator to use when comparing keys (priorities)
     */
    public HeapPriorityQueue(Comparator<K> comparator) {
        super(comparator);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * Constructs a new HeapPriorityQueue that compares keys (priorities) using the
     * natural ordering of the key type
     */
    public HeapPriorityQueue() {
        this(null);
    }

    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in determining parent or children in
    // an array-based implementation of a min-heap
    //////////////////////////////////////////////////

    /**
     * Returns the index of the parent of the entry at the given index
     * 
     * @param index the index of the entry for which to return a reference to its
     *              parent
     * @return the index of the parent of the entry at the given index
     */
    protected int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child of the entry at the given index
     * 
     * @param index the index of the entry for which to return a reference to its
     *              left child
     * @return the index of the left child of the entry at the given index
     */
    protected int left(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child of the entry at the given index
     * 
     * @param index the index of the entry for which to return a reference to its
     *              right child
     * @return the index of the right child of the entry at the given index
     */
    protected int right(int index) {
        return 2 * index + 2;
    }

    /**
     * Returns true if the entry at the given index has a left child
     * 
     * @param index the index of the entry for which to check for a left child
     * @return true if the entry at the given index has a left child; otherwise,
     *         return false
     */
    protected boolean hasLeft(int index) {
        return left(index) < list.size();
    }

    /**
     * Returns true if the entry at the given index has a right child
     * 
     * @param index the index of the entry for which to check for a right child
     * @return true if the entry at the given index has a right child; otherwise,
     *         return false
     */
    protected boolean hasRight(int index) {
        return right(index) < list.size();
    }

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////
    /**
     * This returns the entry inserted into the priority queue.
     * @param key The key of the entry to be inserted.
     * @param value The value of the entry to be inserted.
     * @return The entry inserted.
     */
    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> temp = createEntry(key, value);
        list.addLast(temp);
        upHeap(list.size() - 1);
        return temp;
    }
    /**
     * This returns the minimum value in the priority queue.
     * @return The minimum value in the priority queue.
     */
    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    /**
     * This deletes the minimum value entry in the priority queue.
     * @return The deleted minimum value entry in the priority queue.
     */
    @Override
    public Entry<K, V> deleteMin() {
        if (list.isEmpty()) {
            return null;
        }
        Entry<K, V> answer = list.get(0);
        swap(0, list.size() - 1);
        list.remove(list.size() - 1);
        downHeap(0);
        return answer;
    }
    /**
     * This returns the size of the priority queue.
     * @return The size of the priority queue.
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Ensures the min-heap ordering property is maintained appropriately by
     * comparing an entry's key (priority) with the key of its parent, swapping the
     * entries if necessary
     * 
     * @param index the index of the entry at which to determine if up-heap is
     *              necessary to preserve the min-heap ordering property
     */
    protected void upHeap(int index) {
        while(index > 0) {
        	int p = parent(index);
        	if(compare(list.get(index).getKey(), list.get(p).getKey()) >= 0)
        		break;
        	swap(index, p);
        	index = p;
        }
    }

    /**
     * Swaps the entry at index1 with the entry at index2
     * 
     * @param index1 the index of the first entry involved in the swap
     * @param index2 the index of the second entry involved in the swap
     */
    protected void swap(int index1, int index2) {
        Entry<K, V> temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Ensures the min-heap ordering property is maintained appropriately by
     * comparing an entry's key (priority) with the keys of its children, swapping
     * the entry with its lowest-priority child if necessary
     * 
     * @param index the index of the entry at which to determine if down-heap is
     *              necessary to preserve the min-heap ordering property
     */
    protected void downHeap(int index) {
        while(hasLeft(index)) {
        	int leftIndex = left(index);
        	int smallChildIndex = leftIndex;
        	if(hasRight(index)) {
        		int rightIndex = right(index);
        		if(compare(list.get(leftIndex).getKey(), list.get(rightIndex).getKey()) > 0)
        			smallChildIndex = rightIndex;
        	}
        	if(compare(list.get(smallChildIndex).getKey(), list.get(index).getKey()) >= 0)
        		break;
        	swap(index, smallChildIndex);
        	index = smallChildIndex;
        }
    }
}
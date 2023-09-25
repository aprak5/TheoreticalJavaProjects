/**
 * @file DiningThread.java
 * @author Amit Prakash (aprakas5)
 * @version JDK 11.0.10
 * This file contains some functionality for this implementation of the Dining Threads problem.
 * It contains the DiningThread class, which contains methods for its corresponding behaviors.
 * This program was developed via the Eclipse IDE for Developers.
 * 
 * This program is run through the IDE via the following commands: 
 *      Create a java project with the package: edu.ncsu.csc246.main
 *      Put DiningThread.java and Barrier.java into the project/package above
 *      Right-click DiningThread.java and select Run As > Java Application
 *      
 * This program is run through the command-line via the following commands:
 * 		javac -d . Barrier.java
 *      javac -d . DiningThread.java
 *      java edu.ncsu.csc246.main.DiningThread
 */

package edu.ncsu.csc246.main;

/**
 * This class looks at the functionality for the Barrier.
 * With methods and fields for Barrier functionality in a synchronized barrier setting.
 */
public class Barrier {

	/**The maximum number of threads to synchronize*/
	private final int MAX_THREADS_TO_SYNCHRONIZE;
	/**The number of thread that have 'arrived' thus far*/
	private int count; 

	/**
	 * This is the constructor for the Barrier with the maximum number of threads passed in.
	 * @param max The maximum number of threads to synchronize.
	 */
	public Barrier(int MAX_THREADS_TO_SYNCHRONIZE) {
		//Initialize all fields to parameters in the constructor.
		this.MAX_THREADS_TO_SYNCHRONIZE = MAX_THREADS_TO_SYNCHRONIZE;
		//Count must be initialized as 0.
		count = 0;
	}

	/**
	 * This method helps DiningThreads to synchronize and notifies all of them when done.
	 * @throws InterruptedException The InterruptedException is thrown when an interrupt happens during thread execution.
	 */
	public synchronized void waitForOthers() throws InterruptedException {
		//While count is less than the MAX_THREADS_TO_SYNCHRONIZE increment count and wait on the other DiningThreads
		try {
			count++;
			if(count < MAX_THREADS_TO_SYNCHRONIZE)
				wait();
			else // If count == MAX_THREADS_TO_SYNCHRONIZE, notifyAll() DiningThreads.
				notifyAll();
		} catch (InterruptedException e) {
			throw new InterruptedException("The thread execution was interrupted.");
		}
	}
}

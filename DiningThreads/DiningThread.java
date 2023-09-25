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
 * This class looks at the functionality for the DiningThread (a child of the Thread object).
 * With methods and fields for Thread functionality in a synchronized barrier setting.
 */
public class DiningThread extends Thread {

	/**The (Barrier) barrier instance for all the threads.*/
	private Barrier barrier;
	/**The (str) task to do.*/
	private String task; // e.g. "Task A"
	/**The (int) id for the thread.*/
	private int id;

	/**
	 * This is the object constructor which takes and initializes the three fields of barrier, task, and id (respectively).
	 * @param barrier The passed barrier object for this DiningThread.
	 * @param task The passed task for this DiningThread.
	 * @param id The passed id for this DiningThread.
	 */
	public DiningThread(Barrier barrier, String task, int id) {
		//Initialize all fields to parameters in the constructor.
		this.barrier = barrier;
		this.task = task;
		this.id = id;
	}

	/**
	 * This method provides functionality for the thread while it is running. 
	 * It deals with called the Barrier synchonization method and printing out messages to Standard Output for its current status.
	 */
	public void run() {
		//Print out waiting and doing task
		System.out.println("I am thread " + id + ", working on Task " + task + ", and waiting to eat.");
		try {
			//wait for the last thread
			barrier.waitForOthers();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		//Print out eating
		System.out.println("I am thread " + id + " and I am eating.");
	}

	/**
	 * This is the main method, with all functionality for the 4 DiningThreads running and joining.
	 * @param args This the string array of the command-line arguments passed in (which should be of size 0).
	 * @throws InterruptedException The InterruptedException is thrown when an interrupt happens during thread execution.
	 */
	public static void main(String[] args) throws InterruptedException {

		Barrier b = new Barrier(4); // 4 threads must be synchronized

		// create DiningThread objects
		DiningThread d1 = new DiningThread(b, "A", 1);
		DiningThread d2 = new DiningThread(b, "B", 2);
		DiningThread d3 = new DiningThread(b, "C", 3);
		DiningThread d4 = new DiningThread(b, "D", 4);

		// start them
		d1.start();
		d2.start();
		d3.start();
		d4.start();

		// join them
		d1.join();
		d2.join();
		d3.join();
		d4.join();
	}
}

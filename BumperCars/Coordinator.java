/**
 * @file Coordinator.java
 * @author Amit Prakash (aprakas5)
 * @version JDK 11.0.10
 * This file contains some functionality for this implementation of the Bumper Cars problem.
 * It contains the Coordinator class, which contains fields/methods for its corresponding behaviors, 
 * including the main method for the program run/interaction (input/output) with the command-line.
 * This program was developed via the Eclipse IDE for Developers.
 * 
 * This program is run through the command-line via the following commands:
 * 		javac -d . Sleeper.java RiderThread.java Coordinator.java
 *      java edu.ncsu.csc246.main.Coordinator <positive integer number of bumper cars> <positive integer number of riders> <positive (long) integer number of seconds>
 */
package edu.ncsu.csc246.main;

/**
 * This class looks at the functionality for the Coordinator, managing the
 * buffer of bumper cars and running the main method. With
 * methods/fields/constructor to interact with the command-line or manage the
 * buffer of bumper cars. Could be made as a singleton, but to keep an openness
 * to expansion, implemented regularly. 
 */
public class Coordinator {

	/**
	 * The bounded buffer of bumper cars (bound is user-provided via the
	 * command-line), maintained with cars in ascending order 1...n ->
	 * boundedCarBuffer[0]...boundedCarBuffer[n-1].
	 */
	private int[] boundedCarBuffer;

	/**
	 * The constructor to initialize the the boundedCarBuffer via the parameter
	 * provided and according to program design of the buffer.
	 * 
	 * @param bufferSize The integer bound on the boundedCarBuffer.
	 * @throws {@link IllegalArgumentException} If the given parameter is not valid
	 *                (< 1), an IllegalArgumentException is thrown.
	 */
	public Coordinator(int bufferSize) throws IllegalArgumentException {
		// A try-catch to check if the buffer size is valid and return a message if not.
		try {
			// Initialize buffer.
			boundedCarBuffer = new int[bufferSize];
			// Fill with values as according to design.
			for (int i = 1; i <= bufferSize; i++)
				boundedCarBuffer[i - 1] = i;
		} catch (Exception e) {
			throw new IllegalArgumentException("The buffer size provided should be a positive integer."
					+ "\nUsage: <positive integer number of bumper cars> <positive integer number of riders> <positive (long) integer number of seconds>");
		}
	}

	/**
	 * This synchronized method ensures all the threads can get in line and get or
	 * wait for a car.
	 * 
	 * @return The integer id of the car given to the rider. If none available, 0 is
	 *         returned (which is outside of normal bumper car id values).
	 * @throws {@link InterruptedException} If the thread run is interrupted, an
	 *                InterruptedException is thrown.
	 */
	public synchronized int getInLine() throws InterruptedException {
		// A for-loop to ensure all elements in the buffer are checked.
		for (int i = 0; i < boundedCarBuffer.length; i++) {
			// If the buffer element is full, empty it and return the value previously
			// inside it
			if (boundedCarBuffer[i] != 0) {
				boundedCarBuffer[i] = 0;
				return (i + 1);
			}
		}
		// A try-catch to check if while waiting the program is interrupted, if so
		// display an error message.
		try {
			// If the buffer is full wait and return a 0, indicating the buffer is full.
			wait();
			return 0;
		} catch (InterruptedException ie) {
			throw new InterruptedException("The thread execution was interrupted.");
		}
	}

	/**
	 * This synchronized method ensures all the threads in line can get a car and
	 * those that just got a car return it.
	 * 
	 * @param carId The bumper car id for the car that is getting returned.
	 * @throws {@link InterruptedException} If the thread run is interrupted, an
	 *                InterruptedException is thrown.
	 */
	public synchronized void returnCar(int carId) throws InterruptedException {
		// If the buffer element is empty for the carId, fill it and take another rider
		if (boundedCarBuffer[carId - 1] == 0) {
			boundedCarBuffer[carId - 1] = carId;
			notify();
			// Otherwise, wait for it to be empty.
		} else {
			// A try-catch to check if while waiting the program is interrupted, if so
			// display an error message.
			try {
				wait();
			} catch (InterruptedException e) {
				throw new InterruptedException("The thread execution was interrupted.");
			}
		}
	}

	/**
	 * The main method runs the program and ensures correct functionality.
	 * 
	 * @param args The command-line arguments passed as a String array.
	 * @throws {@link IllegalArgumentException} If any argument passed via the
	 *                command-line/user is invalid, an IllegalArgumentException is
	 *                thrown.
	 * @throws {@link InterruptedException} If the thread run is interrupted, an
	 *                InterruptedException is thrown.
	 */
	public static void main(String[] args) throws IllegalArgumentException, InterruptedException {
		// Declare and initialize a Coordinator (via the constructor)
		Coordinator mainCoord;
		// A try-catch to check the first command line argument for the number of cars.
		try {
			mainCoord = new Coordinator(Integer.parseInt(args[0]));
		} catch (Exception e) {
			throw new IllegalArgumentException("The number of cars could not be parsed to a positive integer."
					+ "\nUsage: <positive integer number of bumper cars> <positive integer number of riders> <positive (long) integer number of seconds>");
		}
		// A try-catch to check the second command line argument for the number of
		// riders.
		try {
			// Array declared/initialized for the RiderThreads created, which are then
			// initialized and started
			RiderThread[] riderThreads = new RiderThread[Integer.parseInt(args[1])];
			for (int i = 0; i < riderThreads.length; i++)
				riderThreads[i] = new RiderThread((i + 1), mainCoord);
			for (int i = 0; i < riderThreads.length; i++)
				riderThreads[i].start();
			// A try-catch to check the third command line argument for the number of
			// seconds.
			try {
				// Exit the current thread after time is up
				Thread.sleep(Long.parseLong(args[2]) * 1000);
				System.exit(0);
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"The number of seconds could not be parsed to a positive (long) integer."
								+ "\nUsage: <positive integer number of bumper cars> <positive integer number of riders> <positive (long) integer number of seconds>");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("The number of riders could not be parsed to a positive integer."
					+ "\nUsage: <positive integer number of bumper cars> <positive integer number of riders> <positive (long) integer number of seconds>");
		}
	}
}

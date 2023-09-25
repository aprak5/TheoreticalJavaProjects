/**
 * @file RiderThread.java
 * @author Amit Prakash (aprakas5)
 * @version JDK 11.0.10
 * This file contains some functionality for this implementation of the Bumper Cars problem.
 * It contains the RiderThread class, which contains fields/methods for its corresponding behaviors.
 * This program was developed via the Eclipse IDE for Developers.
 * 
 * This program is run through the command-line via the following commands:
 * 		javac -d . Sleeper.java RiderThread.java Coordinator.java
 *      java edu.ncsu.csc246.main.Coordinator <positive integer number of bumper cars> <positive integer number of riders> <positive (long) integer number of seconds>
 */
package edu.ncsu.csc246.main;

/**
 * This class looks at the functionality for the RiderThread (a child of the
 * Thread object). With methods and fields for Thread functionality in a
 * synchronized setting with a given Coordinator. Relies on Coordinator and
 * Sleeper classes for implementation.
 */
public class RiderThread extends Thread {

	/** The Coordinator object for this Thread. */
	private Coordinator coordinator;
	/** The integer id for this Thread. */
	private int id;

	/**
	 * Sets the id and Coordinator for this RiderThread object.
	 * 
	 * @param id          The id to set for this RiderThread object.
	 * @param coordinator The Coordinator to set for this RiderThread object.
	 */
	public RiderThread(int id, Coordinator coordinator) {
		// Set all fields as values passed into the constructor parameters.
		this.coordinator = coordinator;
		this.id = id;
	}

	/**
	 * Defines runtime behavior for the RiderThread. Uses
	 * Sleeper.walkAround() and Sleeper.rideTime() for its implementation.
	 * 
	 * @throws {@link InterruptedException} If the thread run is interrupted, an
	 *                InterruptedException is thrown.
	 */
	public void run() {
		// A while loop that runs while it is not time to exit the program (as per the
		// main thread sleep()).
		// Some internal comments are options on where to print the status messages for
		// the rider for more logic according to the process ordering.
		while (true) {
			// The rider walks around, and a corresponding message is printed.
			System.out.println("Rider " + id + " is walking around the park.");
			Sleeper.walkAround();
			//			System.out.println("Rider " + id + " is walking around the park.");
			// A try-catch to handle an InterruptedException and to propogate it to the
			// user.
			try {
				// The rider is in line while there are no free bumper cars.
				int carId = coordinator.getInLine();
				while (carId == 0)
					carId = coordinator.getInLine();
				// The rider gets the time to ride in the bumper car, and a corresponding
				// message is printed.
				System.out.println("Rider " + id + " is now riding in car " + carId + ".");
				Sleeper.rideTime();
				//				System.out.println("Rider " + id + " is now riding in car " + carId + ".");
				// The rider is done and returns the bumper car, and a corresponding message is
				// printed.
				System.out.println("Rider " + id + " returned car " + carId + ".");
				coordinator.returnCar(carId);
				//				System.out.println("Rider " + id + " returned car " + carId + ".");
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
		}
	}
}

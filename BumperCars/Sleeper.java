import java.util.*;

/**
 * @file Sleeper.java
 * @author Amit Prakash (aprakas5)
 * @version JDK 11.0.10
 *          This file contains some functionality for this implementation of the
 *          Bumper Cars problem.
 *          It contains the Sleeper class, which contains fields/methods for its
 *          corresponding behaviors,
 *          including the main method for the program run/interaction
 *          (input/output) with the command-line.
 *          This program was developed via the Eclipse IDE for Developers.
 * 
 *          This program is run through the command-line via the following
 *          commands:
 *          javac -d . Sleeper.java RiderThread.java Coordinator.java
 *          java edu.ncsu.csc246.main.Coordinator <positive integer number of
 *          bumper cars> <positive integer number of riders> <positive (long)
 *          integer number of seconds>
 */
public class Sleeper {

    /**
     * Defines the time for a ride as no more than 5 seconds and then makes the thread sleep while riding.
     */
    public static void rideTime() {

        Random generator = new Random();
        int milliseconds = (generator.nextInt(5) + 1) * 1000;

        System.out.println("Riding for " + milliseconds / 1000 + " seconds");

        try {
            Thread.currentThread().sleep(milliseconds);
        } catch (InterruptedException e) {
        }

    }

    /**
     * Defines the time for to walk around as no more than 10 seconds and then makes the thread sleep while walking around.
     */
    public static void walkAround() {

        Random generator = new Random();
        int milliseconds = (generator.nextInt(10) + 1) * 1000;

        System.out.println("Walking around for  " + milliseconds / 1000 + " seconds");

        try {
            Thread.currentThread().sleep(milliseconds);
        } catch (InterruptedException e) {
        }

    }

    /*
     * public static void main (String [] args) {
     * 
     * // example of usage
     * 
     * Sleeper.rideTime();
     * System.out.println ("Ride over");
     * Sleeper.walkAround();
     * System.out.println ("Completed walking around ...");
     * }
     */
}

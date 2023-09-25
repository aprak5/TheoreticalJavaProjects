/**
 * @file Life.java
 * @author Amit Prakash (aprakas5)
 * @version JDK 11.0.10
 * This file contains all functionality for this implementation of Game of Life by John Conway.
 * It contains three classes Cell, Life, and CellRule, which contain corresponding behaviors.
 * This program was developed via the Eclipse IDE for Developers.
 * This program is run through the console via the following command: java Life.java <file name/path> <number of generations>
 */
package edu.ncsu.csc246.main;

/**
 * Import several classes here for error handling, for lists, and for I/O.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the main method with all functionality for I/O from the
 * console/external input and error-handling for such, along with calling
 * methods to initialize and run the game via the Cell and CellRule classes.
 * This method also prints output for the game to the console, so the user can
 * see each generation of the game, as requested. In the future, this class will
 * have to be refactored for better expansion of I/O and initializing the game,
 * but for now this class works as intended.
 */
public class Life {

	/**
	 * The main method has all functionality for I/O from the console/external input
	 * and error-handling for such, along with calling methods to initialize and run
	 * the game via the Cell and CellRule classes. This method also prints output
	 * for the game to the console, so the user can see each generation of the game,
	 * as requested.
	 * 
	 * @param args This parameter is given from the command-line input
	 * @throws FileNotFoundException    If the file given on the command-line cannot
	 *                                  be opened/accessed, a FileNotFoundException
	 *                                  is thrown.
	 * @throws IllegalArgumentException If any command-line arguments are invalid or
	 *                                  there is a problem with the Cell field
	 *                                  modification, an IllegalArgumentException is
	 *                                  thrown.
	 * @throws InterruptedException     InterruptedException If thread(s) cannot be
	 *                                  joined and are interrupted, an
	 *                                  InterruptedException is thrown.
	 */
	public static void main(String[] args)
			throws FileNotFoundException, IllegalArgumentException, InterruptedException {
		// Error Handling for the number of command-line inputs
		if (args.length < 2)
			throw new IllegalArgumentException(
					"The command line input should be in the format: java Life.java <file name/path> <number of generations>.");
		// Initialization for command-line inputs to variables
		String fileName = args[0];
		int numGens = 0;
		// Error Handling for the second command-line input
		try {
			numGens = Integer.parseInt(args[1].trim());
			if (numGens <= 0)
				throw new Exception();
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"The command line input for the number of generations should be a number greater than 0.");
		}
		// Error Handling for the first command-line input
		try {
			// Read and Initialize first grid/dimensions from the given file
			// via java.util.Scanner and java.io.FileInputStream and Cell
			Scanner fileScanner = new Scanner(new FileInputStream(fileName));
			int numRows = fileScanner.nextInt();
			int numCols = fileScanner.nextInt();
			fileScanner.nextLine();
			Cell[][] currGen = new Cell[numRows][numCols];

			for (int i = 0; i < numRows; i++) {
				String[] currLine = fileScanner.nextLine().trim().split(" ");
				for (int j = 0; j < numCols; j++) {
					currGen[i][j] = new Cell(Integer.parseInt(currLine[j]));
				}
			}

			fileScanner.close();
			// Print input to console
			System.out.print("Your input:\n");
			for (int j = 0; j < numRows; j++) {
				for (int k = 0; k < numCols; k++) {
					System.out.print(currGen[j][k].getLife() + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
			// Start the game and run it according to the number of generations as inputted
			for (int i = 0; i < numGens; i++) {
				System.out.print("Generation " + (i + 1) + ": \n");
				// Create a new grid and initialize it so it can store the future generation
				// later via Cell
				Cell[][] newGen = new Cell[numRows][numCols];
				for (int j = 0; j < numRows; j++)
					for (int k = 0; k < numCols; k++)
						newGen[j][k] = new Cell();
				// Check neighbors and implement rules of the game on currGrid via CellRule and
				// save to newGrid
				CellRule.checkCells(currGen, newGen, numCols, numRows);
				// Print out the new generation
				for (int j = 0; j < numRows; j++) {
					for (int k = 0; k < numCols; k++) {
						System.out.print(newGen[j][k].getLife() + " ");
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				// Save new generation as the current generation
				for (int j = 0; j < numRows; j++)
					for (int k = 0; k < numCols; k++)
						currGen[j][k].setLife(newGen[j][k].getLife());
			}

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("This file was not found or could not be properly accessed.");
		}
	}
}

/**
 * This class represents a cell in the grid for the game of life. It is separate
 * so it can easily be changed and understood separate from the whole game. This
 * class contains constructors, accessors, and modifiers for the Cell object.
 */
class Cell {
	/** This is the integer value of whether the cell is alive(1) or dead(0). */
	private int alive;
	/**
	 * This is the integer value of the number of neighbors(0-8) near the cell who
	 * are alive.
	 */
	private int neighbors;

	/**
	 * This is the default constructor, which sets both the two fields above to 0.
	 */
	public Cell() {
		this.setLife(0);
		this.setNeighbors(0);
	}

	/**
	 * This is the modified constructor, which sets both the neighbor field above to
	 * 0 and uses an integer parameter (alive) to set the alive field.
	 * 
	 * @param alive This is an integer parameter of whether the cell is alive(1) or
	 *              dead(0).
	 */
	public Cell(int alive) {
		this.setNeighbors(0);
		this.setLife(alive);
	}

	/**
	 * This sets the integer neighbors alive field according to the parameter passed
	 * in.
	 * 
	 * @param neighbors This is the integer parameter of the number of
	 *                  neighbors(0-8) near the cell who are alive.
	 * @throws IllegalArgumentException An IllegalArgumentException is thrown if the
	 *                                  neighbors parameter, which represents the
	 *                                  number of neighbors(0-8) near the cell who
	 *                                  are alive, is not valid(0-8).
	 */
	public void setNeighbors(int neighbors) throws IllegalArgumentException {
		if (neighbors < 0 || neighbors > 8)
			throw new IllegalArgumentException("There can only be between 0 and 8 neighbors for a cell.");
		this.neighbors = neighbors;
	}

	/**
	 * This returns the integer value representing whether or not a cell is alive(1)
	 * or dead(0).
	 * 
	 * @return alive: This is the integer value of whether the cell is alive(1) or
	 *         dead(0).
	 */
	public int getLife() {
		return alive;
	}

	/**
	 * This adds a neighbor to the integer neighbors field, which represents the
	 * number of neighbors(0-8) near the cell who are alive.
	 */
	public void addNeighbor() {
		neighbors++;
	}

	/**
	 * This gets the integer neighbors alive field, which represents the number of
	 * neighbors(0-8) near the cell who are alive, according to the parameter passed
	 * in.
	 * 
	 * @return neighbors: This is the integer value of the number of neighbors(8)
	 *         near the cell who are alive.
	 */
	public int getNeighbors() {
		return neighbors;
	}

	/**
	 * This sets the integer value representing whether or not a cell is alive(1) or
	 * dead(0)
	 * 
	 * @param alive This is the integer parameter of whether the cell is alive(1) or
	 *              dead(0).
	 * @throws IllegalArgumentException If the integer value for the alive
	 *                                  parameter, which represents the value of
	 *                                  whether the cell is alive(1) or dead(0), is
	 *                                  not valid(0-1), an IllegalArgumentException
	 *                                  is thrown.
	 */
	public void setLife(int alive) {
		if (alive == 0 || alive == 1) {
			this.alive = alive;
		} else {
			throw new IllegalArgumentException("The input alive value can only be 0 or 1.");
		}
	}
}

/**
 * This class checks and counts neighbors for all the cells, so that they can be
 * transferred to the new generation. It also creates threads for each of the
 * cells, so that an entire grid can be done simultaneously.
 */
class CellRule {
	/**
	 * This function gets the number of neighbors for each cell, and saves the
	 * future generation based on the number neighbors from arr (the current array)
	 * to future generation's array.
	 * 
	 * @param currGen   The current array that will be checked for neighbors.
	 * @param futureGen This array will keep track of the future generation.
	 * @param columns   This is the number of columns for both arrays.
	 * @param rows      This is the number of rows for both arrays.
	 */
	private static void countNeighbors(Cell[][] currGen, int row, int column, int rows, int columns) {
		// Select the cell based on the passed row and column via the parameters.
		Cell c = currGen[row][column];
		c.setNeighbors(0); // Fail-safe to ensure the value is certain before logic is applied
		// For all cells around the selected cell (passed via row/column in the
		// parameters)
		// Check if the cell is alive, if so add a neighbor to the current cell
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue; // don't check self
				// Bounds checking and handling (the grid should wrap around)
				if (row + i < 0) {
					if (column + j < 0) {
						if (currGen[rows - 1][columns - 1].getLife() == 1)
							c.addNeighbor();
					} else if (column + j >= columns) {
						if (currGen[rows - 1][0].getLife() == 1)
							c.addNeighbor();
					} else {
						if (currGen[rows - 1][column + j].getLife() == 1)
							c.addNeighbor();
					}
					continue;
				} else if (row + i >= rows) {
					if (column + j < 0) {
						if (currGen[0][columns - 1].getLife() == 1)
							c.addNeighbor();
					} else if (column + j >= columns) {
						if (currGen[0][0].getLife() == 1)
							c.addNeighbor();
					} else {
						if (currGen[0][column + j].getLife() == 1)
							c.addNeighbor();
					}
					continue;
				} else if (column + j < 0) {
					if (currGen[row + i][columns - 1].getLife() == 1)
						c.addNeighbor();
					continue;
				} else if (column + j >= columns) {
					if (currGen[row + i][0].getLife() == 1)
						c.addNeighbor();
					continue;
				}
				// Normal case
				if (currGen[row + i][column + j].getLife() == 1) {
					c.addNeighbor();
				}
			}
		}
	}

	/**
	 * This method implements the 4 rules for the game, nesting conditionals to
	 * check the rules more efficiently. This method checks the oldCell's neighbors
	 * to decide what the newCell should be (alive(1) or dead(0)).
	 * 
	 * @param oldCell This is the oldCell to check against from the current
	 *                generation.
	 * @param newCell This is the newCell to decide for from the next generation.
	 */
	private static void checkNeighbors(Cell oldCell, Cell newCell) {
		// Cell is alive
		if (oldCell.getLife() == 1) {
			// Cell will stay alive
			if (oldCell.getNeighbors() == 2 || oldCell.getNeighbors() == 3)
				newCell.setLife(1);
			// Cell will die
			else
				newCell.setLife(0);
		}
		// Cell is dead
		else {
			// Cell will come to life
			if (oldCell.getNeighbors() == 3)
				newCell.setLife(1);
			// Cell will stay dead
			else
				newCell.setLife(0);
		}
	}

	/**
	 * This method implements the two other methods in the class via parallel
	 * threads for each cell in the current generation's grid. The threads are later
	 * joined back together in a separate for-each loop.
	 * 
	 * @param currGen   The current generation's grid to use to continue the game
	 *                  (to look for neighbors/apply rules off of).
	 * @param futureGen The future generation's grid to use to save the next
	 *                  generation as a result from applying all the rules of the
	 *                  game.
	 * @param columns   The number of columns in both grids.
	 * @param rows      The number of rows in both grids.
	 * @throws InterruptedException If thread(s) cannot be joined and are
	 *                              interrupted, an InterruptedException is thrown.
	 */
	public static void checkCells(Cell[][] currGen, Cell[][] futureGen, int columns, int rows)
			throws InterruptedException {
		// List of threads created for each cell
		ArrayList<Thread> threads = new ArrayList<>();
		// Loop to create threads and run methods within them.
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Integer thread_local_row = row;
				Integer thread_local_column = column;
				Thread t = new Thread(() -> {
					countNeighbors(currGen, thread_local_row, thread_local_column, rows, columns);
					checkNeighbors(currGen[thread_local_row][thread_local_column],
							futureGen[thread_local_row][thread_local_column]);
				});
				t.start();
				threads.add(t);
			}
		}
		// Loop to join/terminate the threads, if failed an InterruptedException is
		// generated.
		for (Thread t : threads)
			try {
				t.join();
			} catch (InterruptedException e) {
				throw new InterruptedException(
						"There was a problem with joining 1 or more threads, as the process has been interrupted.");
			}
	}
}

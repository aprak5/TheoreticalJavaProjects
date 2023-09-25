package edu.ncsu.csc316.dsa.manager;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.io.StudentReader;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * StudentManager manages Student information. 
 * StudentManager can sort a Student roster.
 * @author Dr. King and Amit Prakash
 */
public class StudentManager {

    /** A roster of students in the system.*/
    private Student[] roster;
    
    /**The sorting algorithm to use.*/
    private Sorter<Student> sorter;
    
    /**
     * The constructor for the StudentManager, takes the path from which to read the roster and the sorting algorithm to use to sort the roster.
     * @param pathToFile The file path for the Students to be read in through.
     * @param sorter The sorting algorithm to use to sort the Students read in.
     */
    public StudentManager(String pathToFile, Sorter<Student> sorter) {
        roster = StudentReader.readInputAsArray(pathToFile);
        this.sorter = sorter;
    }
    /**
     * Initializes a StudentManager.
     * The default sorting algorithm for sorter is InsertionSorter.
     * @param pathToFile The path to the input student CSV file.
     */
    public StudentManager(String pathToFile) {
        this(pathToFile, new InsertionSorter<Student>());
    } 
    /**
     * Sorts and returns an array of Students.
     * @return roster: The sorted array of Students.
     */
    public Student[] sort()
    {
        sorter.sort(roster);
        return roster;
    }
}
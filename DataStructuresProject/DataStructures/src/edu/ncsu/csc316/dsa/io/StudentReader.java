package edu.ncsu.csc316.dsa.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * StudentReader processes input CSV files that contain student information.
 * Input CSV files should be in the following format: FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS.
 * If a field is incorrect or missing, the whole student object is ignored.
 * @author Dr. King and Amit Prakash
 *
 */
public class StudentReader {

	/**
	 * Returns the input CSV file as an array of Student objects.
	 * @param filePath The path to the input CSV file.
	 * @return list: An array of Student objects.
	 */
	public static Student[] readInputAsArray(String filePath)
	{
		Student[] list = new Student[10];
		try(Scanner scan = new Scanner(new FileInputStream(filePath), "UTF8"))
		{
			scan.nextLine(); // SKIP HEADER LINE
			int index = 0;
			while(scan.hasNextLine())
			{
				if(index >= list.length)
				{
					list = Arrays.copyOf(list, list.length * 2 + 1);
				}
				list[index] = processLine(scan.nextLine());
				index++;
			}
			list = Arrays.copyOf(list, index);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found: " + e.getMessage());
		}
		return list;
	}

	/**
	 * Processes a single line from the input file to construct a Student.
	 * @param line The input line from the input file
	 * @return String as Student: A Student representation of the input line.
	 */
	private static Student processLine(String line) {
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");
		String studentFirstName = null;
		String studentLastName = null;
		int studentID = -1;
		String studentUnityID = null;
		double studentGPA = -1;
		int studentCreditHours = -1;
		
		try {
			if(lineReader.hasNext()) {
				studentFirstName = lineReader.next();
			} 
			else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
			if(lineReader.hasNext()) {
				studentLastName = lineReader.next();
				if(studentLastName.equals("")) {
					lineReader.close();
					throw new IllegalArgumentException();
				}	
			} 
			else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
			if(lineReader.hasNext() && !lineReader.hasNextInt()) {
				studentUnityID = lineReader.next();
				if(studentUnityID.equals("")) {
					lineReader.close();
					throw new IllegalArgumentException();
				}
			} 
			else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
			if(lineReader.hasNextInt()) {
				studentID = lineReader.nextInt();
			} 
			else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
			if(lineReader.hasNextDouble()) {
				studentGPA = lineReader.nextDouble();
			} 
			else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
			if(lineReader.hasNextInt()) {
				studentCreditHours = lineReader.nextInt();
			}
			else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
			lineReader.close();
			return new Student(studentFirstName, studentLastName, studentID, studentCreditHours, studentGPA, studentUnityID);
		}
		catch (IllegalArgumentException i) {
			lineReader.close();
			return null;
		}
	}	
}

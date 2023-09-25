package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, number of credit hours, gpa, and unityID.
 * This class has the constructor, getters, setters, compareTo(), equals(), hashCode(), and toString() method for the Student.
 * @author Dr. King and Amit Prakash
 */
public class Student implements Comparable<Student>, Identifiable {
	/**Student's first name*/
	private String first;
	/**Student's last name*/
	private String last;
	/**Student's id number*/
	private int id;
	/**Student's number of credit hours*/
	private int creditHours;
	/**Student's GPA*/
	private double gpa;
	/**Student's unity ID*/
	private String unityID;
	/**
	 * This constructs a Student object and initializes all fields via their setters.
	 * @param first The student's first name.
	 * @param last  The student's last name.
	 * @param id  The student's ID number.
	 * @param creditHours  The student's number of credit hours.
	 * @param gpa  The student's GPA.
	 * @param unityID  The student's unity ID.
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.setFirst(first);
		this.setLast(last);
		this.setId(id);
		this.setCreditHours(creditHours);
		this.setGpa(gpa);
		this.setUnityID(unityID);
	}
	/**
	 * This retrieves this student object's first name.
	 * @return first: The student's first name.
	 */
	public String getFirst() {
		return first;
	}
	/**
	 * This modifies the student object's first name to the given parameter (first name).
	 * @param first The student's first name to set.
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	/**
	 * This retrieves this student object's last name.
	 * @return last: The student's last name.
	 */
	public String getLast() {
		return last;
	}
	/**
	 * This modifies the student object's last name to the given parameter (last name).
	 * @param last The student's last name to set.
	 */
	public void setLast(String last) {
		this.last = last;
	}
	/**
	 * This retrieves this student object's ID number.
	 * @return id: The student's ID number.
	 */
	public int getId() {
		return id;
	}
	/**
	 * This modifies the student object's ID number to the given parameter (ID number).
	 * @param id The student's ID number to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * This retrieves this student object's number of credit hours.
	 * @return creditHours: The student's number of credit hours.
	 */
	public int getCreditHours() {
		return creditHours;
	}
	/**
	 * This modifies the student object's number of credit hours to the given parameter (number of credit hours).
	 * @param creditHours The student's number of credit hours to set.
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	/**
	 * This retrieves this student object's GPA.
	 * @return gpa: The student's GPA.
	 */
	public double getGpa() {
		return gpa;
	}
	/**
	 * This modifies the student object's GPA to the given parameter (GPA).
	 * @param gpa The student's GPA to set.
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	/**
	 * This retrieves this student object's unity ID.
	 * @return unityID: The student's unity ID.
	 */
	public String getUnityID() {
		return unityID;
	}
	/**
	 * This modifies the student object's unity ID to the given parameter (unity ID).
	 * @param unityID The student's unity ID to set.
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	/**
	 * This presents a hashed version of the student object.
	 * This is overridden for specific implementation according to the Student object.
	 * @return result: hashed code resulting from a particular student object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}
	/**This is overridden for specific implementation according to the Student object.
	 * If the last name, first name, and ID number of the student is equal, true is returned.
	 * If the student is of another type false is returned.
	 * @return boolean: true if the two objects are equal (in last name, first name, ID number), otherwise false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		
		return (other.getLast().equals(this.getLast())) && (other.getFirst().equals(this.getFirst())) && (other.getId() == this.getId());
	}
	/**
	 * This method turns the student object into a string output/representation.
	 * This string is all the fields separated by a comma, in the order shown below.
	 * This is overridden for specific implementation according to the Student object.
	 * @return student string representation: The string representation of the student object
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}
	/**
	 * This is a comparison students' first names, last names, and ID numbers.
	 * This is overridden to provide a specific comparison of the student objects according to last name, first name, and id in that order. 
	 * @return position indicator integer: This is the value that separates this object from the Students, given 0 if they are the same, a negative integer if before, a positive integer if after.
	 * @param s1 The student object to be compared to the object called on this object.
	 */
	public int compareTo(Student s1) {
		if(this.getLast().compareTo(s1.getLast()) != 0)
			return this.getLast().compareTo(s1.getLast());
		if(this.getFirst().compareTo(s1.getFirst()) != 0)
			return this.getFirst().compareTo(s1.getFirst());
		return Integer.compare(this.getId(), s1.getId());
	}
}

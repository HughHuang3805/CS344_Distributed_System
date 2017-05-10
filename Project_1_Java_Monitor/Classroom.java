package project1;

import java.util.Scanner;
import java.util.Vector;

public class Classroom {
	
	private static int capacity = 0;
	private static int numSeats = 3;
	private static int numStudent = 0;
	Vector<Student> waitForTable = new Vector<Student>();
	
	public static void askForCapacity(){//ask user for classroom capacity
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the classroom capacity: ");
		capacity = reader.nextInt();
	}
	
	public static void askForNumStudent(){//ask user for classroom capacity
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the number of students: ");
		numStudent = reader.nextInt();
	}

	public static int getCapacity() {
		return capacity;
	}

	public static void setCapacity(int capacity) {
		Classroom.capacity = capacity;
	}

	public static int getNumSeats() {
		return numSeats;
	}

	public static void setNumSeats(int numSeats) {
		Classroom.numSeats = numSeats;
	}

	public static int getNumStudent() {
		return numStudent;
	}

	public static void setNumStudent(int numStudent) {
		Classroom.numStudent = numStudent;
	}
	
	
}

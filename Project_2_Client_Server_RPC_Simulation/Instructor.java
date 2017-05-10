package project2;

import java.util.Vector;


public class Instructor extends Thread{

	public static Vector<Student> waitToGetInClassroom = new Vector<Student>();
	public static Vector<Student> waitForTable = new Vector<Student>();
	public static Vector<Instructor> okToSayFull = new Vector<Instructor>();
	public static Vector<Student> waitToTakeExam = new Vector<Student>();
	private static int numberStudentInClassroom = 0;
	private static int numOfExams = 4;
	private static int numberOfStudentsWaiting= 0;
	private static int numSeats = 5;
	public static int getNumSeats() {
		return numSeats;
	}

	public static void setNumSeats(int numSeats) {
		Instructor.numSeats = numSeats;
	}

	private String name = "";
	private int counter = 0;
	public static int capacity = 5;
	private static boolean arrived = false;



	public Instructor(){

	}

	public Instructor(int id){
		name = "Teacher " + id;
	}

	public void run(){

		try {
			goInClassroom();
			getSeated();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void goInClassroom() throws InterruptedException{
		synchronized (waitToGetInClassroom){
			//System.out.println("I am " + getName() + " letting " + waitToGetInClassroom.elementAt(0).getName() + " in the classroom.");

			while(true){
				if(numberOfStudentsWaiting == capacity){
					waitToGetInClassroom.notifyAll(); //signal the first waiting student
					msg("allowing students to go in.");
					numberOfStudentsWaiting = 0;
					waitToGetInClassroom.removeAllElements();
					break;
				}
			}
		}
	}

	public synchronized void getSeated() throws InterruptedException{
		sleep(2500);
		synchronized(waitForTable){
			while(true){
				if(waitForTable.size() < numSeats){
				//	okToSayFull.add(this);
				//	synchronized(okToSayFull){
						msg("hiiiiii");
						waitForTable.wait();
						sleep(1000);
				//	}
				}
				if(waitForTable.size() == numSeats){
					waitForTable.notifyAll();
					msg("Table full. All seats are occupied.");
					waitForTable.removeAllElements();
					break;
				}
			}
		}

	}

	public void msg(String m) {
		System.out.println(getName() +": "+m);
	}

	/*	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 */
	public static int getNumStudentInClassroom() {
		return numberStudentInClassroom;
	}

	public static void setNumStudentInClassroom(int numStudentInClassroom) {
		Instructor.numberStudentInClassroom = numStudentInClassroom;
	}

	public static int getNumOfExams() {
		return numOfExams;
	}

	public static void setNumOfExams(int numOfExams) {
		Instructor.numOfExams = numOfExams;
	}

	public static synchronized int getNumberStudentInClassroom() {
		return numberStudentInClassroom;
	}

	public static void setNumberStudentInClassroom(int numberStudentInClassroom) {
		Instructor.numberStudentInClassroom = numberStudentInClassroom;
	}

	public static boolean isArrived() {
		return arrived;
	}

	public static int getNumberOfStudentsWaiting() {
		return numberOfStudentsWaiting;
	}

	public static void setNumberOfStudentsWaiting(int numberOfStudentsWaiting) {
		Instructor.numberOfStudentsWaiting = numberOfStudentsWaiting;
	}

}

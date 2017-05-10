package project1;

import java.util.Vector;

public class Teacher implements Runnable{

	public static long time = System.currentTimeMillis();
	public static Vector<Student> waitToGetInClassroom = new Vector<Student>();
	public static Vector<Student> waitToTakeExam = new Vector<Student>();
	private static int numberStudentInClassroom = 0;
	private static int numOfExams = 4;
	private static int numberOfStudentsWaiting= 0;
	private String name = "";
	private int counter = 0;
	private static boolean arrived = false;

	public static Student[] studentsGoingIn = new Student[Classroom.getCapacity()];;
	public static Student enoughStudent = new Student();
	
	
	public Teacher(int id){
		name = "Teacher " + id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		instructorArrived();
		
		//System.out.println(numOfExams);


		//}

	}

	public synchronized void instructorArrived(){
		synchronized (waitToGetInClassroom){
			//System.out.println("I am " + getName() + " letting " + waitToGetInClassroom.elementAt(0).getName() + " in the classroom.");
			
			while(true){
				System.out.println(numberOfStudentsWaiting);//0
				arrived = true;
				waitToGetInClassroom.notifyAll(); //signal the first waiting student
				//System.out.println("hi");
				if(numberOfStudentsWaiting == Classroom.getNumStudent())
					break;
			}
			System.out.println("hi in teacher");


			msg("allowing students to go in.");
			getInClassroom();
			//counter++;
			//System.out.println("Counter: " + counter);
		}
	}


	public synchronized Student[] studentsThatAreGoingIn(){
		//boolean status = false;

		if(Classroom.getNumStudent() > Classroom.getCapacity()){
			for(int i = 0; i < Classroom.getCapacity(); i++){

				studentsGoingIn[i] = waitToGetInClassroom.remove(0);
				//System.out.println(Classroom.getNumStudent());
				//msg("hi");
				studentsGoingIn[i].setNumOfExams(studentsGoingIn[i].getNumOfExams() - 1); 

			}
		} else{
			for(int i = 0; i < Classroom.getNumStudent(); i++){

				studentsGoingIn[i] = waitToGetInClassroom.remove(0);
				//System.out.println(studentsGoingIn[i].getName());
				//msg("hi");
				studentsGoingIn[i].setNumOfExams(studentsGoingIn[i].getNumOfExams() - 1); 

			}
		}
		return studentsGoingIn;
	}

	public void getInClassroom(){
		//while(waitToGetInClassroom.size() > 0 && okToEnterClassroom(waitToGetInClassroom.elementAt(0))){
		studentsThatAreGoingIn();
		synchronized (studentsGoingIn){
			//msg("hi");
			if(Classroom.getNumStudent() > Classroom.getCapacity()){
				while(numberStudentInClassroom <= Classroom.getCapacity()){
					msg("hi all, get in the classroom");
					synchronized(enoughStudent){
						try {
							enoughStudent.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					studentsGoingIn.notifyAll(); //signal going in student
					//numberOfStudentsWaiting = 0;
					
					if(numberStudentInClassroom == Classroom.getCapacity())
						break;
					System.out.println(numberStudentInClassroom);
				}
			} else{
				while(numberOfStudentsWaiting <= Classroom.getNumStudent()){//numberOfStudentsWaiting is already 10 beofre
					msg("hi all, get in the classroom");
					studentsGoingIn.notifyAll(); //signal going in student
					//numberOfStudentsWaiting = 0;
					synchronized(enoughStudent){
						try {
							enoughStudent.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(numberStudentInClassroom == Classroom.getCapacity())
						break;
					System.out.println(numberStudentInClassroom);
				}
			}

			//msg("hi");
			//counter++;
			//System.out.println("Counter: " + counter);
		}
		//decrease the number of exams need to complete
		//waitToGetInClassroom.elementAt(0).setNumOfExams(waitToGetInClassroom.elementAt(0).getNumOfExams() - 1); 
		//waitToGetInClassroom.removeElementAt(0);//remove it from the waiting queue
		//}
		//numOfExams--;
	}

	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getNumStudentInClassroom() {
		return numberStudentInClassroom;
	}

	public static void setNumStudentInClassroom(int numStudentInClassroom) {
		Teacher.numberStudentInClassroom = numStudentInClassroom;
	}

	public static int getNumOfExams() {
		return numOfExams;
	}

	public static void setNumOfExams(int numOfExams) {
		Teacher.numOfExams = numOfExams;
	}

	public static synchronized int getNumberStudentInClassroom() {
		return numberStudentInClassroom;
	}

	public static void setNumberStudentInClassroom(int numberStudentInClassroom) {
		Teacher.numberStudentInClassroom = numberStudentInClassroom;
	}

	public static boolean isArrived() {
		return arrived;
	}

	public static void setArrived(boolean arrived) {
		Teacher.arrived = arrived;
	}

	public static int getNumberOfStudentsWaiting() {
		return numberOfStudentsWaiting;
	}

	public static void setNumberOfStudentsWaiting(int numberOfStudentsWaiting) {
		Teacher.numberOfStudentsWaiting = numberOfStudentsWaiting;
	}

}

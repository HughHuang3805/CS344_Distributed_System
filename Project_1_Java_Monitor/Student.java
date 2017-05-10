package project1;

public class Student implements Runnable{

	public static long time = System.currentTimeMillis();
	private int numOfExams = 3;
	private int numOfStudents = 0;
	private String name = "";
	
	public Student(){
		
	}
	
	public Student(int id){
		name = "Student " + id;
	}

	@Override
	public void run() {
		waitForInstructor();
		goingInClassroom();
	}

	public synchronized void waitForInstructor(){//before students go in the classroom


		synchronized(Teacher.waitToGetInClassroom){
			try {
				incrementWaitingStudents();
				Teacher.waitToGetInClassroom.wait();
				msg("hi");

				//msg("hi");
				Teacher.waitToGetInClassroom.notifyAll();
				goingInClassroom();
				System.out.println("after wait");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("hi");
			}
		}
		//Teacher.waitToGetInClassroom.removeElementAt(0);
	}

	public synchronized void goingInClassroom(){//when he instructor arrives

		//if(Teacher.getNumStudentInClassroom() != Classroom.getCapacity()){
		synchronized(Teacher.studentsGoingIn){
			try {
				incrementNumberOfStudents();
				synchronized(Teacher.enoughStudent){
					if(Teacher.getNumStudentInClassroom() == Classroom.getCapacity()){
						Teacher.enoughStudent.notify();
					}
				}
				while(Teacher.getNumStudentInClassroom() < Classroom.getCapacity()){
					Teacher.studentsGoingIn.wait();
				}
				System.out.println(Teacher.getNumStudentInClassroom());
				msg("I can go in the classroom.");
				//msg("going in classroom");
				this.numOfExams--;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("hi");
			}
		}
		//}
		//msg("going in classroom");
		//Teacher.waitToGetInClassroom.removeElementAt(0);

	}

	public synchronized void incrementNumberOfStudents(){
		synchronized(this){
			Teacher.setNumStudentInClassroom(Teacher.getNumStudentInClassroom() + 1);
			//System.out.println(Teacher.getNumberStudentInClassroom());
		}
	}

	public synchronized void incrementWaitingStudents(){
		synchronized(this){
			Teacher.setNumberOfStudentsWaiting(Teacher.getNumberOfStudentsWaiting() + 1);
			//System.out.println(Teacher.getNumberStudentInClassroom());
		}
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

	public int getNumOfExams() {
		return numOfExams;
	}

	public void setNumOfExams(int numOfExams) {
		this.numOfExams = numOfExams;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}

}

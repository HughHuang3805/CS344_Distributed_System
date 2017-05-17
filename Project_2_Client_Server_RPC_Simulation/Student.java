package project2;


public class Student extends Thread{

	private int numOfExams = 3;
	private int numOfStudents = 0;
	public char grade;
	private String name = "";

	public void run(){

		waitForInstructor();
		//getATable();

	}



	public Student(){

	}

	public Student(int id){
		name = "Student " + id;
	}


	public synchronized void waitForInstructor(){//when he instructor arrives


		synchronized(Instructor.waitToGetInClassroom){
			try {
				if(Instructor.getNumberOfStudentsWaiting() < Instructor.capacity){
					incrementNumberOfStudents();
					msg("Waiting for Instructor");
					Instructor.waitToGetInClassroom.add(this);
					Instructor.waitToGetInClassroom.wait();
					msg("Insturctor arrived. I am going in the classroom");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("hi");
			}

		}

	}

	public synchronized void getATable() throws InterruptedException{
		sleep(1000);
		synchronized(Instructor.waitForTable){
			try{
				
				if(Instructor.waitForTable.size() < Instructor.getNumSeats()){
					msg("I am at a table.");
					Instructor.waitForTable.add(this);
					//synchronized(Instructor.okToSayFull){
						Instructor.waitForTable.notify();
					//}
					Instructor.waitForTable.wait();
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			}

		}

	}
	
	public synchronized void waitToTakeExam() throws InterruptedException{
		sleep(2000);
		synchronized(Instructor.waitToTakeExam){
			if(Instructor.waitToTakeExam.size() < Instructor.getNumSeats()){
				msg("I am waiting to get the exam.");
				Instructor.waitToTakeExam.add(this);
				sleep(1000);
				Instructor.waitToTakeExam.notify();
				Instructor.waitToTakeExam.wait();
			}
		}
		sleep(2000);
	}

	public synchronized void waitToBeGraded() throws InterruptedException{
		sleep(2500);
		synchronized(Instructor.waitToBeGraded){
			if(Instructor.waitToBeGraded.size() < Instructor.getNumSeats()){
				msg("I am waiting for the exam to be graded.");
				Instructor.waitToBeGraded.add(this);
				Instructor.waitToBeGraded.notify();
				Instructor.waitToBeGraded.wait();
			}
		}
		sleep(2500);
	}
	
	public synchronized void incrementNumberOfStudents(){
		synchronized(this){
			Instructor.setNumberOfStudentsWaiting(Instructor.getNumberOfStudentsWaiting() + 1);
		}
	}

	public synchronized void incrementWaitingStudents(){
		synchronized(this){
			Instructor.setNumberOfStudentsWaiting(Instructor.getNumberOfStudentsWaiting() + 1);
			System.out.println(Instructor.getNumberStudentInClassroom());
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
	}*/

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

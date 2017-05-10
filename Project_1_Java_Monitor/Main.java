package project1;

public class Main {

	public static void main(String[] args){//just start a timer thread
		Classroom.askForCapacity();
		Classroom.askForNumStudent();
		
		
		
		for(int i = 1; i <= Classroom.getNumStudent(); i++){
			Student s  = new Student(i);
			Teacher.waitToGetInClassroom.addElement(s);
		}
		for(int i = 0; i < Classroom.getNumStudent(); i++){
			Thread t = new Thread(Teacher.waitToGetInClassroom.elementAt(i));
			t.start();
		}
		
		MyTimer myTimer = new MyTimer();
		myTimer.startTimer();
		
	}

}

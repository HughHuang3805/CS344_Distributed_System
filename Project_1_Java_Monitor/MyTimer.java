package project1;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer implements Runnable{

	private int totalRemainingExams = 4;
	private int currentExamNumber = 1;

	/*before the timer starts, student threads start, and then teacher thread starts. */

	public synchronized void startTimer(){
		Thread teacher = new Thread(new Teacher(1));
		teacher.start();
		Timer timer = new Timer("MyTimer");//create a new Timer
		TimerTask timerTask = new TimerTask() {
		
			@Override
			public void run() {//a teacher thread should start. Either the classroom is filled or the timer is up
				//should synchronize a teacher thread to see if the teacher can start an exam
				if(totalRemainingExams == 0){
					timer.cancel();
					System.out.println("All exams scheduled for today are done. Good Bye!");
					return;
				}
				System.out.println("Current exam number is: " + currentExamNumber);
				System.out.println("Total exam(s) remaining is: " + totalRemainingExams);
				currentExamNumber++;
				totalRemainingExams--;
				
			}
		};

		timer.scheduleAtFixedRate(timerTask, 15, 3000);//this line starts the timer at the same time its executed

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		startTimer();
	}
}

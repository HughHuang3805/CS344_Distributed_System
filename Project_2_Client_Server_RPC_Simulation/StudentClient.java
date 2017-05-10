package project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class StudentClient extends Thread{

	private static int numStudents = 5;
	
	
	
	public void run(){

		try (	Socket studentClient = new Socket("192.168.0.137", 3000);
				PrintWriter out = new PrintWriter(studentClient.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(studentClient.getInputStream()));){

			int methodNumber = 0;
			String line;
			int counter = 0;

			while(methodNumber < 2){

				out.println("Student " + methodNumber);
				line = in.readLine();
				//System.out.println(line);

				methodNumber++;
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		StudentClient sc;
		for(int i = 0; i < numStudents; i++){
			sc = new StudentClient();
			sc.start();
		}
	}
}

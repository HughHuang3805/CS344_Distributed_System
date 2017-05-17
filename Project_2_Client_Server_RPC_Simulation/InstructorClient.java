package project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class InstructorClient extends Thread{

	public void run(){

		try (	Socket studentClient = new Socket("192.168.0.137", 3000);
				PrintWriter out = new PrintWriter(studentClient.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(studentClient.getInputStream()));){

			int methodNumber = 0;
			String line;

			while(methodNumber < 9){

				out.println("Instructor " + methodNumber);
				line = in.readLine();
			//	System.out.println(line);

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
		InstructorClient ic = new InstructorClient();
		ic.start();
	}
	
}

package project2;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {

	public static void main(String[] args) throws IOException {

		int portNumber = 3000;
		boolean listening = true;

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
			while (listening) {
				new ServerThread(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}

}

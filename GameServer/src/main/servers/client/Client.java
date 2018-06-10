package main.servers.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private Scanner reader;
	private PrintStream writer;
	
	public Client(Socket socket) {
		try {
			this.socket = socket;
			
			reader = new Scanner(socket.getInputStream());
			writer = new PrintStream(socket.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Scanner getReader() {
		return reader;
	}
	
	public String readClient() {
		return reader.nextLine();
	}
	
	public void writeInClient(String msg){
		writer.append(msg);
	} 
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}

package main.servers.server.uiserver;

import main.servers.client.Client;

public class ServerWriterThread extends Thread {
	
	private Client clientOne;
	private Client clientTwo;
	private static String message; 
	
	public ServerWriterThread(Client clientOne, Client clientTwo){
		this.clientOne = clientOne;
		this.clientTwo = clientTwo;
	}
	
	@Override
	public void run() {

		while(true) {
			try {
				if(message != null) {
					clientOne.writeInClient(message+"\n");
					sleep(67);
					message = null;
				}
				sleep(67);
			} catch (InterruptedException e) {
				System.err.println("Impossivel escrever mensagem para o cliente\n"+e);
			}			
		}
	}
	
	public static void setMessage(String message) {
		if(message != null)
			ServerWriterThread.message = message;
	}
	
}

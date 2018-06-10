package main.servers.server.uiserver;

import static main.servers.server.uiserver.ServerWriterThread.setMessage;
import java.nio.BufferOverflowException;
import main.servers.client.Client;

public class ServerReaderThread extends Thread {

	private Client client;
	private String msg;
	
	public ServerReaderThread(Client client){
		this.client = client;
	}

	@Override
	public void run() {	

		while(true) {
			try {
				
				msg = client.readClient();
				setMessage(msg);
				
				sleep(67);
			} catch (InterruptedException | BufferOverflowException e) {
				System.err.println("Erro ao fazer comunicação com clientes\n"+e);
			}			
		}

	}

}

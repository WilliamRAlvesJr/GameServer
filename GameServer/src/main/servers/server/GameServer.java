package main.servers.server;

import java.io.IOException;
import java.net.ServerSocket;
import main.servers.client.Client;
import main.servers.client.ClientFactory;
import main.servers.server.uiserver.ServerReaderThread;
import main.servers.server.uiserver.ServerWriterThread;

public class GameServer {

	private static int WAIT_SERVER_START = 1000;
	private static int PORT = 8080;

	private static Client clientOne;
	private static Client clientTwo;
	private static ServerSocket serverSocket;
	private static ServerReaderThread readerTwo;
	private static ServerWriterThread writerTwo;
	private static ServerReaderThread readerOne;
	private static ServerWriterThread writerOne;
	
	public GameServer() {
		try {
			System.out.println("Iniciando servidor...");
			serverSocket = new ServerSocket(PORT);
			Thread.sleep(WAIT_SERVER_START);
			System.out.println("Servidor iniciado com sucesso!!!");

		} catch (InterruptedException | IOException e) {
			System.err.println("Erro ao iniciar o servidor: "+ e);
		}
	}

	public static void main(String args[]){
		new GameServer();
		
		waitClients();
		initReadersAndWriters();
		startClientsInteraction();
		
	}

	private static void waitClients() {
		System.out.println("Esperando clientes...");
		
		clientOne = new ClientFactory().Build(serverSocket);
		clientTwo = new ClientFactory().Build(serverSocket);

		System.out.println("Clientes iniciados com sucesso!!!");
	}

	private static void initReadersAndWriters(){ 
		System.out.println("preparando Comunicadores...");
		
		readerOne = new ServerReaderThread(clientOne);
		readerTwo = new ServerReaderThread(clientTwo);
		
		writerOne = new ServerWriterThread(clientOne, clientTwo);
		writerTwo = new ServerWriterThread(clientTwo, clientOne);
		
		System.out.println("Comunicadores preparados");
	}

	private static void startClientsInteraction(){
		System.out.println("Iniciando comunicadores...");
		
		readerOne.start();
		readerTwo.start();
		
		writerOne.start();
		writerTwo.start();
		
		System.out.println("Comunicadores iniciados");
	}

}

package main.servers.client;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientFactory {

	Client clientPlayer;

	public Client Build(ServerSocket serverSocket) {
		try {
			clientPlayer = new Client(serverSocket.accept());		
			System.out.println("Um novo jogador entrou no servidor");
			return clientPlayer;
		} catch(IOException e) {
			System.err.println("Erro ao construir cliente: "+e);
			System.exit(0);
		}
		return null;
	}
}

package jeu;

import java.io.*;
import java.net.*;

public class Serveur extends ServeurClient {
	
	ServerSocket ss;
	Socket clientSocket;

	public Serveur(Jeu jeu) {
		
		super(jeu);
		
		try {
			ss = new ServerSocket(PORT);
			System.out.println("J'attends qu'un client se connecte");
			jeu.plateau.setVisible(true);
			clientSocket = ss.accept();
			System.out.println("Un client s'est connecte");
			jeu.plateau.setVisible(true);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			out.println(jeu.opts.getGameHeight());
			out.println(jeu.opts.getGameWidth());
			
		}
		catch(IOException e) {
			System.out.println("Oops, la creation du serveur plante");
		}
	}

	void closeSocket() {
	
		try {
			this.clientSocket.close();
			ss.close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la fermeture des sockets");
		}
		
	}
	
}

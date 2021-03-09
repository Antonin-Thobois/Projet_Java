package jeu;

import java.io.*;

abstract class ServeurClient {

	Jeu jeu;
	
	PrintWriter out;
	BufferedReader in;
	String entree;

	static final int PORT = 30000;

	public ServeurClient(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public int attenteCoup() {
		System.out.println("j'attends que l'autre joue");
		while(true) {
			try {
				entree = in.readLine();
				if (entree != null) {
					jeu.lock = false;
					return Integer.parseInt(entree);
				}
			}
			catch(Exception e) {
				System.out.println("Pb dans l'attente de coup. Je quitte");
				System.exit(-1);
			}
		}
			
	}
	
	public void envoyerCoup(int col) {
		out.println(col);
	}

	abstract void closeSocket();
	
}

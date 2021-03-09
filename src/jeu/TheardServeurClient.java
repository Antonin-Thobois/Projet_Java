package jeu;

public class TheardServeurClient extends Thread {
	
	Jeu jeu;
	ServeurClient sc;

	public TheardServeurClient(ServeurClient sc, Jeu j) {
		this.sc = sc;
		this.jeu = j;
	}
	
	public void run() {
		jeu.jouer(sc.attenteCoup());
	}
}

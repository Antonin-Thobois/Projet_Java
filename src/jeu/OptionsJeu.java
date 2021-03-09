package jeu;

public class OptionsJeu {
	private int nbRow;
	private int nbCol;
	boolean netOn = false;
	boolean joueur = false;
	ServeurClient sc;
	Jeu jeu;

	public OptionsJeu(int nbRow, int nbCol, Jeu jeu, boolean serveur) {
		this.jeu = jeu;
		setSize(nbRow, nbCol, true);
		if(!serveur) {
			initNetwork(false);
		}else{
			initNetwork(true);
		}
	}

	public void setSize(int nbRow, int nbCol, boolean initSize) {
		this.nbRow = nbRow;
		this.nbCol = nbCol;
		if (initSize)
			initSize(nbRow, nbCol);
	}

	public void initSize(int nbRow, int nbCol) {
		jeu.plateau = new Grille(nbRow, nbCol, jeu);
		jeu.plateau.setVisible(true);
		jeu.matJeu = new byte[nbRow][nbCol];
	}

	public int getGameWidth() {
		return nbCol;
	}

	public int getGameHeight() {
		return nbRow;
	}
	
	public void initNetwork(boolean joueur) {
		try {
			netOn = true;

			if (!joueur) {
				sc = new Client("", jeu);

				int nbRow = 6;
				int nbCol = 7;

				setSize(nbRow, nbCol, true);

				jeu.lock = true;
				TheardServeurClient nt = new TheardServeurClient(sc, jeu);
				nt.start();
			}
			else {
				sc = new Serveur(jeu);
			}
		}
		catch(Exception e) {
			System.out.println("Erreur lors de creation du client.");
		}
	}
	
}

package jeu;

public class Jeu {
	Grille plateau;
	byte[][] matJeu;
	int nbCoups;
	boolean enCours;
	boolean joueur;
	OptionsJeu opts;
	boolean lock;
	
	public Jeu(String j) {
		nbCoups = 0;
		enCours = true;
		System.out.println(j);
		int nbR = 6;
		int nbC = 7;

		if(j == "serveur"){
			System.out.println("test1");
			this.opts = new OptionsJeu(nbR, nbC, this, true);
		}else{
			System.out.println("test2");
			this.opts = new OptionsJeu(nbR, nbC, this, false);
		}
	}

	public boolean joueurGagne(boolean joueur, int ligneM, int colM) {
		byte jVal = 1;
		if (joueur)
			jVal = 2;
		
		if (horiGagne(jVal, ligneM, colM) || vertGagne(jVal, ligneM, colM) || diag1Gagne(jVal, ligneM, colM) || diag2Gagne(jVal, ligneM, colM))
			return true;
		
		return false;
	}

	public boolean horiGagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int colMin = colM - 3;
		if (colMin <= 0)
			colMin = 0;
		
		int colMax = colM + 3;
		if (colMax >= opts.getGameWidth())
			colMax = opts.getGameWidth() - 1;
		
		for (int i = colMin; i <= colMax; i++) {
			if (this.matJeu[ligneM][i] == jVal)
				nbAlign++;
			else
				nbAlign = 0;

			if (nbAlign == 4)
				return true;
		}
		return false;
	}

	public boolean vertGagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM - 3;
		if (ligneMin <= 0)
			ligneMin = 0;
		
		int ligneMax = ligneM + 3;
		if (ligneMax >= opts.getGameHeight())
			ligneMax = opts.getGameHeight() - 1;
		
		
		for (int i = ligneMin; i <= ligneMax; i++) {
			if (this.matJeu[i][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
		
			if (nbAlign == 4)
				return true;
		}
		return false;
	}

	public boolean diag1Gagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		int compteur = 0;
		while (ligneMax + 1 < opts.getGameHeight() && colMax + 1 < opts.getGameWidth() && compteur <= 2) {
			ligneMax++;
			colMax++;
			compteur++;
		}
		
		compteur = 0;
		while (ligneMin >= 1 && colMin >= 1 && compteur <= 2) {
			ligneMin--;
			colMin--;
			compteur++;
		}
		
		ligneM = ligneMin;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
				
			ligneM++;
			colM++;
			
		} while (ligneM <= ligneMax && colM <= colMax);
		
		return false;
	}

	public boolean diag2Gagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		int compteur = 0;
		while (ligneMax + 1 < opts.getGameHeight() && colMin >= 1 && compteur <= 2) {
			ligneMax++;
			colMin--;
			compteur++;
		}
		
		compteur = 0;
		while (ligneMin >= 1 && colMax + 1 < opts.getGameWidth() && compteur <= 2) {
			ligneMin--;
			colMax++;
			compteur++;
		}
		
		ligneM = ligneMax;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
			
			ligneM--;
			colM++;
					
		} while (ligneM >= ligneMin && colM <= colMax);
		
		return false;
	}

	public void jouer(int col) {
		boolean coupValable;	// Contient la validite du coup que le jouer veut jouer
		int ligne = 0;

		ligne = this.searchLine(col);
		coupValable = this.coupValable(ligne, col);
		
		if (coupValable)
			validerCoup(ligne, col);
		
	}

	public void validerCoup(int ligne, int col) {
		
		if (!joueur) {
			this.matJeu[ligne - 1][col - 1] = 1;
			Case cc = (Case)plateau.pane.getComponent((opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierVal(1);
		} else {
			this.matJeu[ligne - 1][col - 1] = 2;
			Case cc = (Case)plateau.pane.getComponent((opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierVal(2);
		}

		boolean gagne = this.joueurGagne(joueur, ligne - 1, col - 1);
		if (gagne) {
			if (opts.netOn)
				networkPlay(col, false);
			enCours = false;
			if (!joueur) {
				Saisie.erreurMsgOk("Le joueur 1 a gagne !", "Bravo au joueur 1!");
			}else{
				Saisie.erreurMsgOk("Le joueur 2 a gagne !", "Bravo au joueur 2!");
			}
		}
		
		nbCoups++;
		if (nbCoups >= opts.getGameHeight() * opts.getGameWidth() && !gagne) {
			if (opts.netOn)
				networkPlay(col, false);
			Saisie.erreurMsgOk("Aucun des 2 joueurs n'a su gagner... : partie nulle", "Partie nulle");
			enCours = false;
		}
		joueur = !joueur;
		if (joueur) {
			plateau.statusBar.setText("Le joueur 2 joue");
			plateau.statusBar.setIcon(plateau.pionV);
		}
		else {
			plateau.statusBar.setText("Le joueur 1 joue");
			plateau.statusBar.setIcon(plateau.pionR);
		}
	}

	public boolean coupValable(int ligne, int col) {
		
		if (ligne == -1) {
			Saisie.erreurMsgOk("Vous ne pouvez pas jouer ce coup : la colonne est remplie", "Coup invalide");
			return false;
		}
		
		if (!enCours) {
			Saisie.erreurMsgOk("La partie est terminée, vous ne pouvez plus jouer", "Erreur : partie termin�e");
			return false;
		}
		
		if (lock) {
			Saisie.erreurMsgOk("Ce n'est pas à vous de jouer", "Erreur");
			return false;
		}
		
		return true;
	}

	public int searchLine(int col) {
		for (int i = opts.getGameHeight(); i >= 1; i--) {
			if (matJeu[i - 1][col - 1] == 0)
				return i;
		}
		return -1;
	}

	public void networkPlay(int col, boolean wait) {

		if (!wait) {
			opts.sc.envoyerCoup(col);
			opts.sc.closeSocket();
		} else if (opts.joueur && nbCoups % 2 == 1 || !opts.joueur && nbCoups % 2 == 0) {
			opts.sc.envoyerCoup(col);
			lock = true;
			TheardServeurClient nt = new TheardServeurClient(opts.sc, this);
			nt.start();
		}

	}
}

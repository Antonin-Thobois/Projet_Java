package jeu;

import java.io.*;

public class Jeu {
    Interface interfaceJeu;    // L'interface du jeu
    boolean jeuTerminer;
    int nbCoups;
    boolean joueur;
    int hauteurJeu;
    int largeurJeu;

    public Jeu (){
        nbCoups = 0;
        jeuTerminer = false;
        hauteurJeu = 6;
        largeurJeu = 7;
    }

    public void jouer(int col) {
		boolean coupValable;
		int ligne = 0;
		
		coupValable = false;
			
		ligne = this.rechercherLigne(col);
		coupValable = this.coupValable(ligne, col);
		
		if (coupValable)
			validerCoup(ligne, col);
		
    }
    
    public void rechercherLigne(int col) {
        for(int i = hauteurJeu; i >= )
    }
}

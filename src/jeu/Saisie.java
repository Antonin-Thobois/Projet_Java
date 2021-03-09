package jeu;

import javax.swing.*;

public class Saisie {
	public static void erreurMsgOk(String message, String titre) {
		
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.ERROR_MESSAGE);
		
	}
	
}
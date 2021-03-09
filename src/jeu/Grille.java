package jeu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Grille extends JFrame implements MouseListener, ActionListener, WindowListener {
	static int nbGrilles;
	
	JPanel global = new JPanel();
	
	JPanel pane = new JPanel();
	GridLayout gridLay;
	
	JToolBar bar = new JToolBar();
	
	ImageIcon pionR = new ImageIcon("pionR.gif");
	ImageIcon pionV = new ImageIcon("pionV.gif");
	
	JLabel statusBar;
    
	public Grille(int nbRow, int nbCol, Jeu jeu) {
		super("Puissance 4");
		setSize(400, 300);
		setLocation(50, 50);
		
		global.setLayout(new BorderLayout());
		
		gridLay = new GridLayout(nbRow, nbCol, 0, 0);
		pane.setLayout(gridLay);

		global.add(bar, "North");
		
		makeCells(nbRow, nbCol, jeu);
		global.add(pane, "Center");
		
		statusBar = new JLabel("Le joueur 1 joue", pionR, JLabel.LEFT);
		global.add(statusBar, "South");
		
		setContentPane(global);
		setVisible(false);
		
		addWindowListener(this);
		
		nbGrilles++;
	}

	public void makeCells(int nbRow, int nbCol, Jeu jeu) {
		Case c;
		for (int i = 0; i < nbRow; i++) {
			for (int j = 0; j < nbCol; j++) {
				c = new Case(i + 1, j + 1, 0, jeu);
				c.addMouseListener(this);
				pane.add(c);
			}
		}	
	}
	
	public void mouseEntered(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		int col = src.col;
		int ligne = src.jeu.searchLine(col);
		if (ligne != -1) {
			Case cc = (Case)src.jeu.plateau.pane.getComponent((src.jeu.opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierBg(new Color(198, 198, 242));
			repaint();
		}
	}
	
	public void mouseExited(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		int col = src.col;
		int ligne = src.jeu.searchLine(col);
		if (ligne != -1) {
			Case cc = (Case)src.jeu.plateau.pane.getComponent((src.jeu.opts.getGameWidth()) * (ligne - 1) + (col - 1));
			cc.modifierBg(Color.white);
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		src.jeu.jouer(src.col);
	}
	
	public void mouseReleased(MouseEvent evt) {
		
	}
	
	public void windowActivated(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		nbGrilles--;
	}
	
	public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowIconified(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowOpened(java.awt.event.WindowEvent windowEvent) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

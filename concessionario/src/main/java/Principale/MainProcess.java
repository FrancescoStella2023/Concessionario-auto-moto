package Principale;

import gui.*;


import javax.swing.*;
import java.awt.*;

public class MainProcess {
	
	private JFrame frame;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	
	public MainProcess() {
		
		//Creare frame e card layout che ospiteranno le diverse schede
		frame = new JFrame("Gestore concessionario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,350);
		frame.setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		LoginUtente loginPanel = new LoginUtente(this);
		MostraFunzioni funzioniPanel = new MostraFunzioni(this);
		GestisciClienti clientiPanel = new GestisciClienti(this);
		
		cardPanel.add(loginPanel, "Login");
		cardPanel.add(funzioniPanel, "Elenco funzioni");
		cardPanel.add(clientiPanel, "Gestisci clienti");
		
		JPanel wrapper = new JPanel(new GridBagLayout());//Wrapper per centrare la gui
		wrapper.add(cardPanel);
		
		frame.setContentPane(wrapper);
		frame.setVisible(true);
		
	}
	
	public void showPanel(String name) {//Funzione che permette di cambiare scheda
		cardLayout.show(cardPanel, name);
	}
	
   
	public static void main(String args[]) {//Fa partire il programma
		SwingUtilities.invokeLater(() -> new MainProcess());
		
	}
	
}

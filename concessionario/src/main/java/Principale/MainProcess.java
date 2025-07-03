package Principale;

import gui.*;


import javax.swing.*;

import controller.GestisciLoginAndConnection;

import java.awt.*;
import java.sql.SQLException;

public class MainProcess {
	
	private JFrame frame;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private MostraFunzioni funzioniPanel;
	
	
	public MainProcess() {
		
		//Creare frame e card layout che ospiteranno le diverse schede
		frame = new JFrame("Gestore concessionario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550,400);
		frame.setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		
		//Inizializza e aggiungi al cardPanel le diverse schede
		LoginUtente loginPanel = new LoginUtente(this);
		funzioniPanel = new MostraFunzioni(this);
		GestisciClienti clientiPanel = new GestisciClienti(this);
		GestisciClub clubPanel = new GestisciClub(this);
		GestisciDipendenti dipendentiPanel = new GestisciDipendenti(this);
		GestisciMagazzino magazzinoPanel = new GestisciMagazzino(this);
		GestisciVeicoli veicoliPanel = new GestisciVeicoli(this);
		GestisciVendite venditePanel = new GestisciVendite(this);
		VisualizzaDati datiPanel = new VisualizzaDati(this);
		
		cardPanel.add(loginPanel, "Login");
		cardPanel.add(funzioniPanel, "Mostra funzioni");
		cardPanel.add(clientiPanel, "Gestisci clienti");
		cardPanel.add(clubPanel, "Gestisci club");
		cardPanel.add(dipendentiPanel, "Gestisci dipendenti");
		cardPanel.add(magazzinoPanel, "Gestisci magazzino");
		cardPanel.add(veicoliPanel, "Gestisci veicoli");
		cardPanel.add(venditePanel, "Gestisci vendite");
		cardPanel.add(datiPanel, "Visualizza dati");
		
		
		
		
		JPanel wrapper = new JPanel(new GridBagLayout());//Wrapper per centrare la gui
		wrapper.add(cardPanel);
		
		frame.setContentPane(wrapper);
		frame.setVisible(true);
		
	}
	
	public void showPanel(String name) {//Funzione che permette di cambiare scheda
		cardLayout.show(cardPanel, name);
	}
	
	
	public MostraFunzioni getMostraFunzioni() {
		return funzioniPanel;
	}
   
	public static void main(String args[]) {//Fa partire il programma
		
		try{ //avvia la connessione al database
			GestisciLoginAndConnection.startConnection("postgres", "20052206");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		SwingUtilities.invokeLater(() -> new MainProcess());
		
	}
	
}

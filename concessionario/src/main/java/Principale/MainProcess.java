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
	private LoginUtente loginPanel;
	
	private MostraFunzioni funzioniPanel;
	private GestisciClienti clientiPanel;
	private GestisciClub clubPanel;
	private GestisciDipendenti dipendentiPanel;
	private GestisciMagazzino magazzinoPanel;
	private GestisciVeicoli veicoliPanel;
	private GestisciVendite venditePanel;
	private VisualizzaDati datiPanel;
	
	
	public MainProcess() {
		
		//Creare frame e card layout che ospiteranno le diverse schede
		frame = new JFrame("Gestore concessionario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,450);
		frame.setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		
		//Inizializza e aggiungi al cardPanel le diverse schede
		loginPanel = new LoginUtente(this);
		funzioniPanel = new MostraFunzioni(this);
		clientiPanel = new GestisciClienti(this);
		clubPanel = new GestisciClub(this);
		dipendentiPanel = new GestisciDipendenti(this);
		magazzinoPanel = new GestisciMagazzino(this);
		veicoliPanel = new GestisciVeicoli(this);
		venditePanel = new GestisciVendite(this);
		datiPanel = new VisualizzaDati(this);
		
		//aggiunge tutti i card panel
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
		clientiPanel.clear();
		clubPanel.clear();
		dipendentiPanel.clear();
		magazzinoPanel.clear();
		veicoliPanel.clear();
		venditePanel.clear();
	}
	
	
	public MostraFunzioni getMostraFunzioni() {//Passa funzioniPanel per aggiornarlo in Login
		return funzioniPanel;
	}
   
	public JFrame getFrame() { //ritorna il frame, usato per cambiare il nome dinamicamente alla finestra
		return frame;
	}
	
	public static void main(String args[]) {//Fa partire il programma
		
		try{ //avvia la connessione al database
			GestisciLoginAndConnection.startConnection("postgres", "20052206");
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		SwingUtilities.invokeLater(() -> new MainProcess()); //garantisce che la gui venga inizializzata nel thread corretto
		
	}
	
}

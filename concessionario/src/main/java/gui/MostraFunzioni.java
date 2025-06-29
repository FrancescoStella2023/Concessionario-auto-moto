package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import Principale.*;

public class MostraFunzioni extends JPanel {
		
	private JButton gestisciClientiButton;
	private JButton gestisciClubButton;
	private JButton gestisciDipendentiButton;
	private JButton gestisciMagazzinoButton;
	private JButton gestisciVeicoliButton;
	private JButton gestisciVenditeButton;
	private JButton visualizzaDatiButton;
	private JButton backButton;
	
	private MainProcess mainProcess;
		
	public MostraFunzioni (MainProcess main) {
		
		this.mainProcess = main;//Accesso al mainProcess per chiamare showPanel
		
		//Set up grafica interfaccia
		FormLayout layout = new FormLayout(
			"40dlu, left:pref, 50dlu, pref",
			"pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
		);
			
		setLayout(layout);
		CellConstraints cc = new CellConstraints();
		
		//Da implementare un if check per is admin che rende visibili solo i bottoni a cui l'utente ha accesso
		
		gestisciClientiButton = new JButton("Gestisci Clienti");
	    gestisciClubButton = new JButton("Gestisci Club");
	    gestisciDipendentiButton = new JButton("Gestisci Dipendenti");
	    gestisciMagazzinoButton = new JButton("Gestisci Magazzino");
	    gestisciVeicoliButton = new JButton("Gestisci Veicoli");
	    gestisciVenditeButton = new JButton("Gestisci Vendite");
	    visualizzaDatiButton = new JButton("Visualizza Dati");
	    backButton = new JButton("Esci");
		
		add(gestisciClientiButton, cc.xy(2, 1));
		add(gestisciClubButton, cc.xy(2, 3));
		add(gestisciDipendentiButton, cc.xy(2, 5));
		add(gestisciMagazzinoButton, cc.xy(2, 7));
		add(gestisciVeicoliButton, cc.xy(2, 9));
		add(gestisciVenditeButton, cc.xy(2, 11));
		add(visualizzaDatiButton, cc.xy(2, 13));
		add(backButton, cc.xy(4, 13));
		
		gestisciClientiButton.addActionListener(buttonPressed(0));
		gestisciClubButton.addActionListener(buttonPressed(1));
		gestisciDipendentiButton.addActionListener(buttonPressed(2));
		gestisciMagazzinoButton.addActionListener(buttonPressed(3));
		gestisciVeicoliButton.addActionListener(buttonPressed(4));
		gestisciVenditeButton.addActionListener(buttonPressed(5));
		visualizzaDatiButton.addActionListener(buttonPressed(6));
		backButton.addActionListener(buttonPressed(7));
		
	}
	
		
	private ActionListener buttonPressed(int numScheda) {//Usa uno switch per aprire le schede corrette
		 return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				switch (numScheda) {
					case 0:
						mainProcess.showPanel("Gestisci clienti");
						break;
					case 1:
						mainProcess.showPanel("Gestisci club");
						break;
					case 2:
						System.out.println("Gestisci Dipendenti");
						break;
					case 3:
						mainProcess.showPanel("Gestisci magazzino");
						break;
					case 4:
						mainProcess.showPanel("Gestisci veicoli");
						break;
					case 5:
						System.out.println("Gestisci Vendite");
						break;
					case 6:
						mainProcess.showPanel("Visualizza dati");
						break;
					case 7:
						mainProcess.showPanel("Login");
						break;
				}
            }
        };
	}
}
	
	
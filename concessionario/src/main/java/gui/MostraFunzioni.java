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
	
	private MainProcess mainProcess;
		
	public MostraFunzioni (MainProcess main) {
		
		this.mainProcess = main;//Accesso al mainProcess per chiamare showPanel
		
		//Set up grafica interfaccia
		FormLayout layout = new FormLayout(
			"40dlu, left:pref",
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
		
		add(gestisciClientiButton, cc.xy(2, 1));
		add(gestisciClubButton, cc.xy(2, 3));
		add(gestisciDipendentiButton, cc.xy(2, 5));
		add(gestisciMagazzinoButton, cc.xy(2, 7));
		add(gestisciVeicoliButton, cc.xy(2, 9));
		add(gestisciVenditeButton, cc.xy(2, 11));
		add(visualizzaDatiButton, cc.xy(2, 13));
		
		gestisciClientiButton.addActionListener(Test(0));
		gestisciClubButton.addActionListener(Test(1));
		gestisciDipendentiButton.addActionListener(Test(2));
		gestisciMagazzinoButton.addActionListener(Test(3));
		gestisciVeicoliButton.addActionListener(Test(4));
		gestisciVenditeButton.addActionListener(Test(5));
		visualizzaDatiButton.addActionListener(Test(6));
	}
	
		
	private ActionListener Test(int numScheda) {//Usa uno switch per aprire le schede corrette
		 return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				switch (numScheda) {
					case 0:
						System.out.println("Gestisci Clienti");
						mainProcess.showPanel("Gestisci clienti");
						break;
					case 1:
						System.out.println("Gestisci Club");
						break;
					case 2:
						System.out.println("Gestisci Dipendenti");
						break;
					case 3:
						System.out.println("Gestisci Magazzino");
						break;
					case 4:
						System.out.println("Gestisci Veicoli");
						break;
					case 5:
						System.out.println("Gestisci Vendite");
						break;
					case 6:
						System.out.println("Visualizza Dati");
						break;
				}
            }
        };
	}
}
	
	
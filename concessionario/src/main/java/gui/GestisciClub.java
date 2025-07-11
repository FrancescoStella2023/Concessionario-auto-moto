package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import Principale.*;
import controller.ProcessQueryGestioneClienti;
import controller.ProcessQueryGestioneClub;
import exceptions.InvalidInputException;

public class GestisciClub extends JPanel{
	
	private JTextField nomeClubField;
	private JTextField percentualeDiScontoField;
	private JButton enterButton;
	
	private JButton backButton;
	
	private MainProcess mainProcess;
	
	public GestisciClub(MainProcess main) {
		
		this.mainProcess = main;
		
		FormLayout layout = new FormLayout(
				"default:grow, right:pref, 4dlu, 100dlu, default:grow",
				"50dlu, pref, 4dlu, pref, 4dlu, pref"
		);
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel formPanel = new JPanel(layout);
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Inserisci club", formPanel);

		
		nomeClubField = new JTextField();
		percentualeDiScontoField = new JTextField();
		
		enterButton = new JButton("Enter");
		backButton = new JButton("Back");
		
		Dimension buttonSize = new Dimension(220, 25);
	    enterButton.setPreferredSize(buttonSize);
		
		CellConstraints cc = new CellConstraints();
		
		formPanel.add(new JLabel("Nome club:"), cc.xy(2,2));
		formPanel.add(nomeClubField, cc.xy(4,2));
		
		formPanel.add(new JLabel("Percentuale di sconto:"), cc.xy(2,4));
		formPanel.add(percentualeDiScontoField, cc.xy(4,4));
		
		
		formPanel.add(enterButton, cc.xy(4, 6));
		enterButton.addActionListener(e ->{//Aggiungi cliente
			try {
				ProcessQueryGestioneClub.eseguiQueryClub(getNome(), getPercentualeDiSconto());
				JOptionPane.showMessageDialog(this, "Club inserito con successo.");
				mainProcess.showPanel("Mostra funzioni"); //ritorna al menu
				
			}
			catch(InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
				
		});
		
		add(backButton, BorderLayout.SOUTH);
		backButton.addActionListener(backPressed());
		
	}
	
	//Getters per prendere i dati dai field
	public String getNome() { return nomeClubField.getText(); }
	public String getPercentualeDiSconto() { return percentualeDiScontoField.getText(); }
	
	
	private ActionListener backPressed(){//Ritorna a MostraFunzioni
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}
	
	public void clear() {//Ripulisci diversi field
        nomeClubField.setText(null);
		percentualeDiScontoField.setText(null);
    }
}

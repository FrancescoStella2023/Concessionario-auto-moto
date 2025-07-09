package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import Principale.*;
import controller.ProcessQueryGestioneClienti;
import exceptions.InvalidInputException;

public class GestisciClienti extends JPanel {
	
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JTextField numeroTelefonoField;
    private JTextField comuneField;
    private JTextField numCivicoField;
    private JTextField indirizzoField;
    private JTextField idClubField;
	private JButton enterButton;
	private JButton backButton;
	
	private MainProcess mainProcess;
	
	public GestisciClienti(MainProcess main) {
       	
       	this.mainProcess = main;
		
		FormLayout layout = new FormLayout(
            "right:pref, 4dlu, 150dlu",
            "pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
        );
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel formPanel = new JPanel(layout);
		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Aggiungi clienti", formPanel);


        nomeField = new JTextField();
        cognomeField = new JTextField();
        emailField = new JTextField();
        numeroTelefonoField = new JTextField();
        comuneField = new JTextField();
        numCivicoField = new JTextField();
        indirizzoField = new JTextField();
        idClubField = new JTextField();
        
        
        enterButton = new JButton("Enter");
        backButton = new JButton("Back");
        
        CellConstraints cc = new CellConstraints();

        formPanel.add(new JLabel("Nome (solo caratteri):"), cc.xy(1, 1));
        formPanel.add(nomeField, cc.xy(3, 1));

        formPanel.add(new JLabel("Cognome (solo caratteri):"), cc.xy(1, 3));
        formPanel.add(cognomeField, cc.xy(3, 3));

        formPanel.add(new JLabel("Email (almeno una @):"), cc.xy(1, 5));
        formPanel.add(emailField, cc.xy(3, 5));

        formPanel.add(new JLabel("Telefono (solo cifre):"), cc.xy(1, 7));
        formPanel.add(numeroTelefonoField, cc.xy(3, 7));

        formPanel.add(new JLabel("Comune (solo caratteri):"), cc.xy(1, 9));
        formPanel.add(comuneField, cc.xy(3, 9));

        formPanel.add(new JLabel("Num. Civico (solo cifre):"), cc.xy(1, 11));
        formPanel.add(numCivicoField, cc.xy(3, 11));

        formPanel.add(new JLabel("Indirizzo (solo caratteri):"), cc.xy(1, 13));
        formPanel.add(indirizzoField, cc.xy(3, 13));

        formPanel.add(new JLabel("ID Club (solo cifre, 0 nessun club):"), cc.xy(1, 15));
        formPanel.add(idClubField, cc.xy(3, 15));
		
		
		Dimension buttonSize = new Dimension(220, 25);
	    enterButton.setPreferredSize(buttonSize);
	    formPanel.add(enterButton, cc.xy(3, 17));
        
        
        
		enterButton.addActionListener(e -> {
			try {
				
				ProcessQueryGestioneClienti.eseguiQueryNewCliente(getNome(), getCognome(), getEmail(), getNumeroTelefono(), getComune(), getNumCivico(), getIndirizzo(), getIdClub());
				JOptionPane.showMessageDialog(this, "Cliente inserito con successo.");
				mainProcess.showPanel("Mostra funzioni"); //ritorna al menu

			}
			catch(InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
		});
		
		add(backButton, BorderLayout.SOUTH);
		backButton.addActionListener(backPressed());
	}
	
	
	// Getters per DAO o salvataggio
	private String getNome() { return nomeField.getText(); }
	private String getCognome() { return cognomeField.getText(); }
	private String getEmail() { return emailField.getText(); }
	private String getNumeroTelefono() { return numeroTelefonoField.getText(); }
	private String getComune() {  return comuneField.getText(); }
	private String getNumCivico() { return numCivicoField.getText();  }
	private String getIndirizzo() {  return indirizzoField.getText(); }
	
	private String getIdClub() {//impostare a zero il valore del club se è vuoto
		 String idClub = idClubField.getText(); 
		 
		 if (idClub.isEmpty()) {
			 return "0";
		} else {
			return idClub;
		}
		 
	}
	
	
	private ActionListener backPressed(){//Ritorna a MostraFunzioni
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}
	
}


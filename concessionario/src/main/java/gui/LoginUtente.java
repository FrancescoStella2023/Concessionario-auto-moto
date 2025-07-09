package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;

import Principale.*;
import controller.GestisciLoginAndConnection;
import exceptions.*;

public class LoginUtente extends JPanel {
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel label;
	private JButton loginButton;
	
	private MainProcess mainProcess;
	
	public LoginUtente (MainProcess main) {
		
		this.mainProcess = main;
		
		
		FormLayout layout = new FormLayout(
			"right:pref, 4dlu, 150dlu",
			"75dlu, pref, 4dlu, pref, 4dlu, pref"
		);
		setLayout(layout);
		
		label = new JLabel("Inserisci le credenziali per accedere");
		label.setFont(label.getFont().deriveFont(java.awt.Font.BOLD, 13f));
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("Login");
		
		CellConstraints cc = new CellConstraints();
		
		add(label, cc.xy(1, 1));
		add(new JLabel("Username (id dipendente):"), cc.xy(1,2));
		add(usernameField, cc.xy(3,2));
		
		add(new JLabel("Password:"), cc.xy(1,4));
		add(passwordField, cc.xy(3,4));
		
		add(loginButton, cc.xy(3, 6));
		
		loginButton.addActionListener(e -> {try{
        	
        	GestisciLoginAndConnection.startLogin(getUsername(), getPassword());
            mainProcess.getMostraFunzioni().AggiornaBottoni();
            mainProcess.showPanel("Mostra funzioni");
        }
        catch(InvalidCredentialsException ex) {
        	ex.showErrorDialogPanel(this);
        }});
	}
	
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	
	public String getPassword() {
		return new String(passwordField.getPassword());
	}
	
}

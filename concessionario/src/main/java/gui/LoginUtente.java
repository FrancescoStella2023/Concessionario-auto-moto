package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUtente extends JPanel {
	
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton loginButton;
	
	
	public LoginUtente () {
		
		FormLayout layout = new FormLayout(
			"right:pref, 4dlu, 100dlu",
			"pref, 4dlu, pref, 4dlu, pref"
		);
		setLayout(layout);
		
		usernameField = new JTextField();
		passwordField = new JTextField();
		loginButton = new JButton("Login");
		
		CellConstraints cc = new CellConstraints();
		
		add(new JLabel("Username:"), cc.xy(1,1));
		add(usernameField, cc.xy(3,1));
		
		add(new JLabel("Password:"), cc.xy(1,3));
		add(passwordField, cc.xy(3,3));
		
		add(loginButton, cc.xy(3, 5));
		
		loginButton.addActionListener(Test()); //Placeholder
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	public String getPassword() {
		return passwordField.getText();
	}
	
	private ActionListener Test() { //Controllare se i text field e button funzionano
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pressed");
				System.out.println("Username - " + getUsername());
				System.out.println("Password - " + getPassword());
			}
		};
	}
	
}

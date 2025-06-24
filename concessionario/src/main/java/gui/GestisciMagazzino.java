package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class GestisciMagazzino extends JPanel {
	
	private JTextField idMagazzino;
	private JTextField indirizzoField;
	private JButton enterButton;
	
	public GestisciMagazzino() {
		
		FormLayout layout = new FormLayout(
			"right:pref, 4dlu, 100dlu",
			"pref, 4dlu, pref, 4dlu, pref"
		);
		setLayout(layout);
		
		indirizzoField = new JTextField();
		enterButton = new JButton("Enter");
		
		CellConstraints cc = new CellConstraints();
		
		add(new JLabel("Indirizzo:"), cc.xy(1,1));
		add(indirizzoField, cc.xy(3,1));
		

		
		add(loginButton, cc.xy(3, 5));
		
		loginButton.addActionListener(Test());
		
	}
	
	private ActionListener Test(){
		
	}
	
}

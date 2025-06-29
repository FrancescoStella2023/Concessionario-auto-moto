package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import Principale.*;


public class GestisciMagazzino extends JPanel {
	
	private JTextField idMagazzino;
	private JTextField indirizzoField;
	private JButton enterButton;
	private JButton backButton;
	
	MainProcess mainProcess;
	
	public GestisciMagazzino(MainProcess main) {
		
		this.mainProcess = main;
		
		FormLayout layout = new FormLayout(
			"right:pref, 4dlu, 100dlu",
			"pref, 4dlu, pref, 4dlu, pref"
		);
		setLayout(layout);
		
		indirizzoField = new JTextField();
		enterButton = new JButton("Inserisci");
		backButton = new JButton("Back");
		
		CellConstraints cc = new CellConstraints();
		
		add(new JLabel("Indirizzo:"), cc.xy(1,1));
		add(indirizzoField, cc.xy(3,1));
		
		
		add(enterButton, cc.xy(3, 5));
		enterButton.addActionListener(enterPressed());
		
		add(backButton, cc.xy(1,  5));
		backButton.addActionListener(backPressed());
		
	}
	
	
	// Getter per DAO o salvataggio
	public String getIndirizzo() { return indirizzoField.getText(); }
	
	
	private ActionListener enterPressed(){
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                System.out.println("Indirizzo - " + getIndirizzo());
            }
        };
	}
	
	private ActionListener backPressed(){
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}
	
}

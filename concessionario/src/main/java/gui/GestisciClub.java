package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import Principale.*;

public class GestisciClub extends JPanel{
	
	private JTextField nomeClubField;
	private JTextField percentualeDiScontoField;
	private JButton enterButton;
	private JButton backButton;
	
	private MainProcess mainProcess;
	
	public GestisciClub(MainProcess main) {
		
		this.mainProcess = main;
		
		FormLayout layout = new FormLayout(
				"right:pref, 4dlu, 100dlu",
				"pref, 4dlu, pref, 4dlu, pref"
		);
	    setLayout(layout);
		
		nomeClubField = new JTextField();
		percentualeDiScontoField = new JTextField();
		
		enterButton = new JButton("Enter");
		backButton = new JButton("Back");
		
		CellConstraints cc = new CellConstraints();
		
		add(new JLabel("Nome club:"), cc.xy(1,1));
		add(nomeClubField, cc.xy(3,1));
		
		add(new JLabel("Percentuale di sconto:"), cc.xy(1,3));
		add(percentualeDiScontoField, cc.xy(3,3));
		
		
		add(enterButton, cc.xy(3, 5));
		enterButton.addActionListener(enterPressed());
		
		add(backButton, cc.xy(1,  5));
		backButton.addActionListener(backPressed());
		
	}
	
	// Getters per DAO o salvataggio
	public String getNome() { return nomeClubField.getText(); }
	public String getPercentualeDiSconto() { return percentualeDiScontoField.getText(); }
	
	private ActionListener enterPressed(){
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
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

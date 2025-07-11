package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import Principale.*;
import controller.ProcessQueryGestioneMagazzini;
import exceptions.InvalidInputException;


public class GestisciMagazzino extends JPanel {

	private JTextField indirizzoField;
	private JButton enterButton;
	private JButton backButton;

	private JTabbedPane tabbedPane;
	private JPanel pannelloInserimento;

	MainProcess mainProcess;

	public GestisciMagazzino(MainProcess main) {
		this.mainProcess = main;

		tabbedPane = new JTabbedPane();
		pannelloInserimento = new JPanel();

		FormLayout layout = new FormLayout(
			"default:grow, right:pref, 4dlu, 100dlu, default:grow",
			"50dlu, pref, 4dlu, pref, 4dlu, pref"
		);
		pannelloInserimento.setLayout(layout);

		indirizzoField = new JTextField();
		enterButton = new JButton("Inserisci");
		backButton = new JButton("Back");
		
		Dimension buttonSize = new Dimension(220, 25);
	    enterButton.setPreferredSize(buttonSize);

		CellConstraints cc = new CellConstraints();

		pannelloInserimento.add(new JLabel("Indirizzo:"), cc.xy(2,4));
		pannelloInserimento.add(indirizzoField, cc.xy(4,4));

		pannelloInserimento.add(enterButton, cc.xy(4, 6));
		enterButton.addActionListener(e -> {
			try {
				ProcessQueryGestioneMagazzini.eseguiQueryMagazzino(getIndirizzo());
				JOptionPane.showMessageDialog(this, "Maggazzino inserito con successo.");
				mainProcess.showPanel("Mostra funzioni"); //ritorna al menu
				
			} catch (InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
		});

		backButton.addActionListener(backPressed());

		tabbedPane.addTab("Inserisci Magazzino", pannelloInserimento);

		setLayout(new BorderLayout());
		add(tabbedPane, BorderLayout.CENTER);
		add(backButton, BorderLayout.SOUTH);
	}
	
	public String getIndirizzo() { return indirizzoField.getText(); }

	private ActionListener backPressed(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainProcess.showPanel("Mostra funzioni");
			}
		};
	}
	
	public void clear() {
		indirizzoField.setText(null);
    }
}

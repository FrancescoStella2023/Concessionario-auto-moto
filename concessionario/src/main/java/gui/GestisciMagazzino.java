package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
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
			"right:pref, 4dlu, 100dlu",
			"pref, 4dlu, pref, 4dlu, pref"
		);
		pannelloInserimento.setLayout(layout);

		indirizzoField = new JTextField();
		enterButton = new JButton("Inserisci");
		backButton = new JButton("Back");

		CellConstraints cc = new CellConstraints();

		pannelloInserimento.add(new JLabel("Indirizzo:"), cc.xy(1,1));
		pannelloInserimento.add(indirizzoField, cc.xy(3,1));

		pannelloInserimento.add(enterButton, cc.xy(3, 5));
		enterButton.addActionListener(e -> {
			try {
				ProcessQueryGestioneMagazzini.eseguiQueryMagazzino(getIndirizzo());
				JOptionPane.showMessageDialog(this, "Maggazzino inserito con successo.");
				
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
}

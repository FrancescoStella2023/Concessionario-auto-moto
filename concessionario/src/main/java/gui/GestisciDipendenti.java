package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import Principale.*;
import controller.ProcessQueryGestioneDipendenti;
import controller.ProcessQueryMostraDati;
import exceptions.InvalidInputException;

//------------Temporaneo, da riorganizzare/riscrivere

public class GestisciDipendenti extends JPanel{
	
	private JTextField idDipendenteNewPassField;
	private JTextField newPasswordField;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField passwordField;
    private JTextField isAdminField;
    
    private JButton backButton;
    
    private JRadioButton trueRadio;
    private JRadioButton falseRadio;
    private ButtonGroup tipoGruppo;

    private ArrayList<String> listaDipendenti;

    private JPanel pannelloAdd;
    private JPanel pannelloNewPass;
    private JPanel pannelloViewDipendenti;
    
    private ArrayList<String[]> datiDipendenti;

    private JTabbedPane tabbedPane;
    
    MainProcess mainProcess;
    
    public GestisciDipendenti(MainProcess main) {
    	
    	this.mainProcess = main;
    	
        listaDipendenti = new ArrayList<>();
        tabbedPane = new JTabbedPane();
        
        backButton = new JButton("Back");
        
        //Diviso in diverse funzioni per tenere ordinato
        creaPannelloVisualizzazione();
        creaPannelloAggiunta();
        creaPannelloCambiaPassword();
        
        tabbedPane.addTab("Visualizza", pannelloViewDipendenti);
        tabbedPane.addTab("Aggiungi", pannelloAdd);
        tabbedPane.addTab("Cambia Password", pannelloNewPass);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
        populateData();
    }
    
    public void populateData(){
		try {
			datiDipendenti = new ArrayList<>();
			ArrayList<String[]> dataDip = ProcessQueryMostraDati.eseguiQueryView("dipendenti");
			datiDipendenti.add(dataDip.get(0)); //intestazioni colonne
			datiDipendenti.add(dataDip.get(1)); //dati effettivi
			
		} catch (SQLException e) {
			
        	JOptionPane.showMessageDialog(this, "Errore nella visualizzazione dei dati, riprova.");
        	
		}
        
        popolaPanelConDati(pannelloViewDipendenti, datiDipendenti);
        
        revalidate();
        repaint();
    }
    
    private void popolaPanelConDati(JPanel panel, ArrayList<String[]> dati) {
	    panel.removeAll();
	
	    if (dati == null || dati.size() < 2) return;
	
	    //Separare titoli da contenuto
	    String[] intestazioni = dati.get(0);
	    String[] valori = dati.get(1);
	
	    int colonne = intestazioni.length;
	    int righe = valori.length / colonne;
	
	    int posColonnaPassword = 0; //salva il numero della colonna delle password per non non farle vedere
	    
	    // Container verticale che contiene le righe
	    JPanel contenitore = new JPanel();
	    contenitore.setLayout(new BoxLayout(contenitore, BoxLayout.Y_AXIS));
	
	    // Riga intestazioni (Colore più marcato per differenziarle)
	    JPanel headerRow = new JPanel(new GridLayout(1, colonne));
	    for (String intestazione : intestazioni) {
	        if(!intestazione.equals("password")) {
	        	JLabel label = new JLabel(intestazione, SwingConstants.CENTER);
		        label.setFont(label.getFont().deriveFont(java.awt.Font.BOLD, 11f));//Rimpicciolisci font
		        label.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
		        headerRow.add(label);
		        posColonnaPassword++; //incrementa la posizione della colonna password
	        }
	        
	    }
	    contenitore.add(headerRow);
	
	    // Righe dati
	    for (int i = 0; i < righe; i++) {
	        JPanel dataRow = new JPanel(new GridLayout(1, colonne));
	        for (int j = 0; j < colonne; j++) {
	            if(j != posColonnaPassword-1) { //se sta cercando di stampare la colonna delle password la salta
		        	int index = i * colonne + j;
		            JLabel cella = new JLabel(valori[index], SwingConstants.CENTER);
		            cella.setFont(cella.getFont().deriveFont(11f));//Rimpicciolisci font
		            cella.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
		            dataRow.add(cella);
	            }
	        }
	        contenitore.add(dataRow);
	    }
	
	    panel.setLayout(new BorderLayout());
	    panel.add(contenitore, BorderLayout.NORTH);
	
	    panel.revalidate();
	    panel.repaint();
}

    private void creaPannelloAggiunta() {
    	pannelloAdd = new JPanel(new GridLayout(7, 2, 10, 10));

    	nomeField = new JTextField();
    	cognomeField = new JTextField();
    	passwordField = new JTextField();
    	isAdminField = new JTextField();
    	isAdminField.setVisible(false); // campo nascosto

    	trueRadio = new JRadioButton("true");
    	falseRadio = new JRadioButton("false");
    	tipoGruppo = new ButtonGroup();
    	tipoGruppo.add(trueRadio);
    	tipoGruppo.add(falseRadio);

    	falseRadio.setSelected(true);
    	isAdminField.setText("false");

    	trueRadio.addActionListener(e -> isAdminField.setText("true"));
    	falseRadio.addActionListener(e -> isAdminField.setText("false"));

    	pannelloAdd.add(new JLabel("Nome (solo caratteri):"));
    	pannelloAdd.add(nomeField);
    	pannelloAdd.add(new JLabel("Cognome (solo caratteri):"));
    	pannelloAdd.add(cognomeField);
    	pannelloAdd.add(new JLabel("Password:"));
    	pannelloAdd.add(passwordField);
    	pannelloAdd.add(new JLabel("È Admin:"));

    	// Pannello per i radio
    	JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	radioPanel.add(trueRadio);
    	radioPanel.add(falseRadio);
    	falseRadio.setSelected(true); //di base è false
    	pannelloAdd.add(radioPanel);

    	pannelloAdd.add(isAdminField); // campo invisibile

    	JButton aggiungiButton = new JButton("Aggiungi");
    	aggiungiButton.addActionListener(e -> {
    	    try {
    	        ProcessQueryGestioneDipendenti.eseguiQueryNewDipendente(getNome(), getCognome(), getPassword(), getIsAdmin());
    	        JOptionPane.showMessageDialog(this, "Dipendente aggiunto.");
    	        populateData(); //aggiorna la view
    	    } catch (InvalidInputException ex) {
    	        ex.showErrorDialogPanel(this);
    	    }
    	});
    	pannelloAdd.add(new JLabel());
    	pannelloAdd.add(aggiungiButton, 9);
    }

    public String getNome() { return nomeField.getText(); }
	public String getCognome() { return cognomeField.getText(); }
	public String getPassword() { return passwordField.getText(); }
	public String getIsAdmin() { return isAdminField.getText(); }
	
    private void creaPannelloCambiaPassword() {
        pannelloNewPass = new JPanel(new GridLayout(3, 2, 10, 10));

        idDipendenteNewPassField = new JTextField();
        newPasswordField = new JTextField();

        pannelloNewPass.add(new JLabel("ID Dipendente (solo cifre):"));
        pannelloNewPass.add(idDipendenteNewPassField);
        pannelloNewPass.add(new JLabel("Nuova Password:"));
        pannelloNewPass.add(newPasswordField);

        JButton cambiaPassButton = new JButton("Cambia Password");
        cambiaPassButton.addActionListener(e -> {
            try {
				ProcessQueryGestioneDipendenti.eseguiQueryCambiaPassword(getIdDipendenteNew(), getPasswordNew());
				JOptionPane.showMessageDialog(this, "Password aggiornata.");
			} 
            catch (InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
        	
        });

        pannelloNewPass.add(new JLabel());
        pannelloNewPass.add(cambiaPassButton, 5);
    }
    
    public String getIdDipendenteNew() { return idDipendenteNewPassField.getText(); }
	public String getPasswordNew() { return newPasswordField.getText(); }

    private void creaPannelloVisualizzazione() {
        pannelloViewDipendenti = new JPanel(new BorderLayout());
        aggiornaVisualizzazione();
    }

    private void aggiornaVisualizzazione() {
        pannelloViewDipendenti.removeAll();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String dip : listaDipendenti) {
            listModel.addElement(dip);
        }

        JList<String> lista = new JList<>(listModel);
        pannelloViewDipendenti.add(new JScrollPane(lista), BorderLayout.CENTER);
        pannelloViewDipendenti.revalidate();
        pannelloViewDipendenti.repaint();
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

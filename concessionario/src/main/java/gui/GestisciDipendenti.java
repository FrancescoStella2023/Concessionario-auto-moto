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


public class GestisciDipendenti extends JPanel{
	
	private JTabbedPane tabbedPane;
	
    //Campi new password
    private JPanel pannelloNewPass;
	private JTextField idDipendenteNewPassField;
	private JTextField newPasswordField;
	
	//Campi add
	private JPanel pannelloAdd;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField passwordField;
    private JTextField isAdminField;
    private JRadioButton trueRadio;
    private JRadioButton falseRadio;
    private ButtonGroup tipoGruppo;
    
    //Campi view
    private JPanel pannelloViewDipendenti;
    private ArrayList<String> listaDipendenti;
    private ArrayList<String[]> datiDipendenti;
    
    private JButton backButton;
    
    private MainProcess mainProcess;
    
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
    
    private void populateData(){
		try {
			datiDipendenti = new ArrayList<>();
			ArrayList<String[]> dataDip = ProcessQueryMostraDati.eseguiQueryView("dipendenti");
			datiDipendenti.add(dataDip.get(0)); //Intestazioni colonne
			datiDipendenti.add(dataDip.get(1)); //Dati effettivi
			
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
	
	    int posColonnaPassword = 0; //Aalva il numero della colonna delle password per non non farle vedere
	    
	    //Container verticale che contiene le righe
	    JPanel contenitore = new JPanel();
	    contenitore.setLayout(new BoxLayout(contenitore, BoxLayout.Y_AXIS));
	
	    //Riga intestazioni (Colore più marcato per differenziarle)
	    JPanel headerRow = new JPanel(new GridLayout(1, colonne));
	    for (String intestazione : intestazioni) {
	        if(!intestazione.equals("password")) {
	        	JLabel label = new JLabel(intestazione, SwingConstants.CENTER);
		        label.setFont(label.getFont().deriveFont(java.awt.Font.BOLD, 11f));//Rimpicciolisci font
		        label.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
		        headerRow.add(label);
		        posColonnaPassword++; //Incrementa la posizione della colonna password
	        }
	        
	    }
	    contenitore.add(headerRow);
	
	    //Righe dati
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
    	isAdminField.setVisible(false); //Campo nascosto

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

    	//Pannello per i radio
    	JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	radioPanel.add(trueRadio);
    	radioPanel.add(falseRadio);
    	falseRadio.setSelected(true); //Di base è false
    	pannelloAdd.add(radioPanel);

    	pannelloAdd.add(isAdminField); //Campo invisibile

    	JButton aggiungiButton = new JButton("Aggiungi");
    	aggiungiButton.addActionListener(e -> {
    	    try {
    	        ProcessQueryGestioneDipendenti.eseguiQueryNewDipendente(getNome(), getCognome(), getPassword(), getIsAdmin());
    	        JOptionPane.showMessageDialog(this, "Dipendente aggiunto.");
    	        populateData(); //Aggiorna la view
    	        clear();
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
        pannelloNewPass = new JPanel();
        
        FormLayout layout = new FormLayout(
			"default:grow, left:pref, 10dlu, 150dlu, default:grow",
			"25dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu"
		);
		
		pannelloNewPass.setLayout(layout);
		CellConstraints cc = new CellConstraints();
        
        idDipendenteNewPassField = new JTextField();
        newPasswordField = new JTextField();

        pannelloNewPass.add(new JLabel("ID Dipendente (solo cifre):"), cc.xy(2,2));
        pannelloNewPass.add(idDipendenteNewPassField, cc.xy(4,2));
        pannelloNewPass.add(new JLabel("Nuova Password:"), cc.xy(2,4));
        pannelloNewPass.add(newPasswordField, cc.xy(4,4));

        JButton cambiaPassButton = new JButton("Cambia Password");
        cambiaPassButton.addActionListener(e -> {
            try {
				ProcessQueryGestioneDipendenti.eseguiQueryCambiaPassword(getIdDipendenteNew(), getPasswordNew());
				JOptionPane.showMessageDialog(this, "Password aggiornata.");
    	        clear();
			} 
            catch (InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
        	
        });

        pannelloNewPass.add(cambiaPassButton, cc.xy(4,6));
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
    
    private ActionListener backPressed(){//Ritorna a MostraFunzioni
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}
	
	public void clear() {//Ripulisci diversi field
		idDipendenteNewPassField.setText(null);
		newPasswordField.setText(null);
    	nomeField.setText(null);
    	cognomeField.setText(null);
    	passwordField.setText(null);
    	isAdminField.setText(null);
    }
}

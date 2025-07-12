package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Principale.*;
import controller.ProcessQueryGestioneVendite;
import exceptions.InvalidInputException;

public class GestisciVendite extends JPanel {
    
    private JButton backButton;
    private JTabbedPane tabbedPane;
    
    
    // Campi vendita
    private JPanel pannelloVendita;
    private JFormattedTextField dataVenditaField;
    private JLabel prezzoFinaleLabel;
    private JButton calcolaPrezzoFinaleButton;
    private JTextField idClienteEsistenteField;
    private JTextField numeroTelaioField;
    private JButton venditaButton;
    
    // Campi restituzione
    private JPanel pannelloRestituzione;
    private JFormattedTextField dataRestituzioneField;
    private JTextField idVenditaRestField;
    private JButton restituzioneButton;
    
    private MainProcess mainProcess;
    
    public GestisciVendite(MainProcess main) {
    	
        this.mainProcess = main;
        
        tabbedPane = new JTabbedPane();
        
        backButton = new JButton("Back");
        
        //Diviso in diverse funzioni per tenere ordinato
        creaPannelloVendita();
        creaPannelloRestituzione();
        
        tabbedPane.addTab("Nuova Vendita", pannelloVendita);
        tabbedPane.addTab("Restituzione", pannelloRestituzione);
        
        
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
    }
    
    private void creaPannelloVendita() {
        pannelloVendita = new JPanel();
        
        FormLayout layout = new FormLayout(
            "right:pref, 4dlu, 150dlu, 10dlu, pref",
            "pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
        );
        
        pannelloVendita.setLayout(layout);
        
        CellConstraints cc = new CellConstraints();
        
        dataVenditaField = new JFormattedTextField();
        prezzoFinaleLabel = new JLabel("€ 0.00");
        calcolaPrezzoFinaleButton = new JButton("Calcola Prezzo Finale");
        idClienteEsistenteField = new JTextField();
        numeroTelaioField = new JTextField();
        venditaButton = new JButton("Inserisci vendita");
        
        // Placeholder con data odierna
        String dataCorrente = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        dataVenditaField.setText(dataCorrente);
        dataVenditaField.setForeground(Color.GRAY);

        dataVenditaField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dataVenditaField.getText().equals(dataCorrente)) {
                    dataVenditaField.setText("");
                    dataVenditaField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dataVenditaField.getText().isBlank()) {
                    dataVenditaField.setText(dataCorrente);
                    dataVenditaField.setForeground(Color.GRAY);
                }
            }
        });
        
        pannelloVendita.add(new JLabel("Data Vendita (YYYY-MM-DD):"), cc.xy(1,1));
        pannelloVendita.add(dataVenditaField, cc.xy(3,1));
        
        pannelloVendita.add(new JLabel("ID Cliente Esistente (solo cifre):"), cc.xy(1,3));
        pannelloVendita.add(idClienteEsistenteField, cc.xy(3,3));
        
        pannelloVendita.add(new JLabel("Numero Telaio (solo cifre):"), cc.xy(1,5));
        pannelloVendita.add(numeroTelaioField, cc.xy(3,5));
        
        pannelloVendita.add(calcolaPrezzoFinaleButton, cc.xy(3,7));
        pannelloVendita.add(new JLabel("Visualizza prezzo finale:"), cc.xy(1,9));
        pannelloVendita.add(prezzoFinaleLabel, cc.xy(3,9));
        
        pannelloVendita.add(venditaButton, cc.xy(3,11));
        
        calcolaPrezzoFinaleButton.addActionListener(e -> {//Esegui calcolo prezzo finale
           try {
			prezzoFinaleLabel.setText("€ " + Float.toString(ProcessQueryGestioneVendite.calcolaPrezzoFinale(getNumeroTelaio(), getIdCliente())));
           } 
           catch (InvalidInputException ex) {
        	   ex.showErrorDialogPanel(this);
           } 
        });
        
        venditaButton.addActionListener(e -> {//Aggiungi vendita
            try {
            	ProcessQueryGestioneVendite.eseguiQueryVendita(getDataVendita(), getNumeroTelaio(), getIdCliente());
            	JOptionPane.showMessageDialog(this, "Vendita inserita con successo.");
    	        clear();
            }
            catch(InvalidInputException ex) {
            	ex.showErrorDialogPanel(this);
            }
        });
    }
    
    private void creaPannelloRestituzione() {
        pannelloRestituzione = new JPanel();
        
        FormLayout layout = new FormLayout(
            "right:pref, 4dlu, 150dlu",
            "pref, 4dlu, pref, 10dlu, pref"
        );
        pannelloRestituzione.setLayout(layout);
        CellConstraints cc = new CellConstraints();
        
        dataRestituzioneField = new JFormattedTextField();
        idVenditaRestField = new JTextField();
        restituzioneButton = new JButton("Restituisci veicolo");
        Dimension buttonSize = new Dimension(220, 25);
	    restituzioneButton.setPreferredSize(buttonSize);
        
        pannelloRestituzione.add(new JLabel("Data Restituzione (YYYY-MM-DD):"), cc.xy(1,1));
        pannelloRestituzione.add(dataRestituzioneField, cc.xy(3,1));
        
        pannelloRestituzione.add(new JLabel("ID Vendita (solo cifre):"), cc.xy(1,3));
        pannelloRestituzione.add(idVenditaRestField, cc.xy(3,3));
        
        pannelloRestituzione.add(restituzioneButton, cc.xy(3,5));
        
        restituzioneButton.addActionListener(e -> {
            try {
            	ProcessQueryGestioneVendite.eseguiQueryRestituzione(getDataRest(), getIdVenditaRest());
            	JOptionPane.showMessageDialog(this, "Restituzione effettuata con successo.");
    	        clear();
            }
            catch(InvalidInputException ex) {
            	ex.showErrorDialogPanel(this);
            }
        });
    }
    
    private Date getDataVendita() throws InvalidInputException {
	    try {
	        LocalDate data = LocalDate.parse(dataVenditaField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
	        return Date.valueOf(data);
	    } catch (DateTimeParseException e) {
	        throw new InvalidInputException("Formato data vendita non valido. Usa YYYY-MM-DD.");
	    }
	}
	
	private Date getDataRest() throws InvalidInputException {
	    try {
	        LocalDate data = LocalDate.parse(dataRestituzioneField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
	        return Date.valueOf(data);
	    } catch (DateTimeParseException e) {
	        throw new InvalidInputException("Formato data restituzione non valido. Usa YYYY-MM-DD.");
	    }
	}

	private String getIdVenditaRest() { return idVenditaRestField.getText(); }
	private String getIdCliente() { return idClienteEsistenteField.getText(); }
	private String getNumeroTelaio() { return numeroTelaioField.getText(); }
    
	private ActionListener backPressed(){//Ritorna a MostraFunzioni
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}
	
	public void clear() {//Ripulisci diversi field
    	dataVenditaField.setText(null);
    	prezzoFinaleLabel.setText(null);
    	idClienteEsistenteField.setText(null);
    	numeroTelaioField.setText(null);
	    dataRestituzioneField.setText(null);
    	idVenditaRestField.setText(null);
    }
}
package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import Principale.*;
import controller.ProcessQueryGestioneVeicoli;
import exceptions.InvalidInputException;

public class GestisciVeicoli extends JPanel{
	
	private JTabbedPane tabbedPane;
	
    //Campi add
    private JPanel pannelloAdd;
	private JTextField numeroTelaioAdd;
    private JTextField marca;
    private JTextField modello;
    private JTextField colore;
    private JTextField prezzo;
    private JTextField tipoField;
    private JTextField numeroPorte;
    private JTextField tipologiaCambio;
    private JTextField numeroAirbag;
    private JTextField altezzaSeggiolino;
    private JTextField idMagazzino;
    private JButton addButton;
    private JRadioButton autoRadio;
    private JRadioButton motoRadio;
    private ButtonGroup tipoGruppo;
    private JRadioButton automaticoRadio;
    private JRadioButton manualeRadio;
    private ButtonGroup tipologiaCambioGruppo;
    
    
    //Campi remove
    private JPanel pannelloRemove;
    private JTextField numeroTelaioRemove;
    private JButton removeButton;
    
    private JButton backButton;
    
    private MainProcess mainProcess;
    
    public GestisciVeicoli(MainProcess main) {
    	
    	
    	this.mainProcess = main;
    	
    	tabbedPane = new JTabbedPane();
        
        backButton = new JButton("Back");
        
        //Diviso in diverse funzioni per tenere ordinato
        creaPannelloAdd();
        creaPannelloRemove();
        
        tabbedPane.addTab("Aggiungi Veicolo", pannelloAdd);
        tabbedPane.addTab("Rimuovi Veicolo", pannelloRemove);
        
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        addButton.addActionListener(e -> {//Aggiungi veicolo
        	try {
				ProcessQueryGestioneVeicoli.eseguiQueryNewVeicolo(getNumeroTelaio(), getMarca(), getModello(), getColore(), getPrezzo(), getTipo(), getNumeroPorte(), getTipologiaCambio(), getNumeroAirbag(), getAltezzaSeggiolino(), getIdMagazzino());
				JOptionPane.showMessageDialog(this, "Veicolo inserito con successo.");
    	        clear();
        	} catch (InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
        });
        backButton.addActionListener(backPressed());
    }
    

    private void creaPannelloAdd() {
    	pannelloAdd = new JPanel();
    	FormLayout layout = new FormLayout(
        	    "right:pref, 4dlu, 150dlu",
        	    "pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
        	);
        pannelloAdd.setLayout(layout);
        CellConstraints cc = new CellConstraints();
        
        numeroTelaioAdd = new JTextField();
        marca = new JTextField();
        modello = new JTextField();
        colore = new JTextField();
        prezzo = new JTextField();
        numeroPorte = new JTextField();
        
        tipologiaCambio = new JTextField();
    	tipologiaCambio.setVisible(false); //Campo nascosto
    	automaticoRadio = new JRadioButton("Automatico");
    	manualeRadio = new JRadioButton("Manuale");
    	tipologiaCambioGruppo = new ButtonGroup();
    	tipologiaCambioGruppo.add(automaticoRadio);
    	tipologiaCambioGruppo.add(manualeRadio);
    	
    	automaticoRadio.addActionListener(e -> tipologiaCambio.setText("a"));
    	manualeRadio.addActionListener(e -> tipologiaCambio.setText("m"));
    	
    	//Imposta manuale come predefinito
    	manualeRadio.setSelected(true);
    	tipologiaCambio.setText("m");
    	
        numeroAirbag = new JTextField();
        altezzaSeggiolino = new JTextField();
        idMagazzino = new JTextField();
        addButton = new JButton("Aggiungi veicolo");
        Dimension buttonSize = new Dimension(220, 25);
	    addButton.setPreferredSize(buttonSize);
        
        tipoField = new JTextField(); //Nascosto, contiene solo informazioni
        
        autoRadio = new JRadioButton("Auto");
        motoRadio = new JRadioButton("Moto");
        tipoGruppo = new ButtonGroup();
        tipoGruppo.add(autoRadio);
        tipoGruppo.add(motoRadio);
        
        //Iposta auto come predefinito
        autoRadio.setSelected(true);
        aggiornaCampiTipo("a");
        
        //Listeners per cambiare visibilità campi
        autoRadio.addActionListener(e -> aggiornaCampiTipo("a"));
        motoRadio.addActionListener(e -> aggiornaCampiTipo("m"));
        
        pannelloAdd.add(new JLabel("Tipo veicolo:"), cc.xy(1, 1));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        radioPanel.add(autoRadio);
        radioPanel.add(motoRadio);
        pannelloAdd.add(radioPanel, cc.xy(3, 1));
        
        pannelloAdd.add(new JLabel("Numero Telaio (solo cifre):"), cc.xy(1, 3));
        pannelloAdd.add(numeroTelaioAdd, cc.xy(3, 3));

        pannelloAdd.add(new JLabel("Marca (solo caratteri):"), cc.xy(1, 5));
        pannelloAdd.add(marca, cc.xy(3, 5));

        pannelloAdd.add(new JLabel("Modello (solo caratteri):"), cc.xy(1, 7));
        pannelloAdd.add(modello, cc.xy(3, 7));

        pannelloAdd.add(new JLabel("Colore (solo caratteri):"), cc.xy(1, 9));
        pannelloAdd.add(colore, cc.xy(3, 9));

        pannelloAdd.add(new JLabel("Prezzo (solo cifre):"), cc.xy(1, 11));
        pannelloAdd.add(prezzo, cc.xy(3, 11));

        pannelloAdd.add(new JLabel("Numero Porte (solo cifre):"), cc.xy(1, 15));
        pannelloAdd.add(numeroPorte, cc.xy(3, 15));

        pannelloAdd.add(new JLabel("Cambio:"), cc.xy(1, 17));
        JPanel radioCambioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        radioCambioPanel.add(automaticoRadio);
        radioCambioPanel.add(manualeRadio);
        pannelloAdd.add(radioCambioPanel, cc.xy(3, 17));

        pannelloAdd.add(new JLabel("Numero Airbag (solo cifre):"), cc.xy(1, 19));
        pannelloAdd.add(numeroAirbag, cc.xy(3, 19));

        pannelloAdd.add(new JLabel("Altezza Seggiolino (solo cifre):"), cc.xy(1, 21));
        pannelloAdd.add(altezzaSeggiolino, cc.xy(3, 21));

        pannelloAdd.add(new JLabel("Id Magazzino (solo cifre):"), cc.xy(1, 23));
        pannelloAdd.add(idMagazzino, cc.xy(3, 23));
        
        pannelloAdd.add(addButton, cc.xy(3, 25));

        tipoField.setVisible(false);
        pannelloAdd.add(tipoField, cc.xy(1, 1));
    }
    
    private void creaPannelloRemove() {
    	pannelloRemove = new JPanel(new FormLayout(
            "right:pref, 4dlu, 150dlu", 
            "50dlu, pref, 4dlu, pref"
        ));
    	
    	CellConstraints cc = new CellConstraints();

        numeroTelaioRemove = new JTextField();
        removeButton = new JButton("Rimuovi");

        pannelloRemove.add(new JLabel("Numero Telaio da rimuovere (solo cifre):"), cc.xy(1,2));
        pannelloRemove.add(numeroTelaioRemove, cc.xy(3,2));
        pannelloRemove.add(removeButton, cc.xy(3,4));
        
        removeButton.addActionListener(e -> {
        	try {
				ProcessQueryGestioneVeicoli.eseguiQueryDeleteVeicolo(getNumeroTelaioRemove());
				JOptionPane.showMessageDialog(this, "Veicolo rimosso con successo.");
    	        clear();
				
			} catch (InvalidInputException ex) {
				ex.showErrorDialogPanel(this);
			}
        	
        });
    }
    
    private void aggiornaCampiTipo(String tipo) { //Per settare da soli i campi a 0 in caso sia auto o moto
		tipoField.setText(tipo);
		boolean isAuto = tipo.equals("a");//Vero se è un auto
		
		numeroPorte.setEnabled(isAuto);
        tipologiaCambio.setEnabled(isAuto);
        numeroAirbag.setEnabled(isAuto);
        altezzaSeggiolino.setEnabled(!isAuto);
        
        if (isAuto) {
			altezzaSeggiolino.setText("0");
		} else {
			numeroPorte.setText("0");
            numeroAirbag.setText("0");
		}
	}
    

    //Getters per prendere i dati dai field
    public String getNumeroTelaio() { return numeroTelaioAdd.getText(); }
    public String getMarca() { return marca.getText(); }
    public String getModello() { return modello.getText(); }
    public String getColore() { return colore.getText(); }
    public String getPrezzo() { return prezzo.getText(); }
    public String getTipo() { return tipoField.getText(); }
    public String getNumeroPorte() { return numeroPorte.getText(); }
    public String getTipologiaCambio() { return tipologiaCambio.getText(); }
    public String getNumeroAirbag() { return numeroAirbag.getText(); }
    public String getAltezzaSeggiolino() { return altezzaSeggiolino.getText(); }
    public String getIdMagazzino() { return idMagazzino.getText(); }
    public String getNumeroTelaioRemove() { return numeroTelaioRemove.getText(); }
    
    private ActionListener backPressed(){//Ritorna a MostraFunzioni
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}
	
	public void clear() {//Ripulisci diversi field
		numeroTelaioAdd.setText(null);
    	numeroTelaioRemove.setText(null);
    	marca.setText(null);
    	modello.setText(null);
    	colore.setText(null);
    	prezzo.setText(null);
    	tipoField.setText(null);
    	numeroPorte.setText(null);
    	tipologiaCambio.setText(null);
    	numeroAirbag.setText(null);
    	altezzaSeggiolino.setText(null);
    	idMagazzino.setText(null);
    }

}
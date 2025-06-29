package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import Principale.*;

//radio button per la selezione del tipo di veicolo con conseguente scomparsa dei campi non inerenti 
//(chiaramente il valore dei radio button sarà "a" per le auto e "m" per le moto)
//mette a 0 i valori null a seconda se è "a" o "m" e poi nel dao viene gestito tutto

public class GestisciVeicoli extends JPanel{
	
	private JTextField numeroTelaioAdd;
    private JTextField numeroTelaioRemove;
    private JTextField marca;
    private JTextField modello;
    private JTextField colore;
    private JTextField prezzo;
    private JTextField stato;
    private JTextField tipo;
    private JTextField numeroPorte;
    private JTextField tipologiaCambio;
    private JTextField numeroAirbag;
    private JTextField altezzaSeggiolino;
    private JTextField idMagazzino;

    private JPanel pannelloAdd;
    private JPanel pannelloRemove;
    private JTabbedPane tabbedPane;

    private JRadioButton autoRadio;
    private JRadioButton motoRadio;
    private ButtonGroup tipoGruppo;
    private JButton removeButton;
    private JButton backButton;
    
    private MainProcess mainProcess;
    
    public GestisciVeicoli(MainProcess main) {
    	
    	
    	this.mainProcess = main;
    	
    	tabbedPane = new JTabbedPane();
        
        backButton = new JButton("Back");
        
        creaPannelloAdd();//Da implementare
        creaPannelloRemove();
        
        tabbedPane.addTab("Aggiungi Veicolo", pannelloAdd);
        tabbedPane.addTab("Rimuovi Veicolo", pannelloRemove);
        
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
    }

    private void creaPannelloAdd() {
    	pannelloAdd = new JPanel(new GridLayout(6, 2, 10, 10));
    	
    }
    
    private void creaPannelloRemove() {
    	pannelloRemove = new JPanel(new GridLayout(3, 2, 10, 10));
    	
    	numeroTelaioRemove = new JTextField();
    	removeButton = new JButton("Remove");
    	
    	pannelloRemove.add(new JLabel("inserisci Id veicolo da rimuovere"));
    	pannelloRemove.add(numeroTelaioRemove);
    	pannelloRemove.add(removeButton);
    }
    

    // Getters per DAO o salvataggio
    public String getNumeroTelaio() { return numeroTelaioAdd.getText(); }
    public String getMarca() { return marca.getText(); }
    public String getModello() { return modello.getText(); }
    public String getColore() { return colore.getText(); }
    public String getPrezzo() { return prezzo.getText(); }
    public String getStato() { return stato.getText(); }
    public String getTipo() { return tipo.getText(); }
    public String getNumeroPorte() { return numeroPorte.getText(); }
    public String getTipologiaCambio() { return tipologiaCambio.getText(); }
    public String getNumeroAirbag() { return numeroAirbag.getText(); }
    public String getAltezzaSeggiolino() { return altezzaSeggiolino.getText(); }
    public String getIdMagazzino() { return idMagazzino.getText(); }
    
    private ActionListener backPressed(){
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainProcess.showPanel("Mostra funzioni");
            }
        };
	}

}
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
    
    private MainProcess mainProcess;

    public GestisciVeicoli(MainProcess main) {
    	
    	this.mainProcess = main;
    	
    	//Primo tentativo andato male, riprovare domani senza bestemiare
    	
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

}
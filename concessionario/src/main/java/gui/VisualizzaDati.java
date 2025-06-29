package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import Principale.*;

public class VisualizzaDati extends JPanel{
	
	private boolean isAdmin;

    private ArrayList<String> listaVeicoli;
    private ArrayList<String> listaClienti;
    private ArrayList<String> listaDipendenti; // solo se isAdmin == true
    private ArrayList<String> listaMagazzini;
    private ArrayList<String> listaVendite;
    private ArrayList<String> listaClub;

    private JTabbedPane tabbedPane;

    private JPanel veicoli;
    private JPanel clienti;
    private JPanel vendite;
    private JPanel dipendenti;
    private JPanel magazzini;
    private JPanel club;
    
    private JButton backButton;
    
    private MainProcess mainProcess;
    
    public VisualizzaDati(MainProcess main) {
    	
    	this.mainProcess = main;
    	
    	
    	listaVeicoli = new ArrayList<>();
        listaClienti = new ArrayList<>();
        listaDipendenti = new ArrayList<>();
        listaMagazzini = new ArrayList<>();
        listaVendite = new ArrayList<>();
        listaClub = new ArrayList<>();
        
        tabbedPane = new JTabbedPane();

        veicoli = new JPanel();
        clienti = new JPanel();
        vendite = new JPanel();
        magazzini = new JPanel();
        club = new JPanel();

        tabbedPane.addTab("Veicoli", veicoli);
        tabbedPane.addTab("Clienti", clienti);
        tabbedPane.addTab("Vendite", vendite);
        tabbedPane.addTab("Magazzini", magazzini);
        tabbedPane.addTab("Club", club);

        if (isAdmin) {
            dipendenti = new JPanel();
            tabbedPane.addTab("Dipendenti", dipendenti);
        }
        
        backButton = new JButton("back");

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
        
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

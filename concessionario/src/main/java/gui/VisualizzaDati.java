package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
        JScrollPane scrollPane = new JScrollPane(tabbedPane);
        scrollPane.setPreferredSize(new Dimension(550, 400));
        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
        
        populateData();
        
    }
    
    public void populateData() {//Funzione per popolare i panel con dati pertinenti
    	
    	//Dati test, sostituire con dati DB
        listaVeicoli.add("Fiat Panda");
        listaVeicoli.add("Nissan X");

        listaClienti.add("Mario Verdi");
        listaClienti.add("Luigi Rossi");
        listaClienti.add("Roberto Cappuccio");
        
        listaVendite.add("Nissan X - Mario Verdi 12/03/05");
        
        listaMagazzini.add("Roma");
        
        listaClub.add("Oro");
        listaClub.add("Argento");
        
        veicoli.removeAll(); 
        veicoli.setLayout(new BoxLayout(veicoli, BoxLayout.Y_AXIS));
        for (String v : listaVeicoli) {
            veicoli.add(new JLabel(v));
        }

        clienti.removeAll();
        clienti.setLayout(new BoxLayout(clienti, BoxLayout.Y_AXIS));
        for (String c : listaClienti) {
            clienti.add(new JLabel(c));
        }
        
        vendite.removeAll();
        vendite.setLayout(new BoxLayout(vendite, BoxLayout.Y_AXIS));
        for (String c : listaVendite) {
            vendite.add(new JLabel(c));
        }
        
        magazzini.removeAll();
        magazzini.setLayout(new BoxLayout(magazzini, BoxLayout.Y_AXIS));
        for (String c : listaMagazzini) {
        	magazzini.add(new JLabel(c));
        }
        
        club.removeAll();
        club.setLayout(new BoxLayout(club, BoxLayout.Y_AXIS));
        for (String c : listaClub) {
        	club.add(new JLabel(c));
        }

        revalidate();
        repaint();
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

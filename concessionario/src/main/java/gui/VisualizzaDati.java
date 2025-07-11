package gui;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import Principale.*;
import controller.ProcessQueryMostraDati;

public class VisualizzaDati extends JPanel{

    private ArrayList<String[]> datiVeicoli;
    private ArrayList<String[]> datiClienti;
    private ArrayList<String[]> datiMagazzini;
    private ArrayList<String[]> datiVendite;
    private ArrayList<String[]> datiClub;

    private JTabbedPane tabbedPane;

    private JPanel veicoli;
    private JPanel clienti;
    private JPanel vendite;
    private JPanel magazzini;
    private JPanel club;
    
    private JButton backButton;
    
    private MainProcess mainProcess;
    
    public VisualizzaDati(MainProcess main){
    	
    	this.mainProcess = main;
    	

    	tabbedPane = new JTabbedPane();

    	veicoli = new JPanel();
    	clienti = new JPanel();
    	vendite = new JPanel();
    	magazzini = new JPanel();
    	club = new JPanel();

    	tabbedPane.addTab("Veicoli", creaScrollPanePerPanel(veicoli));
    	tabbedPane.addTab("Clienti", creaScrollPanePerPanel(clienti));
    	tabbedPane.addTab("Vendite", creaScrollPanePerPanel(vendite));
    	tabbedPane.addTab("Magazzini", creaScrollPanePerPanel(magazzini));
    	tabbedPane.addTab("Club", creaScrollPanePerPanel(club));

    	JButton aggiornaButton = new JButton("Aggiorna");
    	aggiornaButton.addActionListener(e -> populateData());

    	JPanel topPanel = new JPanel(new BorderLayout());
    	topPanel.add(aggiornaButton, BorderLayout.EAST);

    	backButton = new JButton("Back");

    	setLayout(new BorderLayout());
    	add(topPanel, BorderLayout.NORTH);
    	add(tabbedPane, BorderLayout.CENTER);
    	add(backButton, BorderLayout.SOUTH);

    	backButton.addActionListener(backPressed());

    	populateData();
        
    }
    
    
    //Funzione helper per semplificare leggibilita codice, crea una porzione nel panel per poter abilitare la barra dell'overflow
    private JScrollPane creaScrollPanePerPanel(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 400));
        return scrollPane;
    }
    
    public void populateData(){
    	
		try {
			datiVeicoli = new ArrayList<>();
			ArrayList<String[]> dataVeic = ProcessQueryMostraDati.eseguiQueryView("veicolo");
			datiVeicoli.add(dataVeic.get(0)); //intestazioni colonne
			datiVeicoli.add(dataVeic.get(1)); //dati effettivi
			
			datiClienti = new ArrayList<>();
			ArrayList<String[]> dataCli = ProcessQueryMostraDati.eseguiQueryView("clienti");
	        datiClienti.add(dataCli.get(0));
	        datiClienti.add(dataCli.get(1));

	        datiClub = new ArrayList<>();
	        ArrayList<String[]> dataClu = ProcessQueryMostraDati.eseguiQueryView("club");
	        datiClub.add(dataClu.get(0));
	        datiClub.add(dataClu.get(1));

	        datiVendite = new ArrayList<>();
	        ArrayList<String[]> dataVen = ProcessQueryMostraDati.eseguiQueryView("vendite");
	        datiVendite.add(dataVen.get(0));
	        datiVendite.add(dataVen.get(1));

	        datiMagazzini = new ArrayList<>();
	        ArrayList<String[]> dataMag = ProcessQueryMostraDati.eseguiQueryView("magazzini");
	        datiMagazzini.add(dataMag.get(0));
	        datiMagazzini.add(dataMag.get(1));
			
		} catch (SQLException e) {
			
        	JOptionPane.showMessageDialog(this, "Errore nella visualizzazione dei dati, riprova.");
        	
		}
        
        
        popolaPanelConDati(veicoli, datiVeicoli);
        popolaPanelConDati(clienti, datiClienti);
        popolaPanelConDati(vendite, datiVendite);
        popolaPanelConDati(magazzini, datiMagazzini);
        popolaPanelConDati(club, datiClub);
        
        revalidate();
        repaint();
    }
    
	/* Funzionamento popolaPanelConDati, se servono chiarimenti poi chiedi a me
	- 'dati' contiene in posizione 0 le intestazioni (titoli colonne) come hai spiegato tu
	- e in posizione 1 tutti i valori concatenati riga per riga
	- crea una struttura verticale (BoxLayout Y_AXIS) di pannelli riga
	- ogni riga è un JPanel con GridLayout per le colonne
	- la prima riga mostra le intestazioni in grassetto con bordo nero
	- le righe successive mostrano i dati con bordo grigio chiaro
	- aggiunge tutto al panel passato alla funzione usando due for loop e aggiorna la UI */
    private void popolaPanelConDati(JPanel panel, ArrayList<String[]> dati) {
        panel.removeAll();

        if (dati == null || dati.size() < 2) return;

        //Separare titoli da contenuto
        String[] intestazioni = dati.get(0);
        String[] valori = dati.get(1);

        int colonne = intestazioni.length;
        int righe = valori.length / colonne;

        // Container verticale che contiene le righe
        JPanel contenitore = new JPanel();
        contenitore.setLayout(new BoxLayout(contenitore, BoxLayout.Y_AXIS));

        // Riga intestazioni (Colore più marcato per differenziarle)
        JPanel headerRow = new JPanel(new GridLayout(1, colonne));
        for (String intestazione : intestazioni) {
            JLabel label = new JLabel(intestazione, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(java.awt.Font.BOLD, 11f));//Rimpicciolisci font
            label.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
            headerRow.add(label);
        }
        contenitore.add(headerRow);

        // Righe dati
        for (int i = 0; i < righe; i++) {
            JPanel dataRow = new JPanel(new GridLayout(1, colonne));
            for (int j = 0; j < colonne; j++) {
                int index = i * colonne + j;
                JLabel cella = new JLabel(valori[index], SwingConstants.CENTER);
                cella.setFont(cella.getFont().deriveFont(11f));//Rimpicciolisci font
                cella.setBorder(BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
                dataRow.add(cella);
            }
            contenitore.add(dataRow);
        }

        panel.setLayout(new BorderLayout());
        panel.add(contenitore, BorderLayout.NORTH);

        panel.revalidate();
        panel.repaint();
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

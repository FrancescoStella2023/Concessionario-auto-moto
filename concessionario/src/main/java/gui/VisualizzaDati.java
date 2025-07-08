package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import Principale.*;

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
    
    public VisualizzaDati(MainProcess main) {
    	
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
        
        backButton = new JButton("back");

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
        
        populateData();
        
    }
    
    
    //Funzione helper per semplificare legibilita codice
    private JScrollPane creaScrollPanePerPanel(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 400));
        return scrollPane;
    }
    
    public void populateData() {
        // Dati esempio, da sostituire con query
    	datiVeicoli = new ArrayList<>();
    	datiVeicoli.add(new String[] {"NumeroTelaio", "Marca", "Modello", "Colore", "Prezzo", "isVenduto", "Tipo", "Numero_Porte", "Tipologia_Cambio", "Numero_Airbag", "Altezza_Seggiolino", "Id_magazzino"});
    	datiVeicoli.add(new String[] {
    	    "ABC123", "Fiat", "Panda", "Rosso", "10000", "false", "Utilitaria", "5", "Manuale", "4", "50", "1",
    	    "XYZ789", "Nissan", "Qashqai", "Blu", "18000", "true", "SUV", "5", "Automatico", "6", "60", "2"
    	});
    	
        datiClienti = new ArrayList<>();
        datiClienti.add(new String[] {"Id_cliente", "Nome", "Cognome", "Email", "Numero_di_telefono", "Comune", "Num_civico", "Indirizzo", "Data_iscrizione", "Data_scadenza", "Id_club"});
        datiClienti.add(new String[] {
            "1", "Mario", "Rossi", "mario.rossi@email.it", "3331234567", "Roma", "12", "Via Roma 12", "2023-01-01", "2024-01-01", "1",
            "2", "Luca", "Bianchi", "luca.bianchi@email.it", "3459876543", "Milano", "7", "Corso Milano 7", "2022-06-15", "2023-06-15", "2"
        });

        datiClub = new ArrayList<>();
        datiClub.add(new String[] {"Id_club", "Nome_club", "Percentuale_di_sconto"});
        datiClub.add(new String[] {
            "1", "Oro", "30%",
            "2", "Argento", "10%"
        });

        datiVendite = new ArrayList<>();
        datiVendite.add(new String[] {"Id_vendita", "Data_vendita", "Data_restituzione", "Prezzo_Finale", "Numero_telaio", "Id_dipendente", "Id_cliente"});
        datiVendite.add(new String[] {
            "10", "2023-03-01", "2023-03-10", "12000", "ABC123", "3", "1",
            "11", "2023-04-05", "2023-04-15", "15000", "XYZ789", "2", "2"
        });

        datiMagazzini = new ArrayList<>();
        datiMagazzini.add(new String[] {"Id_magazzino", "Indirizzo"});
        datiMagazzini.add(new String[] {
            "1", "Via Roma 10",
            "2", "Corso Milano 15",
            "3", "Viale Magellano 14",
            "4", "Corso Malta 3"
        });
        
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

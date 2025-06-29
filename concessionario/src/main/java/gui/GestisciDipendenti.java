package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import Principale.*;

//------------Temporaneo, da riorganizzare/riscrivere

public class GestisciDipendenti extends JPanel{
	
	private JTextField idDipendenteNewPassField;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField passwordField;
    private JTextField isAdminField;
    
    private JButton backButton;

    private ArrayList<String> listaDipendenti;

    private JPanel pannelloAdd;
    private JPanel pannelloNewPass;
    private JPanel pannelloViewDipendenti;

    private JTabbedPane tabbedPane;
    
    MainProcess mainProcess;
    
    public GestisciDipendenti(MainProcess main) {
    	
    	this.mainProcess = main;
    	
        listaDipendenti = new ArrayList<>();
        tabbedPane = new JTabbedPane();
        
        backButton = new JButton("Back");

        creaPannelloAggiunta();
        creaPannelloCambiaPassword();
        creaPannelloVisualizzazione();

        tabbedPane.addTab("Aggiungi Dipendente", pannelloAdd);
        tabbedPane.addTab("Cambia Password", pannelloNewPass);
        tabbedPane.addTab("Visualizza Dipendenti", pannelloViewDipendenti);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(backPressed());
    }
    
    private void creaPannelloAggiunta() {
        pannelloAdd = new JPanel(new GridLayout(6, 2, 10, 10));

        nomeField = new JTextField();
        cognomeField = new JTextField();
        passwordField = new JTextField();
        isAdminField = new JTextField();

        pannelloAdd.add(new JLabel("Nome:"));
        pannelloAdd.add(nomeField);
        pannelloAdd.add(new JLabel("Cognome:"));
        pannelloAdd.add(cognomeField);
        pannelloAdd.add(new JLabel("Password:"));
        pannelloAdd.add(passwordField);
        pannelloAdd.add(new JLabel("È Admin (true/false):"));
        pannelloAdd.add(isAdminField);

        JButton aggiungiButton = new JButton("Aggiungi");
        aggiungiButton.addActionListener(e -> {
            String entry = nomeField.getText() + " " + cognomeField.getText();
            listaDipendenti.add(entry);
            JOptionPane.showMessageDialog(this, "Dipendente aggiunto.");
            aggiornaVisualizzazione();
        });

        pannelloAdd.add(new JLabel());
        pannelloAdd.add(aggiungiButton);
    }

    private void creaPannelloCambiaPassword() {
        pannelloNewPass = new JPanel(new GridLayout(3, 2, 10, 10));

        idDipendenteNewPassField = new JTextField();
        JTextField newPasswordField = new JTextField();

        pannelloNewPass.add(new JLabel("ID Dipendente:"));
        pannelloNewPass.add(idDipendenteNewPassField);
        pannelloNewPass.add(new JLabel("Nuova Password:"));
        pannelloNewPass.add(newPasswordField);

        JButton cambiaPassButton = new JButton("Cambia Password");
        cambiaPassButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Password aggiornata (simulato).");
        });

        pannelloNewPass.add(new JLabel());
        pannelloNewPass.add(cambiaPassButton);
    }

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

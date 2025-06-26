package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class GestisciClienti extends JPanel {
	
	
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JTextField numeroTelefonoField;
    private JTextField comuneField;
    private JTextField numCivicoField;
    private JTextField indirizzoField;
    private JTextField idClubField;
	private JButton enterButton;
	
	public GestisciClienti() {
		
		FormLayout layout = new FormLayout(
            "right:pref, 4dlu, 150dlu",
            "pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
        );
        setLayout(layout);
        CellConstraints cc = new CellConstraints();

        nomeField = new JTextField();
        cognomeField = new JTextField();
        emailField = new JTextField();
        numeroTelefonoField = new JTextField();
        comuneField = new JTextField();
        numCivicoField = new JTextField();
        indirizzoField = new JTextField();
        idClubField = new JTextField();
        
        enterButton = new JButton();

        add(new JLabel("Nome:"), cc.xy(1, 1));
        add(nomeField, cc.xy(3, 1));

        add(new JLabel("Cognome:"), cc.xy(1, 3));
        add(cognomeField, cc.xy(3, 3));

        add(new JLabel("Email:"), cc.xy(1, 5));
        add(emailField, cc.xy(3, 5));

        add(new JLabel("Telefono:"), cc.xy(1, 7));
        add(numeroTelefonoField, cc.xy(3, 7));

        add(new JLabel("Comune:"), cc.xy(1, 9));
        add(comuneField, cc.xy(3, 9));

        add(new JLabel("Num. Civico:"), cc.xy(1, 11));
        add(numCivicoField, cc.xy(3, 11));

        add(new JLabel("Indirizzo:"), cc.xy(1, 13));
        add(indirizzoField, cc.xy(3, 13));

        add(new JLabel("ID Club:"), cc.xy(1, 15));
        add(idClubField, cc.xy(3, 15));
		
		
		enterButton.addActionListener(Test());
	}
	
	public void showWindow() {
		JFrame frame = new JFrame("Gestisci magazzini");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
	    wrapper.add(this);
	    
		frame.setContentPane(wrapper);
		frame.setSize(450, 350);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public String getNome() {
		return nomeField.getText();
	}
	
	public String getCognome() {
        return cognomeField.getText();
    }
	
	public String getEmail() {
        return emailField.getText();
    }
    
    public String getNumeroTelefono() {
        return numeroTelefonoField.getText();
    }

    public String getComune() {
        return comuneField.getText();
    }

    public String getNumCivico() {
        return numCivicoField.getText();
    }

    public String getIndirizzo() {
        return indirizzoField.getText();
    }

    public String getIdClub() {
        return idClubField.getText();
    }
	
	private ActionListener Test(){
		return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                System.out.println("nome - " + getNome());
                System.out.println("cogome - " + getCognome());
                System.out.println("email - " + getEmail());
                System.out.println("numero telefono - " + getNumeroTelefono());
                System.out.println("comun - " + getComune());
                System.out.println("numero civico - " + getNumCivico());
                System.out.println("indirizzo - " + getIndirizzo());
                System.out.println("club id - " + getIdClub());
            }
        };
	}
	
}


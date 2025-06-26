package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import Principale.*;

public class MostraFunzioni extends JPanel {
		
	private JButton gestisciClientiButton;
	private JButton gestisciClubButton;
	private JButton gestisciDipendentiButton;
	private JButton gestisciMagazzinoButton;
	private JButton gestisciVeicoliButton;
	private JButton gestisciVenditeButton;
	private JButton visualizzaDatiButton;
	
	private MainProcess mainProcess;
		
	public MostraFunzioni (MainProcess main) {
		
		FormLayout layout = new FormLayout(
			"center:pref",
			"pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
		);
			
		setLayout(layout);
		CellConstraints cc = new CellConstraints();
		
		add(gestisciClientiButton, cc.xy(1, 1));
		add(gestisciClubButton, cc.xy(1, 3));
		add(gestisciDipendentiButton, cc.xy(1, 5));
		add(gestisciMagazzinoButton, cc.xy(1, 7));
		add(gestisciVeicoliButton, cc.xy(1, 9));
		add(gestisciVenditeButton, cc.xy(1, 11));
		add(visualizzaDatiButton, cc.xy(1, 13));
		
		gestisciClientiButton.addActionListener(Test(0));
		gestisciClubButton.addActionListener(Test(1));
		gestisciDipendentiButton.addActionListener(Test(2));
		gestisciMagazzinoButton.addActionListener(Test(3));
		gestisciVeicoliButton.addActionListener(Test(4));
		gestisciVenditeButton.addActionListener(Test(5));
		visualizzaDatiButton.addActionListener(Test(6));
	}
	
		
	public void showWindow() {
		JFrame frame = new JFrame("Funzioni");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
	    wrapper.add(this);
	    
		frame.setContentPane(wrapper);
		frame.setSize(450, 350);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	private ActionListener Test(int numScheda) {
		 return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
				System.out.println(numScheda);
				
				/*Si potrebbe usare un semplice switch per aprire la sheda corretta 
				e fare un check con privilegio admin per vedere se si hanno 
				gli accessi per aprire la scheda e dare erroe in caso contrario
				*/
            }
        };
	}
}
	
	

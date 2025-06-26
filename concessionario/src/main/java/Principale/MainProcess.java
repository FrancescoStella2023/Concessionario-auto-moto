package Principale;

import gui.*;
import javax.swing.*;
import java.awt.*;

public class MainProcess {
	
	private JFrame frame;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	
	public MainProcess() {
		
		frame = new JFrame("Gestore concessionario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,350);
		frame.setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		LoginUtente loginPanel = new LoginUtente(this);
		MostraFunzioni funzioniPanel = new MostraFunzioni(this);
		
		cardPanel.add(loginPanel, "Login");
		cardPanel.add(funzioniPanel, "Elenco funzioni");
		
		frame.setContentPane(cardPanel);
		frame.setVisible(true);
		
	}
	
	public void showPanel(String name) {
		cardLayout.show(cardPanel, name);
	}
	
   
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(() -> new MainProcess());
		
		//LoginUtente login = new LoginUtente();
		//login.showWindow();
		
	}
	
}

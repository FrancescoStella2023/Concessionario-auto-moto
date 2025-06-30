/////
///
///
///	!!NON INSERIRE IL CAMPO ID DIPENDENTE VIENE PRESO DAL DIPENDENTE DELLA SESSIONE IN CORSO
///
///


package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import Principale.*;

public class GestisciVendite extends JPanel{
	
	
	private JButton backButton;
	
	MainProcess mainProcess;
	
	public GestisciVendite(MainProcess main) {
		
		backButton = new JButton("Back");
		
		setLayout(new BorderLayout());
		add(backButton, BorderLayout.SOUTH);
      
        backButton.addActionListener(backPressed());
		
		this.mainProcess = main;
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

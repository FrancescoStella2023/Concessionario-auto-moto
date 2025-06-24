package gui;

import com.jgoodies.forms.layout.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class LoginUtente extends JPanel {
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	
	public LoginUtente () {
		
		
		FormLayout layout = new FormLayout(
			"right:pref, 4dlu, 100dlu",
			"pref, 4dlu, pref, 4dlu, pref"
		);
		setLayout(layout);
		
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("Login");
		
		CellConstraints cc = new CellConstraints();
		
		add(new JLabel("Username:"), cc.xy(1,1));
		add(usernameField, cc.xy(3,1));
		
		add(new JLabel("Password:"), cc.xy(1,3));
		add(passwordField, cc.xy(3,3));
		
		add(loginButton, cc.xy(3, 5));
		
		loginButton.addActionListener(Test());
	}
	
	
	public void showWindow() {
		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
	    wrapper.add(this);
	    
		frame.setContentPane(wrapper);
		frame.setSize(350, 250);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	
	public String getPassword() {
		return new String(passwordField.getPassword());
	}
	
	
	private ActionListener Test() {
		 return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                System.out.println("Username - " + getUsername());
                System.out.println("Password - " + getPassword());
            }
        };
	}
}

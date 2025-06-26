package exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidCredentialsException extends Throwable{
	//usato solo per l'accesso
	String msg;
	
	public InvalidCredentialsException(String msg) {
		this.msg = msg;
	}
	
	public void showErrorDialogPanel(JPanel panel) {
		JOptionPane.showMessageDialog(panel, msg); //mostra il dialog con il rispettivo messaggio di errore
	}
}

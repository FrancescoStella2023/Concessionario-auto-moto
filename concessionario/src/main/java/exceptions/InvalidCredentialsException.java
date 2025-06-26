package exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidCredentialsException extends Throwable{
	public void showErrorDialogPanel(JPanel panel) {
		JOptionPane.showMessageDialog(panel, "Id dipendente o password errati, riprovare.");
	}
}

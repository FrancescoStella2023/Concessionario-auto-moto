package exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidInputException extends Throwable{
	private String msg;
	public InvalidInputException(String msg){
		this.msg = msg;
	}
	public void showErrorDialogPanel(JPanel panel) {
		JOptionPane.showMessageDialog(panel, msg);
	}
}

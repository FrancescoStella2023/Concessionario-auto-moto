package exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvalidInputException extends Throwable{
	private String msg;
	public InvalidInputException(String msg){
		this.msg = msg;
	}
	void showErrorDialogPanel(JPanel panel) {
		JOptionPane.showMessageDialog(panel, msg);
	}
}

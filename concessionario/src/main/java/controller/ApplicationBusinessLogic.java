package controller;

public class ApplicationBusinessLogic {
	public static boolean isAllString(String stringa) {
		if (stringa == null || stringa.isEmpty()) return false; //se è vuota ritorna false
	    for (char c : stringa.toCharArray()) { //per ogni char nella stringa convertita in vettori di char
	        if (!Character.isLetter(c)) return false; //se non è una lettera torna false
	    }
	    return true;
	}
	public static boolean isAllInt(String stringa) {
		if (stringa == null || stringa.isEmpty()) return false; 
	    for (char c : stringa.toCharArray()) {
	        if (!Character.isDigit(c)) return false; //se non è un numero torna false
	    }
	    return true;
	}
}

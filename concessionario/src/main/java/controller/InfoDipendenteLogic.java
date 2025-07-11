package controller;

public class InfoDipendenteLogic {
	//serve per salvare i dati del dipendente della sessione
	private static boolean isAdmin;
	private static int idDipendente;
	
	public static void setPrivilegeAndIdDipendente(int idDip, boolean isAdm) {
		idDipendente = idDip;
		isAdmin = isAdm;
	}

	public static int getIdDipendente() {
		return idDipendente;
	}

	public static boolean getPrivilege() {
		
		return isAdmin;
	}
	
}

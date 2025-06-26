package controller;

import java.sql.SQLException;

import dbdao.DAOConnDB;
import exceptions.InvalidCredentialsException;
public class GestisciLoginAndConnection {
	
	private static DAOConnDB connDB = new DAOConnDB();
	
	public static void startLogin(String idDipendente, String password) throws InvalidCredentialsException, SQLException{
		if(!ApplicationBusinessLogic.isAllInt(idDipendente)) throw new InvalidCredentialsException("Id dipendente errato."); //vede se è tutto int
		
		int idDipendenteInt = Integer.parseInt(idDipendente); 
		if(!connDB.verificaAccesso(idDipendenteInt, password)) throw new InvalidCredentialsException("Credenziali errate.");
		InfoDipendenteLogic.setPrivilegeAndIdDipendente(idDipendenteInt, connDB.verificaAdmin(idDipendenteInt)); //imposta l'id dipendente della sessione e i suoi privilegi
	}
}

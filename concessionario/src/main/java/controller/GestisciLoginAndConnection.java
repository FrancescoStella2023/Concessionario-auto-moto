package controller;

import java.sql.SQLException;

import dbdao.DAOConnDB;
import exceptions.InvalidCredentialsException;
import exceptions.InvalidInputException;
public class GestisciLoginAndConnection {
	
	private static DAOConnDB connDB = new DAOConnDB();
	
	public static void startLogin(String idDipendente, String password) throws InvalidCredentialsException{
		if(!ApplicationBusinessLogic.isAllInt(idDipendente)) throw new InvalidCredentialsException("Id dipendente errato."); //vede se è tutto int
		
		int idDipendenteInt = Integer.parseInt(idDipendente); 
		try{
			if(!connDB.verificaAccesso(idDipendenteInt, password)) throw new InvalidCredentialsException("Credenziali errate.");
			InfoDipendenteLogic.setPrivilegeAndIdDipendente(idDipendenteInt, connDB.verificaAdmin(idDipendenteInt)); //imposta l'id dipendente della sessione e i suoi privilegi

		}
		catch(SQLException ex) {
			ex.printStackTrace();
			throw new InvalidCredentialsException("Errore nel database.");
		}
	}
	
	public static void startConnection(String username, String password) throws SQLException{
		connDB.startConn("jdbc:postgresql://localhost:5432/Concessionario", username, password);
	}
}

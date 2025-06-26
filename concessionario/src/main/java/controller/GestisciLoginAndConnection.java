package controller;

import dbdao.DAOConnDB;
import exceptions.InvalidCredentialsException;
public class GestisciLoginAndConnection {
	
	DAOConnDB connDB = new DAOConnDB();
	
	public static void startLogin(String idDipendente, String password) throws InvalidCredentialsException{
		if(!ApplicationBusinessLogic.isAllString(idDipendente)) throw new InvalidCredentialsException();
		
		if(verificaAccesso())
	}
}

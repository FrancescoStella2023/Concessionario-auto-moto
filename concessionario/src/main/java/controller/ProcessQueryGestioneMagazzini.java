package controller;

import java.sql.SQLException;

import dao.DAOGestioneMagazzini;
import exceptions.InvalidInputException;
import model.EntitaMagazzino;

public class ProcessQueryGestioneMagazzini {
	public static void eseguiQueryMagazzino(String indirizzo) throws InvalidInputException{

		DAOGestioneMagazzini daoMag = new DAOGestioneMagazzini();
		
		try {
				if(!indirizzo.equals("") && !ApplicationBusinessLogic.isAllInt(indirizzo)){
					
					EntitaMagazzino magazzino = new EntitaMagazzino(indirizzo);
					daoMag.aggiungiMagazzino(magazzino);
				}
				else {
					throw new InvalidInputException("Campi vuoti o non validi, riprova");
				}
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			
			switch(ex.getSQLState()) {
				case "22001":
					throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
				default:
					throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
	}
}


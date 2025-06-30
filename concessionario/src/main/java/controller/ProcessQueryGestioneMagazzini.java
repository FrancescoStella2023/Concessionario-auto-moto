package controller;

import java.sql.SQLException;

import dao.DAOGestioneMagazzini;
import exceptions.InvalidInputException;
import model.EntitaMagazzino;

public class ProcessQueryGestioneMagazzini {
	public static void eseguiQueryMagazzino(String indirizzo) throws InvalidInputException{

		DAOGestioneMagazzini daoMag = new DAOGestioneMagazzini();
		
		try {
				EntitaMagazzino magazzino = new EntitaMagazzino(indirizzo);
				daoMag.aggiungiMagazzino(magazzino);
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


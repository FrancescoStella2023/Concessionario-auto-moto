package controller;

import java.sql.SQLException;

import dao.DAOGestioneClub;
import exceptions.InvalidInputException;
import model.EntitaClub;

public class ProcessQueryGestioneClub {
	public static void eseguiQueryClub(String nomeClub, String percentualeDiSconto) throws InvalidInputException{

		DAOGestioneClub daoClub = new DAOGestioneClub();
		
		try {
				if(ApplicationBusinessLogic.isAllInt(percentualeDiSconto) && ApplicationBusinessLogic.isAllString(nomeClub)) { //verifica se i dati sono validi
					//in caso positivo processa i dati
					int percScontoInt = Integer.parseInt(percentualeDiSconto);
					EntitaClub club = new EntitaClub(nomeClub, percScontoInt);
					daoClub.aggiungiClub(club);
				}
				else throw new InvalidInputException("Campi vuoti o non validi, riprova., riprova."); //altrimenti lancia l'eccezione
				
		}
		catch(SQLException ex) { //gestisce gli errori lanciati dal db
			
			switch(ex.getSQLState()) {
				case "23503":
					throw new InvalidInputException("Percentuale di sconto non compresa tra 1 e 50, riprova.");
				case "23514":
					throw new InvalidInputException("Percentuale di sconto non compresa tra 1 e 50, riprova.");
				case "22001":
					throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
				default:
					System.out.println(ex.getSQLState());
					throw new InvalidInputException("Errore del sistema, riprova.");
					

			}
		}
	}
}

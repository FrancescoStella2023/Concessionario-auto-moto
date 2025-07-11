package controller;

import java.sql.SQLException;

import dao.DAOGestioneClienti;
import exceptions.InvalidInputException;
import model.EntitaCliente;

public class ProcessQueryGestioneClienti {
	public static void eseguiQueryNewCliente(String nomeC, String cognomeC, String emailC, String numeroTelefonoC, String comuneC, String numCivicoC, String indirizzoC, String idClubC) throws InvalidInputException{
		DAOGestioneClienti daoCli = new DAOGestioneClienti();
		
		
		try {
			
			if(ApplicationBusinessLogic.isAllInt(numCivicoC) && ApplicationBusinessLogic.isAllString(nomeC) && ApplicationBusinessLogic.isAllString(cognomeC) && ApplicationBusinessLogic.isAllInt(numeroTelefonoC) && ApplicationBusinessLogic.isAllInt(numCivicoC) && ApplicationBusinessLogic.isAllString(indirizzoC) && ApplicationBusinessLogic.isAllInt(idClubC)) { //verifica se i dati inseriti sono validi
				//in caso positivo processa i dati
				
				
				
				int numCivicoInt = Integer.parseInt(numCivicoC);
				int idClubInt = Integer.parseInt(idClubC);
				
				if(!daoCli.checkClubCliente(idClubInt) && idClubInt != 0) throw new InvalidInputException("Club inesistente, riprova.");
				
				EntitaCliente cliente = new EntitaCliente(nomeC, cognomeC, emailC, numeroTelefonoC, comuneC, numCivicoInt, indirizzoC, idClubInt);
				
				daoCli.aggiungiCliente(cliente);
				
				
			}
			else throw new InvalidInputException("Campi vuoti o non validi, riprova."); //altrimenti lancia l'eccezione
		}
		catch(SQLException ex) { //gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
			case "23503":
				throw new InvalidInputException("Numero club inesistente, riprova.");
			case "22001":
				throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
			case "23505":
				throw new InvalidInputException("Numero di telefono o email già registrati, riprova.");
			default:
				ex.printStackTrace();
				throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
	}
}

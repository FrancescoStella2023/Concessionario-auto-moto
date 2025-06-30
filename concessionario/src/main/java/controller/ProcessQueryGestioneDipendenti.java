package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOGestioneDipendenti;
import exceptions.InvalidInputException;
import model.EntitaDipendente;

public class ProcessQueryGestioneDipendenti {
	
	public static void eseguiQueryNewDipendente(String nome, String cognome, String password, boolean isAdmin) throws InvalidInputException{
		
		DAOGestioneDipendenti daoDip = new DAOGestioneDipendenti();
		try {
			if(ApplicationBusinessLogic.isAllString(nome) && ApplicationBusinessLogic.isAllString(cognome)) {//verifica se i dati sono validi
				
				EntitaDipendente dip = new EntitaDipendente(nome, cognome, password, isAdmin);
				
				daoDip.aggiungiDipendente(dip);
			}
			else throw new InvalidInputException("Dati inseriti non validi, riprovare.");//altrimenti lancia l'eccezione
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
				case "23514":
					throw new InvalidInputException("Password minore di 10 caratteri, riprova.");
				case "22001":
					throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
				default:
					throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
	}
	
	public static void eseguiQueryCambiaPassword(String idDipendente, String password) throws InvalidInputException{
		
		DAOGestioneDipendenti daoDip = new DAOGestioneDipendenti();
		
		try {
			if(ApplicationBusinessLogic.isAllInt(idDipendente)) {//verifica se i dati sono validi
				//in caso positivo processa i dati
				int idDipInt = Integer.parseInt(idDipendente);
				if(!daoDip.cambiaPassword(idDipInt, password)) throw new InvalidInputException("Dipendente inesistente"); //in caso non venga modificata nessuna tupla, viene comunicato all'utente
			}
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
				case "23514":
					throw new InvalidInputException("Password minore di 10 caratteri, riprova.");
				case "22001":
					throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
				default:
					throw new InvalidInputException("Errore del sistema, riprova.");
			}
				
		}
	}
	
	
}

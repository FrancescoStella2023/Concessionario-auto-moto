package controller;

import java.sql.SQLException;

import dao.DAOGestioneVeicoli;
import exceptions.InvalidInputException;
import model.EntitaVeicolo;

public class ProcessQueryGestioneVeicoli {
	public static void eseguiQueryNewVeicolo(String numeroTelaio, String marca, String modello, String colore, String prezzo, String tipo, String numeroPorte, String tipologiaCambio, String numeroAirbag, String altezzaSeggiolino, String idMagazzino) throws InvalidInputException{
		DAOGestioneVeicoli daoVei = new DAOGestioneVeicoli();
		
		try {
			//verifica se i dati sono validi
			if(ApplicationBusinessLogic.isAllInt(numeroTelaio) && ApplicationBusinessLogic.isAllString(marca) && ApplicationBusinessLogic.isAllString(modello) && ApplicationBusinessLogic.isAllFloat(prezzo) && ApplicationBusinessLogic.isAllInt(numeroPorte) && ApplicationBusinessLogic.isAllInt(numeroAirbag) && ApplicationBusinessLogic.isAllFloat(altezzaSeggiolino) && ApplicationBusinessLogic.isAllInt(idMagazzino)) {
				//in caso positivo processa i dati
				int numTelaioInt = Integer.parseInt(numeroTelaio);
				float prezzoFloat = Float.parseFloat(prezzo);
				int numPorteInt = Integer.parseInt(numeroPorte);
				int numAirbagInt = Integer.parseInt(numeroAirbag);
				float altezzaSeggFloat = Float.parseFloat(altezzaSeggiolino);
				int idMagazzInt = Integer.parseInt(idMagazzino);
				
				EntitaVeicolo veicolo = new EntitaVeicolo(numTelaioInt, marca, modello, colore, prezzoFloat, tipo, numPorteInt, tipologiaCambio, numAirbagInt, altezzaSeggFloat, idMagazzInt);
				daoVei.aggiungiVeicolo(veicolo);
			}
			else throw new InvalidInputException("Dati inseriti non validi, riprova.");//altrimenti lancia l'eccezione
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
			case "23503":
				throw new InvalidInputException("Id magazzino inesistente, riprova.");
			case "22001":
				throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
			default:
				throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
	}
	
	
	public static void eseguiQueryDeleteVeicolo(String numeroTelaio) throws InvalidInputException{
		DAOGestioneVeicoli daoVei = new DAOGestioneVeicoli();
		
		try {
			if(ApplicationBusinessLogic.isAllInt(numeroTelaio)) {//verifica se i dati sono validi
				//in caso positivo processa i dati
				int numTelaioInt = Integer.parseInt(numeroTelaio);

				if(!daoVei.rimuoviVeicolo(numTelaioInt)) {//in caso non venga modificata nessuna tupla, viene comunicato all'utente
					throw new InvalidInputException("Veicolo inesistente.");
				}
			}
			else {
				throw new InvalidInputException("Numero telaio non valido, riprova");
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

package controller;

import java.sql.SQLException;

import dao.DAOGestioneVeicoli;
import dao.DAOGestioneVendite;
import exceptions.InvalidInputException;
import model.EntitaVendite;

public class ProcessQueryGestioneVendite {
	public static void eseguiQueryVendita(String dataVendita, String numeroTelaio, String idCliente) throws InvalidInputException{

		DAOGestioneVendite daoVen = new DAOGestioneVendite();
		
		try {
			
			if(ApplicationBusinessLogic.isAllInt(numeroTelaio) && ApplicationBusinessLogic.isAllInt(idCliente)) {//verifica se i dati sono validi
				//in caso positivo processa i dati
				int numTelaioInt = Integer.parseInt(numeroTelaio);
				int idDipendente = InfoDipendenteLogic.getIdDipendente();
				int idCliInt = Integer.parseInt(idCliente);
				EntitaVendite vendita = new EntitaVendite(dataVendita, idDipendente, idCliInt, numTelaioInt);
				daoVen.aggiungiVendita(vendita);
			}
			else throw new InvalidInputException("Dati inseriti non validi, riprova.");//altrimenti lancia l'eccezione
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
			case "P0001":
				throw new InvalidInputException("Veicolo già venduto o restituito una volta, riprova.");
			case "22008":
				throw new InvalidInputException("Data non valida.");
			case "23503":
				ex.printStackTrace();
				throw new InvalidInputException("Numero veicolo o cliente inesistenti, riprova.");
			default:
				ex.printStackTrace();
				throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
	}
	
	
	public static void eseguiQueryRestituzione(String dataRestituzione, String idVendita) throws InvalidInputException{
		DAOGestioneVendite daoVen = new DAOGestioneVendite();
		
		try {
			
			if(ApplicationBusinessLogic.isAllInt(idVendita)) {//verifica se i dati sono validi
				//in caso positivo processa i dati
				int idVenInt = Integer.parseInt(idVendita);
				if(!daoVen.effettuaRestituzione(dataRestituzione, idVenInt)) {//in caso non venga modificata nessuna tupla, viene comunicato all'utente
					throw new InvalidInputException("Id della vendita inesistente, riprova.");
				}
			}
			else throw new InvalidInputException("Id della vendita non valido, riprova.");
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
				case "22001": //finire TODO
					throw new InvalidInputException("I dati inseriti superano il limite massimo di caratteri, riprova.");
				default:
					throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
	}
	
	//usato solo per mostrare all'utente il prezzo finale
	private static int getPercentualeDiSconto(String idCliente) throws InvalidInputException{
		
		int percentualeSconto = 0;
		DAOGestioneVendite daoVen = new DAOGestioneVendite();

		try {
			if(ApplicationBusinessLogic.isAllInt(idCliente)) {//verifica se i dati sono validi
				//in caso positivo processa i dati
				int idCliInt = Integer.parseInt(idCliente);
				percentualeSconto = daoVen.getPercentualeSconto(idCliInt);
			}
			else throw new InvalidInputException("Id cliente non valido, riprova.");//altrimenti lancia l'eccezione
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
				case "24000":
					throw new InvalidInputException("Cliente inesistente, riprova.");
				default:
					ex.printStackTrace();
					System.out.println(ex.getSQLState());
					throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
		
		return percentualeSconto;
	}
	
	
	public static float calcolaPrezzoFinale(String numeroTelaio, String idCliente) throws InvalidInputException {
		
		DAOGestioneVeicoli daoVei = new DAOGestioneVeicoli();
		float prezzoVeicolo = 0;
		
		int sconto = getPercentualeDiSconto(idCliente);
		
		try {
			if(ApplicationBusinessLogic.isAllInt(numeroTelaio)) {//verifica se i dati sono validi
				//in caso positivo processa i dati
				int numTelaioInt = Integer.parseInt(numeroTelaio);
				prezzoVeicolo = daoVei.getPrezzoVeicolo(numTelaioInt);
			}
			else throw new InvalidInputException("Campi vuoti o non validi, riprova.");//altrimenti lancia l'eccezione
		}
		catch(SQLException ex) {//gestisce gli errori lanciati dal db
			switch(ex.getSQLState()) {
			case "24000":
				throw new InvalidInputException("Veicolo inesistente, riprova");
				
				default:
					
					throw new InvalidInputException("Errore del sistema, riprova.");
			}
		}
		
		return prezzoVeicolo * (1f - (sconto/100f));
		
	}
	
}
	


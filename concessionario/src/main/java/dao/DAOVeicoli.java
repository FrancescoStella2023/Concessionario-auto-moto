package dao;

import java.sql.SQLException;

import model.EntitaVeicolo;

public interface DAOVeicoli {
	public void aggiungiVeicolo(EntitaVeicolo veicolo) throws SQLException;
	public boolean rimuoviVeicolo(int numeroTelaio) throws SQLException;
	//public static float getPrezzoVeicolo(int numeroTelaio) throws SQLException;
	//public boolean checkVeicolo

}

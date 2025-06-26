package dao;

import java.sql.SQLException;

import model.EntitaVendite;

public interface DAOVendite {
	public void aggiungiVendita(EntitaVendite vendita) throws SQLException;
	public void effettuaRestituzione(String dataRestituzione, int idVendita) throws SQLException;
	public int getPercentualeSconto(int idCliente) throws SQLException;
}

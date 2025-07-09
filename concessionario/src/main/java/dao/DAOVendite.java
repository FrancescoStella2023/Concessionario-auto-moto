package dao;

import java.sql.Date;
import java.sql.SQLException;

import model.EntitaVendite;

public interface DAOVendite {
	public void aggiungiVendita(EntitaVendite vendita) throws SQLException;
	public boolean effettuaRestituzione(Date dataRestituzione, int idVendita) throws SQLException;
	public int getPercentualeSconto(int idCliente) throws SQLException;
}

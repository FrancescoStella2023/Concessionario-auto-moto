package dao;

import java.sql.SQLException;

import model.EntitaClub;

public interface DAOClub {
	public void aggiungiClub(EntitaClub cliente) throws SQLException;
}

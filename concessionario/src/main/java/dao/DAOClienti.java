package dao;

import java.sql.SQLException;

import model.EntitaCliente;

public interface DAOClienti {
	public void aggiungiCliente(EntitaCliente cliente) throws SQLException;
}

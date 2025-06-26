package dao;

import java.sql.SQLException;

import model.EntitaMagazzino;

public interface DAOMagazzini {
	public void aggiungiMagazzino(EntitaMagazzino magazzino) throws SQLException;
}

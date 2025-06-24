package dao;

import java.sql.*;
import model.*;

public interface DAODipendenti {
	public void aggiungiDipendente(EntitaDipendente dipendente) throws SQLException;
	public boolean cambiaPassword(int idDipendente, String newPass) throws SQLException;
	public ResultSet visualizzaDipendenti() throws SQLException;
}

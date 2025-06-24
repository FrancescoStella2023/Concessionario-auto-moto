package dao;

import java.sql.*;
import model.*;

public interface DAODipendenti {
	public void aggiungiDipedente(EntitaDipendente dipendente) throws SQLException;
	public void cambiaPassword(int idDipendente, String newPass) throws SQLException;
	public ResultSet visualizzaDipendenti();
}

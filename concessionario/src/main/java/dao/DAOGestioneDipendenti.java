package dao;

import java.sql.*;

import model.EntitaDipendente;
import dbdao.DAOConnDB;


public class DAOGestioneDipendenti implements DAODipendenti{
	public void aggiungiDipedente(EntitaDipendente dipendente) throws SQLException {
		String sql = "INSERT INTO Dipendenti (nome, cognome, password, is_admin) VALUES (?, ?, ?, ?)";
		
		DAOConnDB daoConn = new DAOConnDB();
		PreparedStatement ps = daoConn.getConn().prepareStatement(sql);
		ps.setString(1, dipendente.getNome()); //riempie i '?'
		ps.setString(2,  dipendente.getCognome());
		ps.setString(3, dipendente.getPassword()); 
		ps.setString(4,  dipendente.getIsAdmin());
	}
	public void cambiaPassword(int idDipendente, String newPass);
	public ResultSet visualizzaDipendenti();
}

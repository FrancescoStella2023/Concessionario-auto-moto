package dao;

import java.sql.*;

import model.EntitaDipendente;
import dbdao.DAOConnDB;


public class DAOGestioneDipendenti implements DAODipendenti{
	
	private Connection conn = new DAOConnDB().getConn();
	
	public void aggiungiDipendente(EntitaDipendente dipendente) throws SQLException {
		String sql = "INSERT INTO Dipendenti (nome, cognome, password, is_admin) VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, dipendente.getNome()); //riempie i '?'
		ps.setString(2,  dipendente.getCognome());
		ps.setString(3, dipendente.getPassword()); 
		ps.setBoolean(4,  dipendente.getIsAdmin());
		
		ps.execute();
	}
	public boolean cambiaPassword(int idDipendente, String newPass) throws SQLException {
		String sql = "UPDATE dipendenti SET password = ? WHERE id_dipendente = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, newPass); //riempie i '?'
		ps.setInt(2,  idDipendente);
		
		int righeModificate = ps.executeUpdate();
		
		ps.close();//rilascia la risorsa
		
		if(righeModificate == 0) return false;
		else return true;
	}
	public ResultSet visualizzaDipendenti() throws SQLException {
		String sql = "SELECT * FROM dipendenti";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		ps.close();//rilascia la risorsa
		return rs;
		
	}
}

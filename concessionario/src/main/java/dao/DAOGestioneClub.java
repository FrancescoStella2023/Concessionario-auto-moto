package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbdao.DAOConnDB;
import model.EntitaClub;

public class DAOGestioneClub implements DAOClub{
	
	private Connection conn = new DAOConnDB().getConn();

	public void aggiungiClub(EntitaClub club) throws SQLException{
		String sql = "INSERT INTO Club (nome_club, percentuale_di_sconto) VALUES(?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, club.getNomeClub()); //riempie i '?'
		ps.setInt(2, club.getPercentualeSconto());
		
		ps.execute();
		ps.close();//rilascia la risorsa
	}
	
}

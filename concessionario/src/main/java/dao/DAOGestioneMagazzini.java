package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbdao.DAOConnDB;
import model.EntitaMagazzino;

public class DAOGestioneMagazzini implements DAOMagazzini{
	
	private Connection conn = new DAOConnDB().getConn();
	
	public void aggiungiMagazzino(EntitaMagazzino magazzino) throws SQLException{
		String sql = "INSERT INTO Magazzini (indirizzo) VALUES(?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, magazzino.getIndirizzo()); //riempie i '?'
		
		ps.execute();
		
		ps.close();//rilascia la risorsa
	}

}

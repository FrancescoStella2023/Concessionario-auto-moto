package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbdao.DAOConnDB;

public class DAOVisualizzaDati implements DAODati{
	private Connection conn = new DAOConnDB().getConn();
	
	public ResultSet visualizzaDati(String nomeTabella) throws SQLException{
		String sql = "SELECT * FROM " + nomeTabella; //non viene fatto il controllo per l'SQL injection perchè il nome viene cablato nel codice
		PreparedStatement ps = conn.prepareStatement(sql);
				
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}

}

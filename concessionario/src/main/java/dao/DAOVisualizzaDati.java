package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbdao.DAOConnDB;

public class DAOVisualizzaDati implements DAODati{
	private Connection conn = new DAOConnDB().getConn();
	
	public ResultSet visualizzaDati(String nomeTabella) throws SQLException{
		String sql = "SELECT * FROM ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, nomeTabella); //riempie i '?'
		
		ResultSet rs = ps.executeQuery();
		
		ps.close();//rilascia la risorsa
		return rs;
	}

}

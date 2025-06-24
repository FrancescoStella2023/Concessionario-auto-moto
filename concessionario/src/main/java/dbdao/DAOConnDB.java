package dbdao;

import java.sql.*;

public class DAOConnDB implements DAOConnection{
	public static Connection conn;
	public boolean verificaAccesso(int idDipendente, String password)  throws SQLException {
		String sql = "SELECT * FROM dipendenti WHERE id_dipendente = ? AND password = ?"; //prepara l'sql per evitare injection
		PreparedStatement ps = conn.prepareStatement(sql);
		String idDipendenteString = Integer.toString(idDipendente); //trasforma idDipendente in String
		ps.setString(1, idDipendenteString); //riempie i '?'
		ps.setString(2,  password);
		
		ResultSet rs = ps.executeQuery(); //esegue la query e salva i risultati
		if(rs == null) return false; //se è vuota la password o l'idDipendente sono errati e ritorna false
		else return true; //altrimenti ritorna true e viene eseguito l'accesso
	}
	
	public boolean verificaAdmin(int idDipendente) throws SQLException{
		String sql = "SELECT isAdmin FROM dipendenti WHERE id_dipendente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		String idDipendenteString = Integer.toString(idDipendente);
		ps.setString(1,idDipendenteString);
		
		ResultSet rs = ps.executeQuery();
		
		boolean isAdmin = rs.getBoolean(1); //salva il risultato
		
		//verifica se è admin
		if(isAdmin) return true;
		else return false;
	}
	
	public void startConn(String url, String user, String password) throws SQLException{
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public Connection getConn() {
		return conn;
	}
}

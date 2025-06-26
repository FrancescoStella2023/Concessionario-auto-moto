package dbdao;

import java.sql.*;

public class DAOConnDB implements DAOConnection{
	public static Connection conn;
	public boolean verificaAccesso(String idDipendente, String password)  throws SQLException {
		String sql = "SELECT * FROM dipendenti WHERE id_dipendente = ? AND password = ?"; //prepara l'sql per evitare injection
		PreparedStatement ps = conn.prepareStatement(sql);
		
		
		int idDipendenteInt = idDipendente.
		ps.setInt(1, idDipendente); //riempie i '?'
		ps.setString(2,  password);
		
		ResultSet rs = ps.executeQuery(); //esegue la query e salva i risultati
		return rs.next(); //ritorna true o false a seconda se esistono o meno risultati quindi se l'idDIpendente e la password sono corretti oppure no
	}
	
	public boolean verificaAdmin(int idDipendente) throws SQLException {
	    String sql = "SELECT isAdmin FROM dipendenti WHERE id_dipendente = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, idDipendente); 

	    ResultSet rs = ps.executeQuery();

	    if (rs.next()) {
	        return rs.getBoolean(1);
	    } else {
	        // Nessun risultato trovato: dipendente inesistente
	        return false;
	    }
	}

	
	public void startConn(String url, String user, String password) throws SQLException{
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public Connection getConn() {
		return conn;
	}
}

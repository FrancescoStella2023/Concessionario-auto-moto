package dbdao;

import java.sql.*;

public interface DAOConnection {
	public boolean verificaAccesso (String idDipendente, String password) throws SQLException;
	public boolean verificaAdmin(int idDipendente) throws SQLException;
	public void startConn(String url, String user, String password) throws SQLException;
	public Connection getConn();
}

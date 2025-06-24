package dbdao;

import java.sql.*;

public interface DAOConnection {
	public boolean verificaAccesso (int idDipendente, String password) throws SQLException;
	public boolean verificaAdmin(int idDipendente) throws SQLException;
	public void startConn(String url, String user, String password) throws SQLException;
	public Connection getConn();
}

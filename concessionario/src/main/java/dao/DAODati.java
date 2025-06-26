package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAODati {
	public ResultSet visualizzaDati(String nomeTabella) throws SQLException;
}

package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOVisualizzaDati;

public class ProcessQueryMostraDati {
	public static ArrayList<String> eseguiQueryView(String nomeTabella) throws SQLException{
	    DAOVisualizzaDati daoData = new DAOVisualizzaDati();
	    ArrayList<String> listaDati = new ArrayList<>();

	    ResultSet rs = daoData.visualizzaDati(nomeTabella); 

	    int columnCount = rs.getMetaData().getColumnCount(); //pre spare quante colonne ci sono nel risultato

	    //salva i nomi delle colonne
	    for (int i = 1; i <= columnCount; i++) {
	        listaDati.add(rs.getMetaData().getColumnLabel(i)); 
	    }

	    //salva i dati
	    while (rs.next()) {
	        for (int i = 1; i <= columnCount; i++) {
	            listaDati.add(rs.getString(i));
	        }
	    }
	    
	    rs.close();
	    return listaDati;
	}
}

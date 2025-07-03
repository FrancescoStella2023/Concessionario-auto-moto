package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOVisualizzaDati;

public class ProcessQueryMostraDati {
	public static ArrayList<String[]> eseguiQueryView(String nomeTabella) throws SQLException {
	    DAOVisualizzaDati daoData = new DAOVisualizzaDati();
	    ArrayList<String[]> risultato = new ArrayList<>();

	    ResultSet rs = daoData.visualizzaDati(nomeTabella); 

	    int columnCount = rs.getMetaData().getColumnCount();

	    //array per le intestazioni
	    String[] intestazioni = new String[columnCount];
	    for (int i = 0; i < columnCount; i++) {
	        intestazioni[i] = rs.getMetaData().getColumnLabel(i + 1);
	    }

	    //lista per contenere tutti i valori riga per riga
	    ArrayList<String> valori = new ArrayList<>();

	    while (rs.next()) {
	        for (int i = 1; i <= columnCount; i++) {
	            valori.add(rs.getString(i));
	        }
	    }

	    //converte la lista di valori in array
	    String[] arrayValori = valori.toArray(new String[0]);

	    //aggiunge entrambi al risultato
	    risultato.add(intestazioni);
	    risultato.add(arrayValori);

	    rs.close();
	    return risultato;
	}

}

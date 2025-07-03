package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.InfoDipendenteLogic;
import dbdao.DAOConnDB;
import model.EntitaVendite;

public class DAOGestioneVendite implements DAOVendite{
	
	private Connection conn = new DAOConnDB().getConn();
	
	public void aggiungiVendita(EntitaVendite vendita) throws SQLException{
		String sql = "INSERT INTO vendite(v_id_dipendente, v_numero_telaio, v_id_cliente) values(?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, InfoDipendenteLogic.getIdDipendente());
		ps.setInt(2, vendita.getNumeroTelaio()); //riempie i '?'
		ps.setInt(3, vendita.getIdCliente()); 
		
		ps.execute();
		
		ps.close();//rilascia la risorsa
	}
	
	public boolean effettuaRestituzione(String dataRestituzione, int idVendita) throws SQLException{
		String sql = "UPDATE vendite set data_restituzione = '?' WHERE id_vendita = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, dataRestituzione); //riempie i '?'
		ps.setInt(2,  idVendita);
		
		int righeModificate = ps.executeUpdate();
		
		ps.close();//rilascia la risorsa
		
		if(righeModificate == 0) return false;
		else return true;
	}
	
	public int getPercentualeSconto(int idCliente) throws SQLException{
		//utilizzato solo per mostrare al dipendente che sta effettuando la vendita
		//quale sarà il prezzo finale, il valore effettivo poi viene calcolato dal trigger
		
		String sql = "SELECT cl.percentuale_di_sconto FROM clienti as c JOIN club as cl ON c.c_id_club = cl.id_club WHERE c.id_cliente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1,  idCliente);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		return rs.getInt(1);
		
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbdao.DAOConnDB;
import model.EntitaVeicolo;

public class DAOGestioneVeicoli implements DAOVeicoli{
	
	private Connection conn = new DAOConnDB().getConn();

	public void aggiungiVeicolo(EntitaVeicolo veicolo) throws SQLException{
		String sql = "INSERT INTO Veicolo (numero_telaio, v_id_magazzino, marca, modello, colore, prezzo, tipo, numero_porte, tipologia_cambio, numero_airbag, altezza_seggiolino) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, veicolo.getNumeroTelaio());
		ps.setInt(2, veicolo.getIdMagazzino());
		ps.setString(3, veicolo.getMarca());
		ps.setString(4, veicolo.getModello());
		ps.setString(5, veicolo.getColore());
		ps.setFloat(6, veicolo.getPrezzo());
		ps.setString(7, veicolo.getTipo());
		
		if(veicolo.getTipo().equals("m")) { //se è una moto
			ps.setNull(8, java.sql.Types.INTEGER); //mette a null numero_porte
			ps.setNull(10, java.sql.Types.INTEGER); //mette a null numero_airbag
			ps.setFloat(11, veicolo.getAltezzaSeggiolino()); 
		}
		else {
			ps.setInt(8, veicolo.getNumeroPorte());
			ps.setInt(10, veicolo.getNumeroAirbag());
			ps.setNull(11, java.sql.Types.FLOAT); //mette a null altezza_seggiolino
		}
		
		ps.setString(9, veicolo.getTipologiaCambio());
		
		
		ps.execute();
		ps.close();//rilascia la risorsa
	}
	
	public boolean rimuoviVeicolo(int numeroTelaio) throws SQLException{
		String sql = "DELETE FROM veicoli where numero_telaio = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, numeroTelaio);
		
		int elementiRimossi = ps.executeUpdate();
		
		ps.close();//rilascia la risorsa
		
		if(elementiRimossi == 0) return false; //se non è stato eliminato niente
		else return true;
		
	}
	
	public float getPrezzoVeicolo(int numeroTelaio) throws SQLException{
		String sql = "SELECT prezzo FROM veicolo WHERE numero_telaio = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, numeroTelaio);
		
		ResultSet rs = ps.executeQuery();
		
		float res = rs.getFloat(1);
		
		ps.close();//rilascia la risorsa
		rs.close();//rilascia la risorsa
		return res;
	}
}

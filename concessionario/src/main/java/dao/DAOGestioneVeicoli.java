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
		
		if(veicolo.getTipo().equals("m")) {
			ps.setNull(8, java.sql.Types.INTEGER); //mette a null per il tipo int
			ps.setNull(10, java.sql.Types.INTEGER);
			ps.setFloat(11, veicolo.getAltezzaSeggiolino());
		}
		else {
			ps.setInt(8, veicolo.getNumeroPorte());
			ps.setNull(11, java.sql.Types.FLOAT);
		}
		
		ps.setString(9, veicolo.getTipologiaCambio());
		
		
		ps.execute();
	}
	
	public boolean rimuoviVeicolo(int numeroTelaio) throws SQLException{
		String sql = "DELETE FROM veicoli where numero_telaio = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, numeroTelaio);
		
		int elementiRimossi = ps.executeUpdate();
		
		if(elementiRimossi == 0) return false;
		else return true;
		
	}
	
	public float getPrezzoVeicolo(int numeroTelaio) throws SQLException{
		String sql = "SELECT prezzo FROM veicolo WHERE numero_telaio = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, numeroTelaio);
		
		ResultSet rs = ps.executeQuery();
		
		float res = rs.getFloat(1);
		return res;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbdao.DAOConnDB;
import model.EntitaCliente;

public class DAOGestioneClienti implements DAOClienti{
	
	private Connection conn = new DAOConnDB().getConn();

	
	public void aggiungiCliente(EntitaCliente cliente) throws SQLException{
		if(cliente.getIdClub() != 0) {
			String sql = "INSERT INTO Clienti (c_id_club, nome, cognome, email, numero_di_telefono, comune, num_civico, indirizzo) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,cliente.getIdClub());
			
			ps.setString(2, cliente.getNome()); //riempie i '?'
			ps.setString(3,  cliente.getCognome());
			ps.setString(4, cliente.getEmail()); 
			ps.setString(5,  cliente.getNumeroTelefono());
			ps.setString(6, cliente.getComune());
			ps.setInt(7, cliente.getNumCivico());
			ps.setString(8, cliente.getIndirizzo());
			
			ps.execute();
			
			ps.close();//rilascia la risorsa
		}
		else {
			String sql = "INSERT INTO Clienti (nome, cognome, email, numero_di_telefono, comune, num_civico, indirizzo) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
						
			ps.setString(1, cliente.getNome()); //riempie i '?'
			ps.setString(2,  cliente.getCognome());
			ps.setString(3, cliente.getEmail()); 
			ps.setString(4,  cliente.getNumeroTelefono());
			ps.setString(5, cliente.getComune());
			ps.setInt(6, cliente.getNumCivico());
			ps.setString(7, cliente.getIndirizzo());
			
			ps.execute();
			
			ps.close();//rilascia la risorsa
		}
	}
	public boolean checkClubCliente(int idClub) throws SQLException {
		String sql = "SELECT * FROM club where id_club = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, idClub); //riempie i '?'
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) return true;
		else return false;
	}
}

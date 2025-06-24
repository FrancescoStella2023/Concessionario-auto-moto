package model;

public class EntitaCliente {
	String nome;
	String cognome;
	String email;
	String numeroTelefono;
	String comune;
	int numCivico;
	String indirizzo;
	int idClub;
	
	public EntitaCliente(String nome, String cognome, String email, String numeroTelefono, String comune, int numCivico, String indirizzo, int idClub) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.numeroTelefono = numeroTelefono;
		this.comune = comune;
		this.numCivico = numCivico;
		this.indirizzo = indirizzo;
		this.idClub = idClub;
	}

	public int getIdClub() {
		return idClub;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public int getNumCivico() {
		return numCivico;
	}

	public String getComune() {
		return comune;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public String getEmail() {
		return email;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}
	
}

package model;

public class EntitaDipendente {
	private String nome;
	private String cognome;
	private String password;
	private boolean isAdmin;
	
	public EntitaDipendente(String nome, String cognome, String password, boolean isAdmin) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.isAdmin = isAdmin;
		
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}
	
	
}

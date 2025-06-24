package model;

public class EntitaVeicolo {
	private int numeroTelaio;
	private String marca;
	private String modello;
	private String colore;
	private float prezzo;
	private boolean stato;
	private char tipo;
	private int numeroPorte;
	private char tipologiaCambio;
	private int numeroAirbag;
	private float altezzaSeggiolino;
	private int idMagazzino;
	
	public EntitaVeicolo(int numeroTelaio, String marca, String modello, String colore, float prezzo, boolean stato, char tipo, int numeroPorte, char tipologiaCambio, int numeroAirbag, float altezzaSeggiolino, int idMagazzino) {
		this.numeroTelaio = numeroTelaio;
		this.marca = marca;
		this.modello = modello;
		this.colore = colore;
		this. prezzo = prezzo;
		this. stato = stato;
		this. tipo = tipo;
		this. numeroPorte = numeroPorte;
		this. tipologiaCambio = tipologiaCambio;
		this. numeroAirbag = numeroAirbag;
		this. altezzaSeggiolino = altezzaSeggiolino;
		this. idMagazzino = idMagazzino;
	}

	public int getIdMagazzino() {
		return idMagazzino;
	}

	public float getAltezzaSeggiolino() {
		return altezzaSeggiolino;
	}

	public int getNumeroAirbag() {
		return numeroAirbag;
	}

	public char getTipologiaCambio() {
		return tipologiaCambio;
	}

	public int getNumeroPorte() {
		return numeroPorte;
	}

	public char getTipo() {
		return tipo;
	}

	public boolean isStato() {
		return stato;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public String getColore() {
		return colore;
	}

	public String getModello() {
		return modello;
	}

	public String getMarca() {
		return marca;
	}

	public int getNumeroTelaio() {
		return numeroTelaio;
	}
	
	
}

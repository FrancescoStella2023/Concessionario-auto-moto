package model;

public class EntitaVeicolo {
	private int numeroTelaio;
	private String marca;
	private String modello;
	private String colore;
	private float prezzo;
	private String tipo;
	private int numeroPorte;
	private String tipologiaCambio;
	private int numeroAirbag;
	private float altezzaSeggiolino;
	private int idMagazzino;
	
	public EntitaVeicolo(int numeroTelaio, String marca, String modello, String colore, float prezzo, String tipo, int numeroPorte, String tipologiaCambio, int numeroAirbag, float altezzaSeggiolino, int idMagazzino) {
		this.numeroTelaio = numeroTelaio;
		this.marca = marca;
		this.modello = modello;
		this.colore = colore;
		this.prezzo = prezzo;
		this.tipo = tipo;
		this.numeroPorte = numeroPorte;
		this.tipologiaCambio = tipologiaCambio;
		this.numeroAirbag = numeroAirbag;
		this.altezzaSeggiolino = altezzaSeggiolino;
		this.idMagazzino = idMagazzino;
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

	public String getTipologiaCambio() {
		return tipologiaCambio;
	}

	public int getNumeroPorte() {
		return numeroPorte;
	}

	public String getTipo() {
		return tipo;
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

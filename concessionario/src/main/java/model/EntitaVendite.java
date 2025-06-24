package model;

public class EntitaVendite {
	private String dataVendita;
	private int idDipendente;
	private int idCliente;
	private int numeroTelaio;
	
	public EntitaVendite(String dataVendita, int idDipendente, int idCliente, int numeroTelaio) {
		this.dataVendita = dataVendita;
		this.idCliente = idCliente;
		this.numeroTelaio = numeroTelaio;
		this.idDipendente = 0; ///PRENDERE DA InfoDipendenteLogic IN SEGUITO///
	}

	public int getNumeroTelaio() {
		return numeroTelaio;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdDipendente() {
		return idDipendente;
	}

	public String getDataVendita() {
		return dataVendita;
	}
	
}

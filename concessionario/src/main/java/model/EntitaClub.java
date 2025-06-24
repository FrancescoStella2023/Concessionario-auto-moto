package model;

public class EntitaClub {
	private String nomeClub;
	private int percentualeSconto;
	
	public EntitaClub(String nomeClub, int percentualeSconto) {
		this.nomeClub = nomeClub;
		this.percentualeSconto = percentualeSconto;
	}

	public int getPercentualeSconto() {
		return percentualeSconto;
	}

	public String getNomeClub() {
		return nomeClub;
	}
	
}

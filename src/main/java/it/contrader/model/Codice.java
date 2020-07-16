package it.contrader.model;

public class Codice {
	private int id;
	private int codice;
	
	public Codice(int id,int codice) {
		this.id=id;
		this.codice= codice;
		
	}

	public int getid () {
		return id;
	}

	public void setid(int id) {
		this.id=id;
	}

	public int getCodice () {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice=codice;
	}


}

package it.contrader.dto;

public class MagazzinoDTO {
	
	private int id;
	private int id_oggetto;
	private int capienza;
	private int posizione;
	public MagazzinoDTO(int id_oggetto, int capienza, int posizione) {
		this.id_oggetto = id_oggetto;
		this.capienza = capienza;
		this.posizione= posizione;
	}
	
	public MagazzinoDTO(int id, int id_oggetto, int capienza, int posizione) {
		this.id = id;
		this.id_oggetto = id_oggetto;
		this.capienza = capienza;
		this.posizione= posizione;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_oggetto() {
		return id_oggetto;
	}
	public void setId_oggetto(int id_oggetto) {
		this.id_oggetto = id_oggetto;
	}
	public int getCapienza() {
		return capienza;
	}
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	public int getPosizione() {
		return posizione;
	}
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	@Override
	public String toString() {
		return  id + "\t\t"  + id_oggetto +"\t\t" + capienza + "\t\t" + posizione;
	}
	

}

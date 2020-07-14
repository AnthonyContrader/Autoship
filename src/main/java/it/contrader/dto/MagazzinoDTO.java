package it.contrader.dto;

import it.contrader.model.Oggetto;

public class MagazzinoDTO {
	
	private int id;
	private Oggetto id_oggetto;
	private int capienza;
	private int posizione;
	public MagazzinoDTO(Oggetto id_oggetto, int capienza, int posizione) {
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
	public Oggetto getId_oggetto() {
		return id_oggetto;
	}
	public void setId_oggetto(Oggetto id_oggetto) {
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


}

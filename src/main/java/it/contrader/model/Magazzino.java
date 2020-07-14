package it.contrader.model;

public class Magazzino {
private int id;
private Oggetto id_oggetto;
private int capienza;
private int posizione;
public Magazzino(Oggetto id_oggetto, int capienza, int posizione) {
	id_oggetto= this.id_oggetto;
	capienza= this.capienza;
	posizione= this.posizione;
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

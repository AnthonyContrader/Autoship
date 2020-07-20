package it.contrader.model;

public class Magazzino {
private int id;
private int id_oggetto;
private int capienza;
private boolean cancellato;
public Magazzino(int id_oggetto, int capienza) {
	this.id_oggetto= id_oggetto;
	this.capienza= capienza;
	
}

public Magazzino(int id, int id_oggetto, int capienza) {
	this.id = id;
	this.id_oggetto= id_oggetto;
	this.capienza= capienza;
	
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

}

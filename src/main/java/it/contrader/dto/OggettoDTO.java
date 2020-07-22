package it.contrader.dto;

public class OggettoDTO {
private int id;
private String nome;
private int dimensione;
private int cancellato;
private boolean cella;

public OggettoDTO(String nome, int dimensione, int cancellato){
	this.nome = nome;
	this.dimensione = dimensione;
	this.cancellato = cancellato;
	this.cella=false;
}

public OggettoDTO(int id, String nome, int dimensione, int cancellato){
	this.id = id;
	this.nome = nome;
	this.dimensione = dimensione; 
	this.cancellato = cancellato;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public boolean getCella() {
	return cella;
}

public void setCella(boolean cella) {
	this.cella = cella;
}

public int getDimensione() {
	return dimensione;
}
public void setDimensione(int dimensione) {
	this.dimensione = dimensione;
}

public int getCancellato() {
	return cancellato;
}
public void setCancellato(int cancellato) {
	this.cancellato = cancellato;
}


}

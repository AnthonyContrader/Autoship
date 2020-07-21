package it.contrader.dto;

public class OggettoDTO {
private int id;
private String nome;
private int dimensione;
private boolean cella;

public OggettoDTO(String nome, int dimensione){
	this.nome = nome;
	this.dimensione = dimensione; 
	this.cella=false;
}

public OggettoDTO(int id, String nome, int dimensione){
	this.id = id;
	this.nome = nome;
	this.dimensione = dimensione; 
	this.cella=false;
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


}

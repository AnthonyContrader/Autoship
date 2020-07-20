package it.contrader.model;

public class Oggetto {
private int id;
private String nome;
private int dimensione;
private boolean cancellato;

public Oggetto(String nome, int dimensione){
	this.nome = nome;
	this.dimensione = dimensione; 
}

public Oggetto(int id, String nome, int dimensione){
	this.id = id;
	this.nome = nome;
	this.dimensione = dimensione; 
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
public int getDimensione() {
	return dimensione;
}
public void setDimensione(int dimensione) {
	this.dimensione = dimensione;
}


}

package it.contrader.dto;

public class CodiceDTO {
private int id;
private int codice;
public CodiceDTO(String nome, int codice){
	this.id= id;
	this.codice = codice; 
}

public CodiceDTO(int id, int dimensione, int codice){
	this.id = id;
	this.codice = codice; 
}

public int getId() {
	return id;
}
public void setCodice(int id) {
	this.codice = id;
}



}

package it.contrader.dto;

public class CodiceDTO {
private int id;
private int codice;
public CodiceDTO(int id, int codice){
	this.id = id;
	this.codice = codice; 
}

public CodiceDTO(int codice){
	this.codice = codice; 
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public int getCodice() {
	return this.codice;
}
public void setCodice(int codice) {
	this.codice=codice;
}


}

package it.contrader.dto;

public class CodiceDTO {
private int id;
private int codice;
<<<<<<< HEAD
public CodiceDTO(int id, int codice){
	this.id = id;
	this.codice = codice; 
}

public CodiceDTO(int codice){
=======
public CodiceDTO(String nome, int codice){
	this.id= id;
	this.codice = codice; 
}

public CodiceDTO(int id, int dimensione, int codice){
	this.id = id;
>>>>>>> 0ace919cf34d1162062a0b0f11dc68a864900b94
	this.codice = codice; 
}

public int getId() {
	return id;
}
<<<<<<< HEAD
public void setId(int id) {
	this.id = id;
}
public int getCodice() {
	return this.codice;
}
public void setCodice(int codice) {
	this.codice=codice;
}
=======
public void setCodice(int id) {
	this.codice = id;
}

>>>>>>> 0ace919cf34d1162062a0b0f11dc68a864900b94


}

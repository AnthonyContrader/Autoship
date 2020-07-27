package it.contrader.dto;

public class MagazzinoDTO {
private int id;
private int id_oggetto;
private int capienza;
private String otp;
private int cancellato;
private String nome_oggetto;

public MagazzinoDTO(int id_oggetto, int capienza, String otp, int cancellato) {
	this.id_oggetto= id_oggetto;
	this.capienza= capienza;
	this.otp = otp;
	this.cancellato = cancellato;
}

public MagazzinoDTO(int id, int id_oggetto, int capienza, String otp, int cancellato) {
	this.id = id;
	this.id_oggetto= id_oggetto;
	this.capienza= capienza;
	this.otp = otp;
	this.cancellato = cancellato;
	
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

public String getOtp() {
	return this.otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}

public int getCancellato() {
	return cancellato;
}
public void setCancellato(int cancellato) {
	this.cancellato = cancellato;
}

public String getNome_oggetto() {
	return this.nome_oggetto;
}

public void setNome_oggetto(String nome_oggetto) {
	this.nome_oggetto = nome_oggetto;
}

}

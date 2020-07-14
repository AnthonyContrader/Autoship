package it.contrader.dto;

public class OggettoDTO {
	private int id;
	private String nome;
	private int dimensione;
	public OggettoDTO(String nome, int dimensione){
		nome = this.nome;
		dimensione = this.dimensione;
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

package it.contrader.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Carrello {
	
	public enum CarrelloStato {
		Ordinato, Spedito
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_oggetto", referencedColumnName = "id")
	private Oggetto oggetto;
	
	@ManyToOne
	@JoinColumn(name = "id_codice", referencedColumnName = "id")
	private Codice codice;
	
	@Enumerated(EnumType.STRING)
	private CarrelloStato stato;
}

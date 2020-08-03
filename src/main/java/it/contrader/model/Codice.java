package it.contrader.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Codice {
	
	public enum CodiceStato {
		Attesa, Confermato, Spedito
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String otp;
	
	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private CodiceStato stato;
	
	private boolean cancellato;
}

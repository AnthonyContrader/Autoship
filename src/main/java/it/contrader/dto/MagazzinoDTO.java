package it.contrader.dto;

import model.AllArgsConstructor;
import model.Column;
import model.Data;
import model.Entity;
import model.GeneratedValue;
import model.Id;
import model.Long;
import model.Magazzino;
import model.NoArgsConstructor;
import model.String;
import model.java;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class MagazzinoDTO {
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column(unique = true)
		private int id;
		private int id_oggetto;
		private int capienza;
		private String otp;
		private int cancellato;

	}
}

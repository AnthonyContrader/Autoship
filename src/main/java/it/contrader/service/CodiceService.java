package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.CodiceConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.CodiceRepository;
import it.contrader.dao.UserRepository;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Codice;
import it.contrader.model.Oggetto;

@Service
public class CodiceService extends AbstractService<Codice, CodiceDTO> {
	
	@Autowired
	private CodiceConverter converter;
	@Autowired
	private CodiceRepository repository;
	
	public CodiceDTO findByOtp(String otp) {
		return converter.toDTO(repository.findByOtp(otp));
	}

}

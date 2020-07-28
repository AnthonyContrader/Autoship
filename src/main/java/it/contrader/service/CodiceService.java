package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.CodiceConverter;
import it.contrader.dao.CodiceRepository;
import it.contrader.dto.CodiceDTO;
import it.contrader.model.Codice;

@Service
public class CodiceService extends AbstractService<Codice, CodiceDTO> {
	
	@Autowired
	private CodiceConverter converter;
	@Autowired
	private CodiceRepository repository;
	
	public CodiceDTO findByOtp(String otp) {
		return converter.toDTO(repository.findByOtp(otp));
	}
	
	public List<CodiceDTO> findByCancellatoFalse() {
		return converter.toDTOList(repository.findByCancellatoFalse());
	}

}

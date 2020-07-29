package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.OggettoConverter;
import it.contrader.dao.OggettoRepository;
import it.contrader.dto.OggettoDTO;
import it.contrader.model.Oggetto;

@Service
public class OggettoService extends AbstractService<Oggetto, OggettoDTO>{

	@Autowired
	private OggettoConverter converter;
	@Autowired
	private OggettoRepository repository;
	
	public List<OggettoDTO> findByCancellatoFalse() {
		return converter.toDTOList(repository.findByCancellatoFalse());
	}
}

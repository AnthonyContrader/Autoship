package it.contrader.autoship.service.impl;

import it.contrader.autoship.service.MagazzinoService;
import it.contrader.autoship.domain.Magazzino;
import it.contrader.autoship.domain.Oggetto;
import it.contrader.autoship.repository.MagazzinoRepository;
import it.contrader.autoship.service.dto.MagazzinoDTO;
import it.contrader.autoship.service.dto.OggettoDTO;
import it.contrader.autoship.service.mapper.MagazzinoMapper;
import it.contrader.autoship.service.mapper.OggettoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Magazzino.
 */
@Service
@Transactional
public class MagazzinoServiceImpl implements MagazzinoService {

    private final Logger log = LoggerFactory.getLogger(MagazzinoServiceImpl.class);

    private final MagazzinoRepository magazzinoRepository;

    private final MagazzinoMapper magazzinoMapper;
    
    private final OggettoMapper oggettoMapper;

    public MagazzinoServiceImpl(MagazzinoRepository magazzinoRepository, MagazzinoMapper magazzinoMapper, OggettoMapper oggettoMapper) {
        this.magazzinoRepository = magazzinoRepository;
        this.magazzinoMapper = magazzinoMapper;
        this.oggettoMapper = oggettoMapper;
    }

    /**
     * Save a magazzino.
     *
     * @param magazzinoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MagazzinoDTO save(MagazzinoDTO magazzinoDTO) {
        log.debug("Request to save Magazzino : {}", magazzinoDTO);
        Magazzino magazzino = magazzinoMapper.toEntity(magazzinoDTO);
        magazzino = magazzinoRepository.save(magazzino);
        return magazzinoMapper.toDto(magazzino);
    }

    /**
     * Get all the magazzinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MagazzinoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Magazzinos");
        return magazzinoRepository.findAll(pageable)
            .map(magazzinoMapper::toDto);
    }


    /**
     * Get one magazzino by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MagazzinoDTO> findOne(Long id) {
        log.debug("Request to get Magazzino : {}", id);
        return magazzinoRepository.findById(id)
            .map(magazzinoMapper::toDto);
    }

    /**
     * Delete the magazzino by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Magazzino : {}", id);
        magazzinoRepository.deleteById(id);
    }

	@Override
	public Optional<MagazzinoDTO> findByOggetto(OggettoDTO oggetto) {
		return magazzinoRepository.findByOggetto(oggettoMapper.toEntity(oggetto))
	            .map(magazzinoMapper::toDto);
	}
}

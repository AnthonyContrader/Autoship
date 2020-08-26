package it.contrader.autoship.service.impl;

import it.contrader.autoship.service.OggettoService;
import it.contrader.autoship.domain.Oggetto;
import it.contrader.autoship.repository.OggettoRepository;
import it.contrader.autoship.service.dto.OggettoDTO;
import it.contrader.autoship.service.mapper.OggettoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Oggetto.
 */
@Service
@Transactional
public class OggettoServiceImpl implements OggettoService {

    private final Logger log = LoggerFactory.getLogger(OggettoServiceImpl.class);

    private final OggettoRepository oggettoRepository;

    private final OggettoMapper oggettoMapper;

    public OggettoServiceImpl(OggettoRepository oggettoRepository, OggettoMapper oggettoMapper) {
        this.oggettoRepository = oggettoRepository;
        this.oggettoMapper = oggettoMapper;
    }

    /**
     * Save a oggetto.
     *
     * @param oggettoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OggettoDTO save(OggettoDTO oggettoDTO) {
        log.debug("Request to save Oggetto : {}", oggettoDTO);
        Oggetto oggetto = oggettoMapper.toEntity(oggettoDTO);
        oggetto = oggettoRepository.save(oggetto);
        return oggettoMapper.toDto(oggetto);
    }

    /**
     * Get all the oggettos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OggettoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Oggettos");
        return oggettoRepository.findAll(pageable)
            .map(oggettoMapper::toDto);
    }


    /**
     * Get one oggetto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OggettoDTO> findOne(Long id) {
        log.debug("Request to get Oggetto : {}", id);
        return oggettoRepository.findById(id)
            .map(oggettoMapper::toDto);
    }

    /**
     * Delete the oggetto by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Oggetto : {}", id);
        oggettoRepository.deleteById(id);
    }
}

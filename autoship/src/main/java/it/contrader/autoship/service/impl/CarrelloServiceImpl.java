package it.contrader.autoship.service.impl;

import it.contrader.autoship.service.CarrelloService;
import it.contrader.autoship.domain.Carrello;
import it.contrader.autoship.repository.CarrelloRepository;
import it.contrader.autoship.service.dto.CarrelloDTO;
import it.contrader.autoship.service.mapper.CarrelloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Carrello.
 */
@Service
@Transactional
public class CarrelloServiceImpl implements CarrelloService {

    private final Logger log = LoggerFactory.getLogger(CarrelloServiceImpl.class);

    private final CarrelloRepository carrelloRepository;

    private final CarrelloMapper carrelloMapper;

    public CarrelloServiceImpl(CarrelloRepository carrelloRepository, CarrelloMapper carrelloMapper) {
        this.carrelloRepository = carrelloRepository;
        this.carrelloMapper = carrelloMapper;
    }

    /**
     * Save a carrello.
     *
     * @param carrelloDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CarrelloDTO save(CarrelloDTO carrelloDTO) {
        log.debug("Request to save Carrello : {}", carrelloDTO);
        Carrello carrello = carrelloMapper.toEntity(carrelloDTO);
        carrello = carrelloRepository.save(carrello);
        return carrelloMapper.toDto(carrello);
    }

    /**
     * Get all the carrellos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CarrelloDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Carrellos");
        return carrelloRepository.findAll(pageable)
            .map(carrelloMapper::toDto);
    }


    /**
     * Get one carrello by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CarrelloDTO> findOne(Long id) {
        log.debug("Request to get Carrello : {}", id);
        return carrelloRepository.findById(id)
            .map(carrelloMapper::toDto);
    }

    /**
     * Delete the carrello by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Carrello : {}", id);
        carrelloRepository.deleteById(id);
    }
}

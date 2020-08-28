package it.contrader.autoship.service.impl;

import it.contrader.autoship.service.CodiceService;
import it.contrader.autoship.domain.Codice;
import it.contrader.autoship.domain.enumeration.CodiceStato;
import it.contrader.autoship.repository.CodiceRepository;
import it.contrader.autoship.service.dto.CodiceDTO;
import it.contrader.autoship.service.mapper.CodiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Codice.
 */
@Service
@Transactional
public class CodiceServiceImpl implements CodiceService {

    private final Logger log = LoggerFactory.getLogger(CodiceServiceImpl.class);

    private final CodiceRepository codiceRepository;

    private final CodiceMapper codiceMapper;

    public CodiceServiceImpl(CodiceRepository codiceRepository, CodiceMapper codiceMapper) {
        this.codiceRepository = codiceRepository;
        this.codiceMapper = codiceMapper;
    }

    /**
     * Save a codice.
     *
     * @param codiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CodiceDTO save(CodiceDTO codiceDTO) {
        log.debug("Request to save Codice : {}", codiceDTO);
        Codice codice = codiceMapper.toEntity(codiceDTO);
        codice = codiceRepository.save(codice);
        return codiceMapper.toDto(codice);
    }

    /**
     * Get all the codices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CodiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Codices");
        return codiceRepository.findAll(pageable)
            .map(codiceMapper::toDto);
    }


    /**
     * Get one codice by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CodiceDTO> findOne(Long id) {
        log.debug("Request to get Codice : {}", id);
        return codiceRepository.findById(id)
            .map(codiceMapper::toDto);
    }
    
    @Override
    public Optional<CodiceDTO> findByOtp(String otp) {
        return codiceRepository.findByOtp(otp)
            .map(codiceMapper::toDto);
    }
    
    @Override
    public Page<CodiceDTO> findByUserId(Pageable pageable, Long user_id) {
        return codiceRepository.findByUserid(pageable, user_id)
            .map(codiceMapper::toDto);
    }

    /**
     * Delete the codice by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete (Long id) {
        log.debug("Request to delete Codice : {}", id);
        codiceRepository.deleteById(id);
        
        
    }
    
   /*    @Override
    public  Page<CodiceDTO> findCarrellosByCodice( CodiceDTO codiceDTO) {
        log.debug("Request to delete Codice : {}", id);
        codiceRepository.deleteById(id);
        
        
    }*/
    
    @Override
    public Page<CodiceDTO> findByStato(Pageable pageable, CodiceStato stato ) {
        return codiceRepository.findByStato(pageable, stato)
            .map(codiceMapper::toDto);
    }
    
    
}

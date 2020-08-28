package it.contrader.autoship.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import it.contrader.autoship.service.CodiceService;
import it.contrader.autoship.service.MagazzinoService;
import it.contrader.autoship.service.OggettoService;
import it.contrader.autoship.service.dto.CodiceDTO;
import it.contrader.autoship.service.dto.MagazzinoDTO;
import it.contrader.autoship.service.CarrelloService;
import it.contrader.autoship.service.dto.CarrelloDTO;
import it.contrader.autoship.web.rest.errors.BadRequestAlertException;
import it.contrader.autoship.web.rest.util.HeaderUtil;
import it.contrader.autoship.web.rest.util.PaginationUtil;
import it.contrader.autoship.domain.enumeration.CodiceStato;
import it.contrader.autoship.service.dto.OggettoDTO;

/**
 * REST controller for managing Codice.
 */
@RestController
@RequestMapping("/api")
public class CodiceResource {

    private final Logger log = LoggerFactory.getLogger(CodiceResource.class);

    private static final String ENTITY_NAME = "codice";

    private final CodiceService codiceService;
    
    private  final MagazzinoService magazzinoService;
    
    private  final OggettoService oggettoService;
    
    private  final CarrelloService carrelloService;
    
    
    

    public CodiceResource(CodiceService codiceService, MagazzinoService magazzinoService, OggettoService oggettoService, CarrelloService carrelloService) {
        this.codiceService = codiceService;
        this.magazzinoService = magazzinoService;
        this.oggettoService = oggettoService;
        this.carrelloService = carrelloService;
    }

    /**
     * POST  /codices : Create a new codice.
     *
     * @param codiceDTO the codiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new codiceDTO, or with status 400 (Bad Request) if the codice has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/codices")
    @Timed
    public ResponseEntity<CodiceDTO> createCodice(@RequestBody CodiceDTO codiceDTO) throws URISyntaxException {
        log.debug("REST request to save Codice : {}", codiceDTO);
        if (codiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new codice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodiceDTO result = codiceService.save(codiceDTO);
        return ResponseEntity.created(new URI("/api/codices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /codices : Updates an existing codice.
     *
     * @param codiceDTO the codiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated codiceDTO,
     * or with status 400 (Bad Request) if the codiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the codiceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/codices")
    @Timed
    public ResponseEntity<CodiceDTO> updateCodice(@RequestBody CodiceDTO codiceDTO) throws URISyntaxException {
        log.debug("REST request to update Codice : {}", codiceDTO);
        if (codiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CodiceDTO result = codiceService.save(codiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, codiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /codices : get all the codices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of codices in body
     */
    @GetMapping("/codices")
    @Timed
    public ResponseEntity<List<CodiceDTO>> getAllCodices(Pageable pageable) {
        log.debug("REST request to get a page of Codices");
        Page<CodiceDTO> page = codiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/codices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/codicesbyuser/{user}")
    @Timed
    public ResponseEntity<List<CodiceDTO>> getAllCodesByUser(Pageable pageable, @PathVariable Long user) {
        Page<CodiceDTO> page = codiceService.findByUserId(pageable, user);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/codicesbyuser/" + user);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @PutMapping("/confirm")
    @Timed
    public ResponseEntity<CodiceDTO> confirm(@RequestBody CodiceDTO codiceDTO) throws URISyntaxException {
       codiceDTO.setStato(CodiceStato.CONFERMATO);
    	CodiceDTO result = codiceService.save(codiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, codiceDTO.getId().toString()))
            .body(result);
    }
    
    @GetMapping("/getallcodes")
    @Timed
	public List<String> getAllCodes(Pageable pageable){
		List<String> otp = new ArrayList<>();			
		Page<CodiceDTO> page = codiceService.findAll(pageable);	
		for(CodiceDTO c : page) {
			otp.add(c.getOtp());
		}			
		return otp;
	}

    /**
     * GET  /codices/:id : get the "id" codice.
     *
     * @param id the id of the codiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the codiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/codices/{id}")
    @Timed
    public ResponseEntity<CodiceDTO> getCodice(@PathVariable Long id) {
        log.debug("REST request to get Codice : {}", id);
        Optional<CodiceDTO> codiceDTO = codiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(codiceDTO);
    }

    /**
     * DELETE  /codices/:id : delete the "id" codice.
     *
     * @param id the id of the codiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/codices/{id}")
    @Timed
    public ResponseEntity<Void> deleteCodice(@PathVariable Long id) {
    	
    	Optional<CodiceDTO> codice = codiceService.findOne(id);
    	log.debug("REST request to delete Codice : {}", id);
        codiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @DeleteMapping("/deletecode/{id}")
    @Timed
    public ResponseEntity<Void> deleteCodiceCustom(Pageable pageable, @PathVariable Long id) {
    	log.debug("REST request to delete Codice : {}", id);
    	Optional<CodiceDTO> codice = codiceService.findOne(id);
		Page<CarrelloDTO> carrelloList = carrelloService.findByCodiceId(pageable, id);
		for(CarrelloDTO c : carrelloList) {
			carrelloService.delete(c.getId());
		}
		Page<MagazzinoDTO> magazzinoList = magazzinoService.findByCodiceId(pageable, id);
		for(MagazzinoDTO m : magazzinoList){
			m.setCodiceId(null);
			magazzinoService.save(m);
		}		
		codiceService.delete(codice.get().getId());
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
    @GetMapping("/getallconfirmed")
    @Timed
    public ResponseEntity<List<CodiceDTO>> getAllConfirmed (Pageable pageable) {
        Page<CodiceDTO> page = codiceService.findByStato(pageable,CodiceStato.CONFERMATO);        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/getallconfirmed" );
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @PutMapping("/send")
    @Timed
    public ResponseEntity<CodiceDTO> send (Pageable pageable , @RequestBody CodiceDTO codiceDTO) throws URISyntaxException {
    	Page<MagazzinoDTO> magazzinoList = magazzinoService.findByCodiceId(pageable, codiceDTO.getId());
		for(MagazzinoDTO m : magazzinoList){
			Optional<OggettoDTO> oggetto = oggettoService.findOne(m.getOggettoId());
			m.setOggettoId(null);
			m.setCodiceId(null);
			magazzinoService.save(m);
			oggetto.get().setCancellato(true);
			oggetto.get().setCella(false);
		 oggettoService.save(oggetto.get());
		}
		codiceDTO.setCancellato(true);
       codiceDTO.setStato(CodiceStato.SPEDITO);
    	CodiceDTO result = codiceService.save(codiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, codiceDTO.getId().toString()))
            .body(result);
    }
}

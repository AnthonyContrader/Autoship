package it.contrader.autoship.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.contrader.autoship.service.CarrelloService;
import it.contrader.autoship.service.CodiceService;
import it.contrader.autoship.service.MagazzinoService;
import it.contrader.autoship.service.OggettoService;
import it.contrader.autoship.web.rest.errors.BadRequestAlertException;
import it.contrader.autoship.web.rest.util.HeaderUtil;
import it.contrader.autoship.web.rest.util.PaginationUtil;
import it.contrader.autoship.domain.enumeration.CodiceStato;
import it.contrader.autoship.service.dto.OggettoDTO;
import it.contrader.autoship.service.dto.CarrelloDTO;
import it.contrader.autoship.service.dto.CodiceDTO;
import it.contrader.autoship.service.dto.MagazzinoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Magazzino.
 */
@RestController
@RequestMapping("/api")
public class MagazzinoResource {

    private final Logger log = LoggerFactory.getLogger(MagazzinoResource.class);

    private static final String ENTITY_NAME = "magazzino";

    private final MagazzinoService magazzinoService;
    
    private final OggettoService oggettoService;
    
    private final CodiceService codiceService;
    
    private final CarrelloService carrelloService;

    public MagazzinoResource(MagazzinoService magazzinoService, OggettoService oggettoService, CodiceService codiceService, CarrelloService carrelloService) {
        this.magazzinoService = magazzinoService;
        this.oggettoService = oggettoService;
        this.codiceService = codiceService; 
        this.carrelloService = carrelloService; 
    }

    /**
     * POST  /magazzinos : Create a new magazzino.
     *
     * @param magazzinoDTO the magazzinoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new magazzinoDTO, or with status 400 (Bad Request) if the magazzino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/magazzinos")
    @Timed
    public ResponseEntity<MagazzinoDTO> createMagazzino(@Valid @RequestBody MagazzinoDTO magazzinoDTO) throws URISyntaxException {
        log.debug("REST request to save Magazzino : {}", magazzinoDTO);
        if (magazzinoDTO.getId() != null) {
            throw new BadRequestAlertException("A new magazzino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MagazzinoDTO result = magazzinoService.save(magazzinoDTO);
        return ResponseEntity.created(new URI("/api/magazzinos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    @PostMapping("/magazzinosinsert")
    @Timed
    public ResponseEntity<MagazzinoDTO> insertMagazzino(@Valid @RequestBody JsonNode json) throws URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		MagazzinoDTO magazzinoDTO = mapper.convertValue(json.get("magazzino"), MagazzinoDTO.class);
		Long id_oggetto = mapper.convertValue(json.get("oggetto"), Long.class);
	    if (magazzinoDTO.getId() != null) {
            throw new BadRequestAlertException("A new magazzino cannot already have an ID", ENTITY_NAME, "idexists");
        }
	    Optional<OggettoDTO> oggetto;
		if(id_oggetto != null) {
			oggetto = oggettoService.findOne(id_oggetto);
			int dimensione = oggetto.get().getDimensione();
			if(magazzinoDTO.getCapienza() < dimensione) {
				id_oggetto = (long) 0;
			}
		}
		if(id_oggetto == null || id_oggetto == 0) {
			magazzinoDTO.setOggettoId(null);
		}
		else{
			oggetto = oggettoService.findOne(id_oggetto);
			magazzinoDTO.setOggettoId(oggetto.get().getId());
			oggetto.get().setCella(true);
			oggettoService.save(oggetto.get());
		}
		magazzinoDTO.setCancellato(false);
        MagazzinoDTO result = magazzinoService.save(magazzinoDTO);
        return ResponseEntity.created(new URI("/api/magazzinosinsert/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /magazzinos : Updates an existing magazzino.
     *
     * @param magazzinoDTO the magazzinoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated magazzinoDTO,
     * or with status 400 (Bad Request) if the magazzinoDTO is not valid,
     * or with status 500 (Internal Server Error) if the magazzinoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/magazzinos")
    @Timed
    public ResponseEntity<MagazzinoDTO> updateMagazzino(@Valid @RequestBody MagazzinoDTO magazzinoDTO) throws URISyntaxException {
        log.debug("REST request to update Magazzino : {}", magazzinoDTO);
        if (magazzinoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MagazzinoDTO result = magazzinoService.save(magazzinoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, magazzinoDTO.getId().toString()))
            .body(result);
    }
    
    @PutMapping("/magazzinosupdate")
    @Timed
    public ResponseEntity<MagazzinoDTO> updateMagazzinoCustom(@Valid @RequestBody JsonNode json) throws URISyntaxException {
    	ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		MagazzinoDTO magazzinoDTO = mapper.convertValue(json.get("magazzino"), MagazzinoDTO.class);
		Long id_oggetto = mapper.convertValue(json.get("oggetto"), Long.class);
	    Optional<OggettoDTO> oggetto;
	    if(magazzinoDTO.getOggettoId() != null && magazzinoDTO.getOggettoId() != id_oggetto) {
	    	Optional<OggettoDTO> oldOggetto = oggettoService.findOne(magazzinoDTO.getOggettoId());
	    	oldOggetto.get().setCella(false);
	    	oggettoService.save(oldOggetto.get());
	    }
		if(id_oggetto != null) {
			oggetto = oggettoService.findOne(id_oggetto);
			int dimensione = oggetto.get().getDimensione();
			if(magazzinoDTO.getCapienza() < dimensione) {
				id_oggetto = (long) 0;
			}
		}
		if(id_oggetto == null || id_oggetto == 0) {
			magazzinoDTO.setOggettoId(null);
		}
		else{
			oggetto = oggettoService.findOne(id_oggetto);
			magazzinoDTO.setOggettoId(oggetto.get().getId());
			oggetto.get().setCella(true);
			oggettoService.save(oggetto.get());
		}
		magazzinoDTO.setCancellato(false);
        MagazzinoDTO result = magazzinoService.save(magazzinoDTO);
        return ResponseEntity.created(new URI("/api/magazzinosupdate/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /magazzinos : get all the magazzinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of magazzinos in body
     */
    @GetMapping("/magazzinos")
    @Timed
    public ResponseEntity<List<MagazzinoDTO>> getAllMagazzinos(Pageable pageable) {
        log.debug("REST request to get a page of Magazzinos");
        Page<MagazzinoDTO> page = magazzinoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/magazzinos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /magazzinos/:id : get the "id" magazzino.
     *
     * @param id the id of the magazzinoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the magazzinoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/magazzinos/{id}")
    @Timed
    public ResponseEntity<MagazzinoDTO> getMagazzino(@PathVariable Long id) {
        log.debug("REST request to get Magazzino : {}", id);
        Optional<MagazzinoDTO> magazzinoDTO = magazzinoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(magazzinoDTO);
    }

    /**
     * DELETE  /magazzinos/:id : delete the "id" magazzino.
     *
     * @param id the id of the magazzinoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/magazzinos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMagazzino(@PathVariable Long id) {
        log.debug("REST request to delete Magazzino : {}", id);
        magazzinoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    

    @PutMapping("/magazzinodelete")
    @Timed
    public ResponseEntity<MagazzinoDTO> deleteOggettoCustom(@Valid @RequestBody MagazzinoDTO magazzinoDTO) throws URISyntaxException {
        log.debug("REST request to update Magazzino : {}", magazzinoDTO);
        magazzinoDTO.setCancellato(true);
        MagazzinoDTO result = magazzinoService.save(magazzinoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, magazzinoDTO.getId().toString()))
            .body(result);
    }
    
    @PutMapping("/magazzinoreinsert")
    @Timed
    public ResponseEntity<MagazzinoDTO> reinsertOggettoCustom(@Valid @RequestBody MagazzinoDTO magazzinoDTO) throws URISyntaxException {
        log.debug("REST request to update Magazzino : {}", magazzinoDTO);
        magazzinoDTO.setCancellato(false);
        MagazzinoDTO result = magazzinoService.save(magazzinoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, magazzinoDTO.getId().toString()))
            .body(result);
    }
    
    @PutMapping("/setcodice")
    @Timed
	public ResponseEntity<MagazzinoDTO> setCodice(@RequestBody JsonNode json) throws URISyntaxException{
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			MagazzinoDTO magazzino = mapper.convertValue(json.get("magazzino"), MagazzinoDTO.class);
			String otp = mapper.convertValue(json.get("otp"), String.class);
			Long id_user = mapper.convertValue(json.get("user"), Long.class);
			Optional<OggettoDTO> oggetto = oggettoService.findOne(magazzino.getOggettoId());
			Optional<CodiceDTO> codice = codiceService.findByOtp(otp);
			CarrelloDTO carrello = new CarrelloDTO();
			carrello.setOggettoId(oggetto.get().getId());
			if(!(codice.isPresent())) {
				CodiceDTO newCodice = new CodiceDTO();
				newCodice.setOtp(otp);
				newCodice.setUserid(id_user);
				newCodice.setStato(CodiceStato.ATTESA);
				newCodice.setCancellato(false);
				newCodice = codiceService.save(newCodice);
				magazzino.setCodiceId(newCodice.getId());
				carrello.setCodiceId(newCodice.getId());
			}
			else {
				magazzino.setCodiceId(codice.get().getId());
				carrello.setCodiceId(codice.get().getId());
			}
			magazzinoService.save(magazzino);			
			carrelloService.save(carrello);
			return ResponseEntity.ok()
					.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, magazzino.getId().toString()))
		            .body(magazzino);
	}
    
    @GetMapping("/getobjectincell")
    @Timed
    public ResponseEntity<List<MagazzinoDTO>> getObjectInCell(Pageable pageable) {
        log.debug("REST request to get a page of Magazzinos");
        Page<MagazzinoDTO> page = magazzinoService.findByOggettoIdNotNullAndCodiceNull(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/magazzinos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}

package it.contrader.autoship.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.contrader.autoship.service.CarrelloService;
import it.contrader.autoship.web.rest.errors.BadRequestAlertException;
import it.contrader.autoship.web.rest.util.HeaderUtil;
import it.contrader.autoship.web.rest.util.PaginationUtil;
import it.contrader.autoship.service.dto.CarrelloDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Carrello.
 */
@RestController
@RequestMapping("/api")
public class CarrelloResource {

    private final Logger log = LoggerFactory.getLogger(CarrelloResource.class);

    private static final String ENTITY_NAME = "carrello";

    private final CarrelloService carrelloService;

    public CarrelloResource(CarrelloService carrelloService) {
        this.carrelloService = carrelloService;
    }

    /**
     * POST  /carrellos : Create a new carrello.
     *
     * @param carrelloDTO the carrelloDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new carrelloDTO, or with status 400 (Bad Request) if the carrello has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/carrellos")
    @Timed
    public ResponseEntity<CarrelloDTO> createCarrello(@RequestBody CarrelloDTO carrelloDTO) throws URISyntaxException {
        log.debug("REST request to save Carrello : {}", carrelloDTO);
        if (carrelloDTO.getId() != null) {
            throw new BadRequestAlertException("A new carrello cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarrelloDTO result = carrelloService.save(carrelloDTO);
        return ResponseEntity.created(new URI("/api/carrellos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /carrellos : Updates an existing carrello.
     *
     * @param carrelloDTO the carrelloDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated carrelloDTO,
     * or with status 400 (Bad Request) if the carrelloDTO is not valid,
     * or with status 500 (Internal Server Error) if the carrelloDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/carrellos")
    @Timed
    public ResponseEntity<CarrelloDTO> updateCarrello(@RequestBody CarrelloDTO carrelloDTO) throws URISyntaxException {
        log.debug("REST request to update Carrello : {}", carrelloDTO);
        if (carrelloDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarrelloDTO result = carrelloService.save(carrelloDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, carrelloDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /carrellos : get all the carrellos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of carrellos in body
     */
    @GetMapping("/carrellos")
    @Timed
    public ResponseEntity<List<CarrelloDTO>> getAllCarrellos(Pageable pageable) {
        log.debug("REST request to get a page of Carrellos");
        Page<CarrelloDTO> page = carrelloService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/carrellos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /carrellos/:id : get the "id" carrello.
     *
     * @param id the id of the carrelloDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the carrelloDTO, or with status 404 (Not Found)
     */
    @GetMapping("/carrellos/{id}")
    @Timed
    public ResponseEntity<CarrelloDTO> getCarrello(@PathVariable Long id) {
        log.debug("REST request to get Carrello : {}", id);
        Optional<CarrelloDTO> carrelloDTO = carrelloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carrelloDTO);
    }

    /**
     * DELETE  /carrellos/:id : delete the "id" carrello.
     *
     * @param id the id of the carrelloDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/carrellos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCarrello(@PathVariable Long id) {
        log.debug("REST request to delete Carrello : {}", id);
        carrelloService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

package it.contrader.autoship.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.contrader.autoship.service.MagazzinoService;
import it.contrader.autoship.web.rest.errors.BadRequestAlertException;
import it.contrader.autoship.web.rest.util.HeaderUtil;
import it.contrader.autoship.web.rest.util.PaginationUtil;
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

    public MagazzinoResource(MagazzinoService magazzinoService) {
        this.magazzinoService = magazzinoService;
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
}

package it.contrader.autoship.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.contrader.autoship.service.OggettoService;
import it.contrader.autoship.web.rest.errors.BadRequestAlertException;
import it.contrader.autoship.web.rest.util.HeaderUtil;
import it.contrader.autoship.web.rest.util.PaginationUtil;
import it.contrader.autoship.service.dto.OggettoDTO;
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
 * REST controller for managing Oggetto.
 */
@RestController
@RequestMapping("/api")
public class OggettoResource {

    private final Logger log = LoggerFactory.getLogger(OggettoResource.class);

    private static final String ENTITY_NAME = "oggetto";

    private final OggettoService oggettoService;

    public OggettoResource(OggettoService oggettoService) {
        this.oggettoService = oggettoService;
    }

    /**
     * POST  /oggettos : Create a new oggetto.
     *
     * @param oggettoDTO the oggettoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new oggettoDTO, or with status 400 (Bad Request) if the oggetto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/oggettos")
    @Timed
    public ResponseEntity<OggettoDTO> createOggetto(@Valid @RequestBody OggettoDTO oggettoDTO) throws URISyntaxException {
        log.debug("REST request to save Oggetto : {}", oggettoDTO);
        if (oggettoDTO.getId() != null) {
            throw new BadRequestAlertException("A new oggetto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OggettoDTO result = oggettoService.save(oggettoDTO);
        return ResponseEntity.created(new URI("/api/oggettos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /oggettos : Updates an existing oggetto.
     *
     * @param oggettoDTO the oggettoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated oggettoDTO,
     * or with status 400 (Bad Request) if the oggettoDTO is not valid,
     * or with status 500 (Internal Server Error) if the oggettoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/oggettos")
    @Timed
    public ResponseEntity<OggettoDTO> updateOggetto(@Valid @RequestBody OggettoDTO oggettoDTO) throws URISyntaxException {
        log.debug("REST request to update Oggetto : {}", oggettoDTO);
        if (oggettoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OggettoDTO result = oggettoService.save(oggettoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, oggettoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /oggettos : get all the oggettos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of oggettos in body
     */
    @GetMapping("/oggettos")
    @Timed
    public ResponseEntity<List<OggettoDTO>> getAllOggettos(Pageable pageable) {
        log.debug("REST request to get a page of Oggettos");
        Page<OggettoDTO> page = oggettoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/oggettos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /oggettos/:id : get the "id" oggetto.
     *
     * @param id the id of the oggettoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the oggettoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/oggettos/{id}")
    @Timed
    public ResponseEntity<OggettoDTO> getOggetto(@PathVariable Long id) {
        log.debug("REST request to get Oggetto : {}", id);
        Optional<OggettoDTO> oggettoDTO = oggettoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oggettoDTO);
    }

    /**
     * DELETE  /oggettos/:id : delete the "id" oggetto.
     *
     * @param id the id of the oggettoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/oggettos/{id}")
    @Timed
    public ResponseEntity<Void> deleteOggetto(@PathVariable Long id) {
        log.debug("REST request to delete Oggetto : {}", id);
        oggettoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

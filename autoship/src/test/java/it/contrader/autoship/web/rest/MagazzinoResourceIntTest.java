package it.contrader.autoship.web.rest;

import it.contrader.autoship.AutoshipApp;

import it.contrader.autoship.domain.Magazzino;
import it.contrader.autoship.repository.MagazzinoRepository;
import it.contrader.autoship.service.CarrelloService;
import it.contrader.autoship.service.CodiceService;
import it.contrader.autoship.service.MagazzinoService;
import it.contrader.autoship.service.OggettoService;
import it.contrader.autoship.service.dto.MagazzinoDTO;
import it.contrader.autoship.service.mapper.MagazzinoMapper;
import it.contrader.autoship.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static it.contrader.autoship.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MagazzinoResource REST controller.
 *
 * @see MagazzinoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoshipApp.class)
public class MagazzinoResourceIntTest {

    private static final Integer DEFAULT_CAPIENZA = 1;
    private static final Integer UPDATED_CAPIENZA = 2;

    private static final Boolean DEFAULT_CANCELLATO = false;
    private static final Boolean UPDATED_CANCELLATO = true;

    @Autowired
    private MagazzinoRepository magazzinoRepository;


    @Autowired
    private MagazzinoMapper magazzinoMapper;
    

    @Autowired
    private MagazzinoService magazzinoService;
    
    @Autowired
    private OggettoService oggettoService;
    
    @Autowired
    private CodiceService codiceService;
    
    @Autowired
    private CarrelloService carrelloService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMagazzinoMockMvc;

    private Magazzino magazzino;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MagazzinoResource magazzinoResource = new MagazzinoResource(magazzinoService, oggettoService, codiceService, carrelloService);
        this.restMagazzinoMockMvc = MockMvcBuilders.standaloneSetup(magazzinoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Magazzino createEntity(EntityManager em) {
        Magazzino magazzino = new Magazzino()
            .capienza(DEFAULT_CAPIENZA)
            .cancellato(DEFAULT_CANCELLATO);
        return magazzino;
    }

    @Before
    public void initTest() {
        magazzino = createEntity(em);
    }

    @Test
    @Transactional
    public void createMagazzino() throws Exception {
        int databaseSizeBeforeCreate = magazzinoRepository.findAll().size();

        // Create the Magazzino
        MagazzinoDTO magazzinoDTO = magazzinoMapper.toDto(magazzino);
        restMagazzinoMockMvc.perform(post("/api/magazzinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magazzinoDTO)))
            .andExpect(status().isCreated());

        // Validate the Magazzino in the database
        List<Magazzino> magazzinoList = magazzinoRepository.findAll();
        assertThat(magazzinoList).hasSize(databaseSizeBeforeCreate + 1);
        Magazzino testMagazzino = magazzinoList.get(magazzinoList.size() - 1);
        assertThat(testMagazzino.getCapienza()).isEqualTo(DEFAULT_CAPIENZA);
        assertThat(testMagazzino.isCancellato()).isEqualTo(DEFAULT_CANCELLATO);
    }

    @Test
    @Transactional
    public void createMagazzinoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = magazzinoRepository.findAll().size();

        // Create the Magazzino with an existing ID
        magazzino.setId(1L);
        MagazzinoDTO magazzinoDTO = magazzinoMapper.toDto(magazzino);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMagazzinoMockMvc.perform(post("/api/magazzinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magazzinoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Magazzino in the database
        List<Magazzino> magazzinoList = magazzinoRepository.findAll();
        assertThat(magazzinoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCapienzaIsRequired() throws Exception {
        int databaseSizeBeforeTest = magazzinoRepository.findAll().size();
        // set the field null
        magazzino.setCapienza(null);

        // Create the Magazzino, which fails.
        MagazzinoDTO magazzinoDTO = magazzinoMapper.toDto(magazzino);

        restMagazzinoMockMvc.perform(post("/api/magazzinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magazzinoDTO)))
            .andExpect(status().isBadRequest());

        List<Magazzino> magazzinoList = magazzinoRepository.findAll();
        assertThat(magazzinoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMagazzinos() throws Exception {
        // Initialize the database
        magazzinoRepository.saveAndFlush(magazzino);

        // Get all the magazzinoList
        restMagazzinoMockMvc.perform(get("/api/magazzinos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(magazzino.getId().intValue())))
            .andExpect(jsonPath("$.[*].capienza").value(hasItem(DEFAULT_CAPIENZA)))
            .andExpect(jsonPath("$.[*].cancellato").value(hasItem(DEFAULT_CANCELLATO.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getMagazzino() throws Exception {
        // Initialize the database
        magazzinoRepository.saveAndFlush(magazzino);

        // Get the magazzino
        restMagazzinoMockMvc.perform(get("/api/magazzinos/{id}", magazzino.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(magazzino.getId().intValue()))
            .andExpect(jsonPath("$.capienza").value(DEFAULT_CAPIENZA))
            .andExpect(jsonPath("$.cancellato").value(DEFAULT_CANCELLATO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMagazzino() throws Exception {
        // Get the magazzino
        restMagazzinoMockMvc.perform(get("/api/magazzinos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMagazzino() throws Exception {
        // Initialize the database
        magazzinoRepository.saveAndFlush(magazzino);

        int databaseSizeBeforeUpdate = magazzinoRepository.findAll().size();

        // Update the magazzino
        Magazzino updatedMagazzino = magazzinoRepository.findById(magazzino.getId()).get();
        // Disconnect from session so that the updates on updatedMagazzino are not directly saved in db
        em.detach(updatedMagazzino);
        updatedMagazzino
            .capienza(UPDATED_CAPIENZA)
            .cancellato(UPDATED_CANCELLATO);
        MagazzinoDTO magazzinoDTO = magazzinoMapper.toDto(updatedMagazzino);

        restMagazzinoMockMvc.perform(put("/api/magazzinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magazzinoDTO)))
            .andExpect(status().isOk());

        // Validate the Magazzino in the database
        List<Magazzino> magazzinoList = magazzinoRepository.findAll();
        assertThat(magazzinoList).hasSize(databaseSizeBeforeUpdate);
        Magazzino testMagazzino = magazzinoList.get(magazzinoList.size() - 1);
        assertThat(testMagazzino.getCapienza()).isEqualTo(UPDATED_CAPIENZA);
        assertThat(testMagazzino.isCancellato()).isEqualTo(UPDATED_CANCELLATO);
    }

    @Test
    @Transactional
    public void updateNonExistingMagazzino() throws Exception {
        int databaseSizeBeforeUpdate = magazzinoRepository.findAll().size();

        // Create the Magazzino
        MagazzinoDTO magazzinoDTO = magazzinoMapper.toDto(magazzino);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restMagazzinoMockMvc.perform(put("/api/magazzinos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(magazzinoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Magazzino in the database
        List<Magazzino> magazzinoList = magazzinoRepository.findAll();
        assertThat(magazzinoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMagazzino() throws Exception {
        // Initialize the database
        magazzinoRepository.saveAndFlush(magazzino);

        int databaseSizeBeforeDelete = magazzinoRepository.findAll().size();

        // Get the magazzino
        restMagazzinoMockMvc.perform(delete("/api/magazzinos/{id}", magazzino.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Magazzino> magazzinoList = magazzinoRepository.findAll();
        assertThat(magazzinoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Magazzino.class);
        Magazzino magazzino1 = new Magazzino();
        magazzino1.setId(1L);
        Magazzino magazzino2 = new Magazzino();
        magazzino2.setId(magazzino1.getId());
        assertThat(magazzino1).isEqualTo(magazzino2);
        magazzino2.setId(2L);
        assertThat(magazzino1).isNotEqualTo(magazzino2);
        magazzino1.setId(null);
        assertThat(magazzino1).isNotEqualTo(magazzino2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MagazzinoDTO.class);
        MagazzinoDTO magazzinoDTO1 = new MagazzinoDTO();
        magazzinoDTO1.setId(1L);
        MagazzinoDTO magazzinoDTO2 = new MagazzinoDTO();
        assertThat(magazzinoDTO1).isNotEqualTo(magazzinoDTO2);
        magazzinoDTO2.setId(magazzinoDTO1.getId());
        assertThat(magazzinoDTO1).isEqualTo(magazzinoDTO2);
        magazzinoDTO2.setId(2L);
        assertThat(magazzinoDTO1).isNotEqualTo(magazzinoDTO2);
        magazzinoDTO1.setId(null);
        assertThat(magazzinoDTO1).isNotEqualTo(magazzinoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(magazzinoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(magazzinoMapper.fromId(null)).isNull();
    }
}

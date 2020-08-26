package it.contrader.autoship.web.rest;

import it.contrader.autoship.AutoshipApp;

import it.contrader.autoship.domain.Oggetto;
import it.contrader.autoship.repository.OggettoRepository;
import it.contrader.autoship.service.OggettoService;
import it.contrader.autoship.service.dto.OggettoDTO;
import it.contrader.autoship.service.mapper.OggettoMapper;
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
 * Test class for the OggettoResource REST controller.
 *
 * @see OggettoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoshipApp.class)
public class OggettoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIMENSIONE = 1;
    private static final Integer UPDATED_DIMENSIONE = 2;

    private static final Boolean DEFAULT_CANCELLATO = false;
    private static final Boolean UPDATED_CANCELLATO = true;

    @Autowired
    private OggettoRepository oggettoRepository;


    @Autowired
    private OggettoMapper oggettoMapper;
    

    @Autowired
    private OggettoService oggettoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOggettoMockMvc;

    private Oggetto oggetto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OggettoResource oggettoResource = new OggettoResource(oggettoService);
        this.restOggettoMockMvc = MockMvcBuilders.standaloneSetup(oggettoResource)
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
    public static Oggetto createEntity(EntityManager em) {
        Oggetto oggetto = new Oggetto()
            .nome(DEFAULT_NOME)
            .dimensione(DEFAULT_DIMENSIONE)
            .cancellato(DEFAULT_CANCELLATO);
        return oggetto;
    }

    @Before
    public void initTest() {
        oggetto = createEntity(em);
    }

    @Test
    @Transactional
    public void createOggetto() throws Exception {
        int databaseSizeBeforeCreate = oggettoRepository.findAll().size();

        // Create the Oggetto
        OggettoDTO oggettoDTO = oggettoMapper.toDto(oggetto);
        restOggettoMockMvc.perform(post("/api/oggettos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oggettoDTO)))
            .andExpect(status().isCreated());

        // Validate the Oggetto in the database
        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeCreate + 1);
        Oggetto testOggetto = oggettoList.get(oggettoList.size() - 1);
        assertThat(testOggetto.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testOggetto.getDimensione()).isEqualTo(DEFAULT_DIMENSIONE);
        assertThat(testOggetto.isCancellato()).isEqualTo(DEFAULT_CANCELLATO);
    }

    @Test
    @Transactional
    public void createOggettoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oggettoRepository.findAll().size();

        // Create the Oggetto with an existing ID
        oggetto.setId(1L);
        OggettoDTO oggettoDTO = oggettoMapper.toDto(oggetto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOggettoMockMvc.perform(post("/api/oggettos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oggettoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Oggetto in the database
        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = oggettoRepository.findAll().size();
        // set the field null
        oggetto.setNome(null);

        // Create the Oggetto, which fails.
        OggettoDTO oggettoDTO = oggettoMapper.toDto(oggetto);

        restOggettoMockMvc.perform(post("/api/oggettos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oggettoDTO)))
            .andExpect(status().isBadRequest());

        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDimensioneIsRequired() throws Exception {
        int databaseSizeBeforeTest = oggettoRepository.findAll().size();
        // set the field null
        oggetto.setDimensione(null);

        // Create the Oggetto, which fails.
        OggettoDTO oggettoDTO = oggettoMapper.toDto(oggetto);

        restOggettoMockMvc.perform(post("/api/oggettos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oggettoDTO)))
            .andExpect(status().isBadRequest());

        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOggettos() throws Exception {
        // Initialize the database
        oggettoRepository.saveAndFlush(oggetto);

        // Get all the oggettoList
        restOggettoMockMvc.perform(get("/api/oggettos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oggetto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].dimensione").value(hasItem(DEFAULT_DIMENSIONE)))
            .andExpect(jsonPath("$.[*].cancellato").value(hasItem(DEFAULT_CANCELLATO.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getOggetto() throws Exception {
        // Initialize the database
        oggettoRepository.saveAndFlush(oggetto);

        // Get the oggetto
        restOggettoMockMvc.perform(get("/api/oggettos/{id}", oggetto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(oggetto.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.dimensione").value(DEFAULT_DIMENSIONE))
            .andExpect(jsonPath("$.cancellato").value(DEFAULT_CANCELLATO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingOggetto() throws Exception {
        // Get the oggetto
        restOggettoMockMvc.perform(get("/api/oggettos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOggetto() throws Exception {
        // Initialize the database
        oggettoRepository.saveAndFlush(oggetto);

        int databaseSizeBeforeUpdate = oggettoRepository.findAll().size();

        // Update the oggetto
        Oggetto updatedOggetto = oggettoRepository.findById(oggetto.getId()).get();
        // Disconnect from session so that the updates on updatedOggetto are not directly saved in db
        em.detach(updatedOggetto);
        updatedOggetto
            .nome(UPDATED_NOME)
            .dimensione(UPDATED_DIMENSIONE)
            .cancellato(UPDATED_CANCELLATO);
        OggettoDTO oggettoDTO = oggettoMapper.toDto(updatedOggetto);

        restOggettoMockMvc.perform(put("/api/oggettos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oggettoDTO)))
            .andExpect(status().isOk());

        // Validate the Oggetto in the database
        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeUpdate);
        Oggetto testOggetto = oggettoList.get(oggettoList.size() - 1);
        assertThat(testOggetto.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testOggetto.getDimensione()).isEqualTo(UPDATED_DIMENSIONE);
        assertThat(testOggetto.isCancellato()).isEqualTo(UPDATED_CANCELLATO);
    }

    @Test
    @Transactional
    public void updateNonExistingOggetto() throws Exception {
        int databaseSizeBeforeUpdate = oggettoRepository.findAll().size();

        // Create the Oggetto
        OggettoDTO oggettoDTO = oggettoMapper.toDto(oggetto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restOggettoMockMvc.perform(put("/api/oggettos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(oggettoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Oggetto in the database
        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOggetto() throws Exception {
        // Initialize the database
        oggettoRepository.saveAndFlush(oggetto);

        int databaseSizeBeforeDelete = oggettoRepository.findAll().size();

        // Get the oggetto
        restOggettoMockMvc.perform(delete("/api/oggettos/{id}", oggetto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Oggetto> oggettoList = oggettoRepository.findAll();
        assertThat(oggettoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Oggetto.class);
        Oggetto oggetto1 = new Oggetto();
        oggetto1.setId(1L);
        Oggetto oggetto2 = new Oggetto();
        oggetto2.setId(oggetto1.getId());
        assertThat(oggetto1).isEqualTo(oggetto2);
        oggetto2.setId(2L);
        assertThat(oggetto1).isNotEqualTo(oggetto2);
        oggetto1.setId(null);
        assertThat(oggetto1).isNotEqualTo(oggetto2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OggettoDTO.class);
        OggettoDTO oggettoDTO1 = new OggettoDTO();
        oggettoDTO1.setId(1L);
        OggettoDTO oggettoDTO2 = new OggettoDTO();
        assertThat(oggettoDTO1).isNotEqualTo(oggettoDTO2);
        oggettoDTO2.setId(oggettoDTO1.getId());
        assertThat(oggettoDTO1).isEqualTo(oggettoDTO2);
        oggettoDTO2.setId(2L);
        assertThat(oggettoDTO1).isNotEqualTo(oggettoDTO2);
        oggettoDTO1.setId(null);
        assertThat(oggettoDTO1).isNotEqualTo(oggettoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(oggettoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(oggettoMapper.fromId(null)).isNull();
    }
}

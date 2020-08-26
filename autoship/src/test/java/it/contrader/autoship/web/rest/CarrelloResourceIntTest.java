package it.contrader.autoship.web.rest;

import it.contrader.autoship.AutoshipApp;

import it.contrader.autoship.domain.Carrello;
import it.contrader.autoship.repository.CarrelloRepository;
import it.contrader.autoship.service.CarrelloService;
import it.contrader.autoship.service.dto.CarrelloDTO;
import it.contrader.autoship.service.mapper.CarrelloMapper;
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
 * Test class for the CarrelloResource REST controller.
 *
 * @see CarrelloResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoshipApp.class)
public class CarrelloResourceIntTest {

    private static final Boolean DEFAULT_CANCELLATO = false;
    private static final Boolean UPDATED_CANCELLATO = true;

    @Autowired
    private CarrelloRepository carrelloRepository;


    @Autowired
    private CarrelloMapper carrelloMapper;
    

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

    private MockMvc restCarrelloMockMvc;

    private Carrello carrello;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CarrelloResource carrelloResource = new CarrelloResource(carrelloService);
        this.restCarrelloMockMvc = MockMvcBuilders.standaloneSetup(carrelloResource)
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
    public static Carrello createEntity(EntityManager em) {
        Carrello carrello = new Carrello()
            .cancellato(DEFAULT_CANCELLATO);
        return carrello;
    }

    @Before
    public void initTest() {
        carrello = createEntity(em);
    }

    @Test
    @Transactional
    public void createCarrello() throws Exception {
        int databaseSizeBeforeCreate = carrelloRepository.findAll().size();

        // Create the Carrello
        CarrelloDTO carrelloDTO = carrelloMapper.toDto(carrello);
        restCarrelloMockMvc.perform(post("/api/carrellos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carrelloDTO)))
            .andExpect(status().isCreated());

        // Validate the Carrello in the database
        List<Carrello> carrelloList = carrelloRepository.findAll();
        assertThat(carrelloList).hasSize(databaseSizeBeforeCreate + 1);
        Carrello testCarrello = carrelloList.get(carrelloList.size() - 1);
        assertThat(testCarrello.isCancellato()).isEqualTo(DEFAULT_CANCELLATO);
    }

    @Test
    @Transactional
    public void createCarrelloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carrelloRepository.findAll().size();

        // Create the Carrello with an existing ID
        carrello.setId(1L);
        CarrelloDTO carrelloDTO = carrelloMapper.toDto(carrello);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarrelloMockMvc.perform(post("/api/carrellos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carrelloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Carrello in the database
        List<Carrello> carrelloList = carrelloRepository.findAll();
        assertThat(carrelloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCarrellos() throws Exception {
        // Initialize the database
        carrelloRepository.saveAndFlush(carrello);

        // Get all the carrelloList
        restCarrelloMockMvc.perform(get("/api/carrellos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carrello.getId().intValue())))
            .andExpect(jsonPath("$.[*].cancellato").value(hasItem(DEFAULT_CANCELLATO.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getCarrello() throws Exception {
        // Initialize the database
        carrelloRepository.saveAndFlush(carrello);

        // Get the carrello
        restCarrelloMockMvc.perform(get("/api/carrellos/{id}", carrello.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(carrello.getId().intValue()))
            .andExpect(jsonPath("$.cancellato").value(DEFAULT_CANCELLATO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCarrello() throws Exception {
        // Get the carrello
        restCarrelloMockMvc.perform(get("/api/carrellos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCarrello() throws Exception {
        // Initialize the database
        carrelloRepository.saveAndFlush(carrello);

        int databaseSizeBeforeUpdate = carrelloRepository.findAll().size();

        // Update the carrello
        Carrello updatedCarrello = carrelloRepository.findById(carrello.getId()).get();
        // Disconnect from session so that the updates on updatedCarrello are not directly saved in db
        em.detach(updatedCarrello);
        updatedCarrello
            .cancellato(UPDATED_CANCELLATO);
        CarrelloDTO carrelloDTO = carrelloMapper.toDto(updatedCarrello);

        restCarrelloMockMvc.perform(put("/api/carrellos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carrelloDTO)))
            .andExpect(status().isOk());

        // Validate the Carrello in the database
        List<Carrello> carrelloList = carrelloRepository.findAll();
        assertThat(carrelloList).hasSize(databaseSizeBeforeUpdate);
        Carrello testCarrello = carrelloList.get(carrelloList.size() - 1);
        assertThat(testCarrello.isCancellato()).isEqualTo(UPDATED_CANCELLATO);
    }

    @Test
    @Transactional
    public void updateNonExistingCarrello() throws Exception {
        int databaseSizeBeforeUpdate = carrelloRepository.findAll().size();

        // Create the Carrello
        CarrelloDTO carrelloDTO = carrelloMapper.toDto(carrello);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCarrelloMockMvc.perform(put("/api/carrellos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carrelloDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Carrello in the database
        List<Carrello> carrelloList = carrelloRepository.findAll();
        assertThat(carrelloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCarrello() throws Exception {
        // Initialize the database
        carrelloRepository.saveAndFlush(carrello);

        int databaseSizeBeforeDelete = carrelloRepository.findAll().size();

        // Get the carrello
        restCarrelloMockMvc.perform(delete("/api/carrellos/{id}", carrello.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Carrello> carrelloList = carrelloRepository.findAll();
        assertThat(carrelloList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Carrello.class);
        Carrello carrello1 = new Carrello();
        carrello1.setId(1L);
        Carrello carrello2 = new Carrello();
        carrello2.setId(carrello1.getId());
        assertThat(carrello1).isEqualTo(carrello2);
        carrello2.setId(2L);
        assertThat(carrello1).isNotEqualTo(carrello2);
        carrello1.setId(null);
        assertThat(carrello1).isNotEqualTo(carrello2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarrelloDTO.class);
        CarrelloDTO carrelloDTO1 = new CarrelloDTO();
        carrelloDTO1.setId(1L);
        CarrelloDTO carrelloDTO2 = new CarrelloDTO();
        assertThat(carrelloDTO1).isNotEqualTo(carrelloDTO2);
        carrelloDTO2.setId(carrelloDTO1.getId());
        assertThat(carrelloDTO1).isEqualTo(carrelloDTO2);
        carrelloDTO2.setId(2L);
        assertThat(carrelloDTO1).isNotEqualTo(carrelloDTO2);
        carrelloDTO1.setId(null);
        assertThat(carrelloDTO1).isNotEqualTo(carrelloDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(carrelloMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(carrelloMapper.fromId(null)).isNull();
    }
}

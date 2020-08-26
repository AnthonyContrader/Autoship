package it.contrader.autoship.web.rest;

import it.contrader.autoship.AutoshipApp;

import it.contrader.autoship.domain.Codice;
import it.contrader.autoship.repository.CodiceRepository;
import it.contrader.autoship.service.CodiceService;
import it.contrader.autoship.service.dto.CodiceDTO;
import it.contrader.autoship.service.mapper.CodiceMapper;
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

import it.contrader.autoship.domain.enumeration.CodiceStato;
/**
 * Test class for the CodiceResource REST controller.
 *
 * @see CodiceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoshipApp.class)
public class CodiceResourceIntTest {

    private static final String DEFAULT_OTP = "AAAAAAAAAA";
    private static final String UPDATED_OTP = "BBBBBBBBBB";

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final CodiceStato DEFAULT_STATO = CodiceStato.ATTESA;
    private static final CodiceStato UPDATED_STATO = CodiceStato.CONFERMATO;

    private static final Boolean DEFAULT_CANCELLATO = false;
    private static final Boolean UPDATED_CANCELLATO = true;

    @Autowired
    private CodiceRepository codiceRepository;


    @Autowired
    private CodiceMapper codiceMapper;
    

    @Autowired
    private CodiceService codiceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCodiceMockMvc;

    private Codice codice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CodiceResource codiceResource = new CodiceResource(codiceService);
        this.restCodiceMockMvc = MockMvcBuilders.standaloneSetup(codiceResource)
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
    public static Codice createEntity(EntityManager em) {
        Codice codice = new Codice()
            .otp(DEFAULT_OTP)
            .user_id(DEFAULT_USER_ID)
            .stato(DEFAULT_STATO)
            .cancellato(DEFAULT_CANCELLATO);
        return codice;
    }

    @Before
    public void initTest() {
        codice = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodice() throws Exception {
        int databaseSizeBeforeCreate = codiceRepository.findAll().size();

        // Create the Codice
        CodiceDTO codiceDTO = codiceMapper.toDto(codice);
        restCodiceMockMvc.perform(post("/api/codices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceDTO)))
            .andExpect(status().isCreated());

        // Validate the Codice in the database
        List<Codice> codiceList = codiceRepository.findAll();
        assertThat(codiceList).hasSize(databaseSizeBeforeCreate + 1);
        Codice testCodice = codiceList.get(codiceList.size() - 1);
        assertThat(testCodice.getOtp()).isEqualTo(DEFAULT_OTP);
        assertThat(testCodice.getUser_id()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCodice.getStato()).isEqualTo(DEFAULT_STATO);
        assertThat(testCodice.isCancellato()).isEqualTo(DEFAULT_CANCELLATO);
    }

    @Test
    @Transactional
    public void createCodiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codiceRepository.findAll().size();

        // Create the Codice with an existing ID
        codice.setId(1L);
        CodiceDTO codiceDTO = codiceMapper.toDto(codice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodiceMockMvc.perform(post("/api/codices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Codice in the database
        List<Codice> codiceList = codiceRepository.findAll();
        assertThat(codiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCodices() throws Exception {
        // Initialize the database
        codiceRepository.saveAndFlush(codice);

        // Get all the codiceList
        restCodiceMockMvc.perform(get("/api/codices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codice.getId().intValue())))
            .andExpect(jsonPath("$.[*].otp").value(hasItem(DEFAULT_OTP.toString())))
            .andExpect(jsonPath("$.[*].user_id").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.toString())))
            .andExpect(jsonPath("$.[*].cancellato").value(hasItem(DEFAULT_CANCELLATO.booleanValue())));
    }
    

    @Test
    @Transactional
    public void getCodice() throws Exception {
        // Initialize the database
        codiceRepository.saveAndFlush(codice);

        // Get the codice
        restCodiceMockMvc.perform(get("/api/codices/{id}", codice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(codice.getId().intValue()))
            .andExpect(jsonPath("$.otp").value(DEFAULT_OTP.toString()))
            .andExpect(jsonPath("$.user_id").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO.toString()))
            .andExpect(jsonPath("$.cancellato").value(DEFAULT_CANCELLATO.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCodice() throws Exception {
        // Get the codice
        restCodiceMockMvc.perform(get("/api/codices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodice() throws Exception {
        // Initialize the database
        codiceRepository.saveAndFlush(codice);

        int databaseSizeBeforeUpdate = codiceRepository.findAll().size();

        // Update the codice
        Codice updatedCodice = codiceRepository.findById(codice.getId()).get();
        // Disconnect from session so that the updates on updatedCodice are not directly saved in db
        em.detach(updatedCodice);
        updatedCodice
            .otp(UPDATED_OTP)
            .user_id(UPDATED_USER_ID)
            .stato(UPDATED_STATO)
            .cancellato(UPDATED_CANCELLATO);
        CodiceDTO codiceDTO = codiceMapper.toDto(updatedCodice);

        restCodiceMockMvc.perform(put("/api/codices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceDTO)))
            .andExpect(status().isOk());

        // Validate the Codice in the database
        List<Codice> codiceList = codiceRepository.findAll();
        assertThat(codiceList).hasSize(databaseSizeBeforeUpdate);
        Codice testCodice = codiceList.get(codiceList.size() - 1);
        assertThat(testCodice.getOtp()).isEqualTo(UPDATED_OTP);
        assertThat(testCodice.getUser_id()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCodice.getStato()).isEqualTo(UPDATED_STATO);
        assertThat(testCodice.isCancellato()).isEqualTo(UPDATED_CANCELLATO);
    }

    @Test
    @Transactional
    public void updateNonExistingCodice() throws Exception {
        int databaseSizeBeforeUpdate = codiceRepository.findAll().size();

        // Create the Codice
        CodiceDTO codiceDTO = codiceMapper.toDto(codice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restCodiceMockMvc.perform(put("/api/codices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Codice in the database
        List<Codice> codiceList = codiceRepository.findAll();
        assertThat(codiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodice() throws Exception {
        // Initialize the database
        codiceRepository.saveAndFlush(codice);

        int databaseSizeBeforeDelete = codiceRepository.findAll().size();

        // Get the codice
        restCodiceMockMvc.perform(delete("/api/codices/{id}", codice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Codice> codiceList = codiceRepository.findAll();
        assertThat(codiceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Codice.class);
        Codice codice1 = new Codice();
        codice1.setId(1L);
        Codice codice2 = new Codice();
        codice2.setId(codice1.getId());
        assertThat(codice1).isEqualTo(codice2);
        codice2.setId(2L);
        assertThat(codice1).isNotEqualTo(codice2);
        codice1.setId(null);
        assertThat(codice1).isNotEqualTo(codice2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodiceDTO.class);
        CodiceDTO codiceDTO1 = new CodiceDTO();
        codiceDTO1.setId(1L);
        CodiceDTO codiceDTO2 = new CodiceDTO();
        assertThat(codiceDTO1).isNotEqualTo(codiceDTO2);
        codiceDTO2.setId(codiceDTO1.getId());
        assertThat(codiceDTO1).isEqualTo(codiceDTO2);
        codiceDTO2.setId(2L);
        assertThat(codiceDTO1).isNotEqualTo(codiceDTO2);
        codiceDTO1.setId(null);
        assertThat(codiceDTO1).isNotEqualTo(codiceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(codiceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(codiceMapper.fromId(null)).isNull();
    }
}

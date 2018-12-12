package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.SecretKey;
import io.github.jhipster.application.repository.SecretKeyRepository;
import io.github.jhipster.application.service.SecretKeyService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SecretKeyResource REST controller.
 *
 * @see SecretKeyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class SecretKeyResourceIntTest {

    private static final Integer DEFAULT_UNIQUE_ID = 1;
    private static final Integer UPDATED_UNIQUE_ID = 2;

    private static final Integer DEFAULT_MANU_ID = 1;
    private static final Integer UPDATED_MANU_ID = 2;

    private static final Boolean DEFAULT_ASSIGNMENT_STATUS = false;
    private static final Boolean UPDATED_ASSIGNMENT_STATUS = true;

    private static final Boolean DEFAULT_VALID_STATUS = false;
    private static final Boolean UPDATED_VALID_STATUS = true;

    @Autowired
    private SecretKeyRepository secretKeyRepository;

    @Autowired
    private SecretKeyService secretKeyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSecretKeyMockMvc;

    private SecretKey secretKey;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SecretKeyResource secretKeyResource = new SecretKeyResource(secretKeyService);
        this.restSecretKeyMockMvc = MockMvcBuilders.standaloneSetup(secretKeyResource)
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
    public static SecretKey createEntity(EntityManager em) {
        SecretKey secretKey = new SecretKey()
            .uniqueId(DEFAULT_UNIQUE_ID)
            .manuId(DEFAULT_MANU_ID)
            .assignmentStatus(DEFAULT_ASSIGNMENT_STATUS)
            .validStatus(DEFAULT_VALID_STATUS);
        return secretKey;
    }

    @Before
    public void initTest() {
        secretKey = createEntity(em);
    }

    @Test
    @Transactional
    public void createSecretKey() throws Exception {
        int databaseSizeBeforeCreate = secretKeyRepository.findAll().size();

        // Create the SecretKey
        restSecretKeyMockMvc.perform(post("/api/secret-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secretKey)))
            .andExpect(status().isCreated());

        // Validate the SecretKey in the database
        List<SecretKey> secretKeyList = secretKeyRepository.findAll();
        assertThat(secretKeyList).hasSize(databaseSizeBeforeCreate + 1);
        SecretKey testSecretKey = secretKeyList.get(secretKeyList.size() - 1);
        assertThat(testSecretKey.getUniqueId()).isEqualTo(DEFAULT_UNIQUE_ID);
        assertThat(testSecretKey.getManuId()).isEqualTo(DEFAULT_MANU_ID);
        assertThat(testSecretKey.isAssignmentStatus()).isEqualTo(DEFAULT_ASSIGNMENT_STATUS);
        assertThat(testSecretKey.isValidStatus()).isEqualTo(DEFAULT_VALID_STATUS);
    }

    @Test
    @Transactional
    public void createSecretKeyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = secretKeyRepository.findAll().size();

        // Create the SecretKey with an existing ID
        secretKey.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecretKeyMockMvc.perform(post("/api/secret-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secretKey)))
            .andExpect(status().isBadRequest());

        // Validate the SecretKey in the database
        List<SecretKey> secretKeyList = secretKeyRepository.findAll();
        assertThat(secretKeyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSecretKeys() throws Exception {
        // Initialize the database
        secretKeyRepository.saveAndFlush(secretKey);

        // Get all the secretKeyList
        restSecretKeyMockMvc.perform(get("/api/secret-keys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secretKey.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniqueId").value(hasItem(DEFAULT_UNIQUE_ID)))
            .andExpect(jsonPath("$.[*].manuId").value(hasItem(DEFAULT_MANU_ID)))
            .andExpect(jsonPath("$.[*].assignmentStatus").value(hasItem(DEFAULT_ASSIGNMENT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].validStatus").value(hasItem(DEFAULT_VALID_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSecretKey() throws Exception {
        // Initialize the database
        secretKeyRepository.saveAndFlush(secretKey);

        // Get the secretKey
        restSecretKeyMockMvc.perform(get("/api/secret-keys/{id}", secretKey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(secretKey.getId().intValue()))
            .andExpect(jsonPath("$.uniqueId").value(DEFAULT_UNIQUE_ID))
            .andExpect(jsonPath("$.manuId").value(DEFAULT_MANU_ID))
            .andExpect(jsonPath("$.assignmentStatus").value(DEFAULT_ASSIGNMENT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.validStatus").value(DEFAULT_VALID_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSecretKey() throws Exception {
        // Get the secretKey
        restSecretKeyMockMvc.perform(get("/api/secret-keys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSecretKey() throws Exception {
        // Initialize the database
        secretKeyService.save(secretKey);

        int databaseSizeBeforeUpdate = secretKeyRepository.findAll().size();

        // Update the secretKey
        SecretKey updatedSecretKey = secretKeyRepository.findById(secretKey.getId()).get();
        // Disconnect from session so that the updates on updatedSecretKey are not directly saved in db
        em.detach(updatedSecretKey);
        updatedSecretKey
            .uniqueId(UPDATED_UNIQUE_ID)
            .manuId(UPDATED_MANU_ID)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .validStatus(UPDATED_VALID_STATUS);

        restSecretKeyMockMvc.perform(put("/api/secret-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSecretKey)))
            .andExpect(status().isOk());

        // Validate the SecretKey in the database
        List<SecretKey> secretKeyList = secretKeyRepository.findAll();
        assertThat(secretKeyList).hasSize(databaseSizeBeforeUpdate);
        SecretKey testSecretKey = secretKeyList.get(secretKeyList.size() - 1);
        assertThat(testSecretKey.getUniqueId()).isEqualTo(UPDATED_UNIQUE_ID);
        assertThat(testSecretKey.getManuId()).isEqualTo(UPDATED_MANU_ID);
        assertThat(testSecretKey.isAssignmentStatus()).isEqualTo(UPDATED_ASSIGNMENT_STATUS);
        assertThat(testSecretKey.isValidStatus()).isEqualTo(UPDATED_VALID_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSecretKey() throws Exception {
        int databaseSizeBeforeUpdate = secretKeyRepository.findAll().size();

        // Create the SecretKey

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecretKeyMockMvc.perform(put("/api/secret-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secretKey)))
            .andExpect(status().isBadRequest());

        // Validate the SecretKey in the database
        List<SecretKey> secretKeyList = secretKeyRepository.findAll();
        assertThat(secretKeyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSecretKey() throws Exception {
        // Initialize the database
        secretKeyService.save(secretKey);

        int databaseSizeBeforeDelete = secretKeyRepository.findAll().size();

        // Get the secretKey
        restSecretKeyMockMvc.perform(delete("/api/secret-keys/{id}", secretKey.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SecretKey> secretKeyList = secretKeyRepository.findAll();
        assertThat(secretKeyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecretKey.class);
        SecretKey secretKey1 = new SecretKey();
        secretKey1.setId(1L);
        SecretKey secretKey2 = new SecretKey();
        secretKey2.setId(secretKey1.getId());
        assertThat(secretKey1).isEqualTo(secretKey2);
        secretKey2.setId(2L);
        assertThat(secretKey1).isNotEqualTo(secretKey2);
        secretKey1.setId(null);
        assertThat(secretKey1).isNotEqualTo(secretKey2);
    }
}

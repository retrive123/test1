package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.AuthenticKey;
import io.github.jhipster.application.repository.AuthenticKeyRepository;
import io.github.jhipster.application.service.AuthenticKeyService;
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
 * Test class for the AuthenticKeyResource REST controller.
 *
 * @see AuthenticKeyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AuthenticKeyResourceIntTest {

    private static final Integer DEFAULT_UNIQUE_KEY = 1;
    private static final Integer UPDATED_UNIQUE_KEY = 2;

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;

    private static final Boolean DEFAULT_ASSIGNMENT_STATUS = false;
    private static final Boolean UPDATED_ASSIGNMENT_STATUS = true;

    private static final Boolean DEFAULT_VALID_STATUS = false;
    private static final Boolean UPDATED_VALID_STATUS = true;

    @Autowired
    private AuthenticKeyRepository authenticKeyRepository;

    @Autowired
    private AuthenticKeyService authenticKeyService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAuthenticKeyMockMvc;

    private AuthenticKey authenticKey;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AuthenticKeyResource authenticKeyResource = new AuthenticKeyResource(authenticKeyService);
        this.restAuthenticKeyMockMvc = MockMvcBuilders.standaloneSetup(authenticKeyResource)
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
    public static AuthenticKey createEntity(EntityManager em) {
        AuthenticKey authenticKey = new AuthenticKey()
            .uniqueKey(DEFAULT_UNIQUE_KEY)
            .productId(DEFAULT_PRODUCT_ID)
            .assignmentStatus(DEFAULT_ASSIGNMENT_STATUS)
            .validStatus(DEFAULT_VALID_STATUS);
        return authenticKey;
    }

    @Before
    public void initTest() {
        authenticKey = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuthenticKey() throws Exception {
        int databaseSizeBeforeCreate = authenticKeyRepository.findAll().size();

        // Create the AuthenticKey
        restAuthenticKeyMockMvc.perform(post("/api/authentic-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(authenticKey)))
            .andExpect(status().isCreated());

        // Validate the AuthenticKey in the database
        List<AuthenticKey> authenticKeyList = authenticKeyRepository.findAll();
        assertThat(authenticKeyList).hasSize(databaseSizeBeforeCreate + 1);
        AuthenticKey testAuthenticKey = authenticKeyList.get(authenticKeyList.size() - 1);
        assertThat(testAuthenticKey.getUniqueKey()).isEqualTo(DEFAULT_UNIQUE_KEY);
        assertThat(testAuthenticKey.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testAuthenticKey.isAssignmentStatus()).isEqualTo(DEFAULT_ASSIGNMENT_STATUS);
        assertThat(testAuthenticKey.isValidStatus()).isEqualTo(DEFAULT_VALID_STATUS);
    }

    @Test
    @Transactional
    public void createAuthenticKeyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = authenticKeyRepository.findAll().size();

        // Create the AuthenticKey with an existing ID
        authenticKey.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuthenticKeyMockMvc.perform(post("/api/authentic-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(authenticKey)))
            .andExpect(status().isBadRequest());

        // Validate the AuthenticKey in the database
        List<AuthenticKey> authenticKeyList = authenticKeyRepository.findAll();
        assertThat(authenticKeyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAuthenticKeys() throws Exception {
        // Initialize the database
        authenticKeyRepository.saveAndFlush(authenticKey);

        // Get all the authenticKeyList
        restAuthenticKeyMockMvc.perform(get("/api/authentic-keys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(authenticKey.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniqueKey").value(hasItem(DEFAULT_UNIQUE_KEY)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].assignmentStatus").value(hasItem(DEFAULT_ASSIGNMENT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].validStatus").value(hasItem(DEFAULT_VALID_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAuthenticKey() throws Exception {
        // Initialize the database
        authenticKeyRepository.saveAndFlush(authenticKey);

        // Get the authenticKey
        restAuthenticKeyMockMvc.perform(get("/api/authentic-keys/{id}", authenticKey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(authenticKey.getId().intValue()))
            .andExpect(jsonPath("$.uniqueKey").value(DEFAULT_UNIQUE_KEY))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.assignmentStatus").value(DEFAULT_ASSIGNMENT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.validStatus").value(DEFAULT_VALID_STATUS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAuthenticKey() throws Exception {
        // Get the authenticKey
        restAuthenticKeyMockMvc.perform(get("/api/authentic-keys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuthenticKey() throws Exception {
        // Initialize the database
        authenticKeyService.save(authenticKey);

        int databaseSizeBeforeUpdate = authenticKeyRepository.findAll().size();

        // Update the authenticKey
        AuthenticKey updatedAuthenticKey = authenticKeyRepository.findById(authenticKey.getId()).get();
        // Disconnect from session so that the updates on updatedAuthenticKey are not directly saved in db
        em.detach(updatedAuthenticKey);
        updatedAuthenticKey
            .uniqueKey(UPDATED_UNIQUE_KEY)
            .productId(UPDATED_PRODUCT_ID)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .validStatus(UPDATED_VALID_STATUS);

        restAuthenticKeyMockMvc.perform(put("/api/authentic-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAuthenticKey)))
            .andExpect(status().isOk());

        // Validate the AuthenticKey in the database
        List<AuthenticKey> authenticKeyList = authenticKeyRepository.findAll();
        assertThat(authenticKeyList).hasSize(databaseSizeBeforeUpdate);
        AuthenticKey testAuthenticKey = authenticKeyList.get(authenticKeyList.size() - 1);
        assertThat(testAuthenticKey.getUniqueKey()).isEqualTo(UPDATED_UNIQUE_KEY);
        assertThat(testAuthenticKey.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testAuthenticKey.isAssignmentStatus()).isEqualTo(UPDATED_ASSIGNMENT_STATUS);
        assertThat(testAuthenticKey.isValidStatus()).isEqualTo(UPDATED_VALID_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAuthenticKey() throws Exception {
        int databaseSizeBeforeUpdate = authenticKeyRepository.findAll().size();

        // Create the AuthenticKey

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuthenticKeyMockMvc.perform(put("/api/authentic-keys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(authenticKey)))
            .andExpect(status().isBadRequest());

        // Validate the AuthenticKey in the database
        List<AuthenticKey> authenticKeyList = authenticKeyRepository.findAll();
        assertThat(authenticKeyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuthenticKey() throws Exception {
        // Initialize the database
        authenticKeyService.save(authenticKey);

        int databaseSizeBeforeDelete = authenticKeyRepository.findAll().size();

        // Get the authenticKey
        restAuthenticKeyMockMvc.perform(delete("/api/authentic-keys/{id}", authenticKey.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AuthenticKey> authenticKeyList = authenticKeyRepository.findAll();
        assertThat(authenticKeyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuthenticKey.class);
        AuthenticKey authenticKey1 = new AuthenticKey();
        authenticKey1.setId(1L);
        AuthenticKey authenticKey2 = new AuthenticKey();
        authenticKey2.setId(authenticKey1.getId());
        assertThat(authenticKey1).isEqualTo(authenticKey2);
        authenticKey2.setId(2L);
        assertThat(authenticKey1).isNotEqualTo(authenticKey2);
        authenticKey1.setId(null);
        assertThat(authenticKey1).isNotEqualTo(authenticKey2);
    }
}

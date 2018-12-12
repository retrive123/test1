package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ProductDetails;
import io.github.jhipster.application.repository.ProductDetailsRepository;
import io.github.jhipster.application.service.ProductDetailsService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductDetailsResource REST controller.
 *
 * @see ProductDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ProductDetailsResourceIntTest {

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MANU_ID = 1;
    private static final Integer UPDATED_MANU_ID = 2;

    private static final String DEFAULT_MANU_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MANU_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;

    private static final LocalDate DEFAULT_PRODUCT_MANU_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRODUCT_MANU_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductDetailsMockMvc;

    private ProductDetails productDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductDetailsResource productDetailsResource = new ProductDetailsResource(productDetailsService);
        this.restProductDetailsMockMvc = MockMvcBuilders.standaloneSetup(productDetailsResource)
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
    public static ProductDetails createEntity(EntityManager em) {
        ProductDetails productDetails = new ProductDetails()
            .productName(DEFAULT_PRODUCT_NAME)
            .manuId(DEFAULT_MANU_ID)
            .manuName(DEFAULT_MANU_NAME)
            .productId(DEFAULT_PRODUCT_ID)
            .productManuDate(DEFAULT_PRODUCT_MANU_DATE);
        return productDetails;
    }

    @Before
    public void initTest() {
        productDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductDetails() throws Exception {
        int databaseSizeBeforeCreate = productDetailsRepository.findAll().size();

        // Create the ProductDetails
        restProductDetailsMockMvc.perform(post("/api/product-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDetails)))
            .andExpect(status().isCreated());

        // Validate the ProductDetails in the database
        List<ProductDetails> productDetailsList = productDetailsRepository.findAll();
        assertThat(productDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ProductDetails testProductDetails = productDetailsList.get(productDetailsList.size() - 1);
        assertThat(testProductDetails.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProductDetails.getManuId()).isEqualTo(DEFAULT_MANU_ID);
        assertThat(testProductDetails.getManuName()).isEqualTo(DEFAULT_MANU_NAME);
        assertThat(testProductDetails.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProductDetails.getProductManuDate()).isEqualTo(DEFAULT_PRODUCT_MANU_DATE);
    }

    @Test
    @Transactional
    public void createProductDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productDetailsRepository.findAll().size();

        // Create the ProductDetails with an existing ID
        productDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductDetailsMockMvc.perform(post("/api/product-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDetails)))
            .andExpect(status().isBadRequest());

        // Validate the ProductDetails in the database
        List<ProductDetails> productDetailsList = productDetailsRepository.findAll();
        assertThat(productDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductDetails() throws Exception {
        // Initialize the database
        productDetailsRepository.saveAndFlush(productDetails);

        // Get all the productDetailsList
        restProductDetailsMockMvc.perform(get("/api/product-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].manuId").value(hasItem(DEFAULT_MANU_ID)))
            .andExpect(jsonPath("$.[*].manuName").value(hasItem(DEFAULT_MANU_NAME.toString())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productManuDate").value(hasItem(DEFAULT_PRODUCT_MANU_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getProductDetails() throws Exception {
        // Initialize the database
        productDetailsRepository.saveAndFlush(productDetails);

        // Get the productDetails
        restProductDetailsMockMvc.perform(get("/api/product-details/{id}", productDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productDetails.getId().intValue()))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.manuId").value(DEFAULT_MANU_ID))
            .andExpect(jsonPath("$.manuName").value(DEFAULT_MANU_NAME.toString()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productManuDate").value(DEFAULT_PRODUCT_MANU_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductDetails() throws Exception {
        // Get the productDetails
        restProductDetailsMockMvc.perform(get("/api/product-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductDetails() throws Exception {
        // Initialize the database
        productDetailsService.save(productDetails);

        int databaseSizeBeforeUpdate = productDetailsRepository.findAll().size();

        // Update the productDetails
        ProductDetails updatedProductDetails = productDetailsRepository.findById(productDetails.getId()).get();
        // Disconnect from session so that the updates on updatedProductDetails are not directly saved in db
        em.detach(updatedProductDetails);
        updatedProductDetails
            .productName(UPDATED_PRODUCT_NAME)
            .manuId(UPDATED_MANU_ID)
            .manuName(UPDATED_MANU_NAME)
            .productId(UPDATED_PRODUCT_ID)
            .productManuDate(UPDATED_PRODUCT_MANU_DATE);

        restProductDetailsMockMvc.perform(put("/api/product-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductDetails)))
            .andExpect(status().isOk());

        // Validate the ProductDetails in the database
        List<ProductDetails> productDetailsList = productDetailsRepository.findAll();
        assertThat(productDetailsList).hasSize(databaseSizeBeforeUpdate);
        ProductDetails testProductDetails = productDetailsList.get(productDetailsList.size() - 1);
        assertThat(testProductDetails.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProductDetails.getManuId()).isEqualTo(UPDATED_MANU_ID);
        assertThat(testProductDetails.getManuName()).isEqualTo(UPDATED_MANU_NAME);
        assertThat(testProductDetails.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProductDetails.getProductManuDate()).isEqualTo(UPDATED_PRODUCT_MANU_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProductDetails() throws Exception {
        int databaseSizeBeforeUpdate = productDetailsRepository.findAll().size();

        // Create the ProductDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductDetailsMockMvc.perform(put("/api/product-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDetails)))
            .andExpect(status().isBadRequest());

        // Validate the ProductDetails in the database
        List<ProductDetails> productDetailsList = productDetailsRepository.findAll();
        assertThat(productDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductDetails() throws Exception {
        // Initialize the database
        productDetailsService.save(productDetails);

        int databaseSizeBeforeDelete = productDetailsRepository.findAll().size();

        // Get the productDetails
        restProductDetailsMockMvc.perform(delete("/api/product-details/{id}", productDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductDetails> productDetailsList = productDetailsRepository.findAll();
        assertThat(productDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductDetails.class);
        ProductDetails productDetails1 = new ProductDetails();
        productDetails1.setId(1L);
        ProductDetails productDetails2 = new ProductDetails();
        productDetails2.setId(productDetails1.getId());
        assertThat(productDetails1).isEqualTo(productDetails2);
        productDetails2.setId(2L);
        assertThat(productDetails1).isNotEqualTo(productDetails2);
        productDetails1.setId(null);
        assertThat(productDetails1).isNotEqualTo(productDetails2);
    }
}

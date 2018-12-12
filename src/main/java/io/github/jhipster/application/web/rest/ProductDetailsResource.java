package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ProductDetails;
import io.github.jhipster.application.service.ProductDetailsService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductDetails.
 */
@RestController
@RequestMapping("/api")
public class ProductDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ProductDetailsResource.class);

    private static final String ENTITY_NAME = "productDetails";

    private final ProductDetailsService productDetailsService;

    public ProductDetailsResource(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    /**
     * POST  /product-details : Create a new productDetails.
     *
     * @param productDetails the productDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productDetails, or with status 400 (Bad Request) if the productDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-details")
    @Timed
    public ResponseEntity<ProductDetails> createProductDetails(@RequestBody ProductDetails productDetails) throws URISyntaxException {
        log.debug("REST request to save ProductDetails : {}", productDetails);
        if (productDetails.getId() != null) {
            throw new BadRequestAlertException("A new productDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductDetails result = productDetailsService.save(productDetails);
        return ResponseEntity.created(new URI("/api/product-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-details : Updates an existing productDetails.
     *
     * @param productDetails the productDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productDetails,
     * or with status 400 (Bad Request) if the productDetails is not valid,
     * or with status 500 (Internal Server Error) if the productDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-details")
    @Timed
    public ResponseEntity<ProductDetails> updateProductDetails(@RequestBody ProductDetails productDetails) throws URISyntaxException {
        log.debug("REST request to update ProductDetails : {}", productDetails);
        if (productDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductDetails result = productDetailsService.save(productDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-details : get all the productDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productDetails in body
     */
    @GetMapping("/product-details")
    @Timed
    public List<ProductDetails> getAllProductDetails() {
        log.debug("REST request to get all ProductDetails");
        return productDetailsService.findAll();
    }

    /**
     * GET  /product-details/:id : get the "id" productDetails.
     *
     * @param id the id of the productDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDetails, or with status 404 (Not Found)
     */
    @GetMapping("/product-details/{id}")
    @Timed
    public ResponseEntity<ProductDetails> getProductDetails(@PathVariable Long id) {
        log.debug("REST request to get ProductDetails : {}", id);
        Optional<ProductDetails> productDetails = productDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productDetails);
    }

    /**
     * DELETE  /product-details/:id : delete the "id" productDetails.
     *
     * @param id the id of the productDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductDetails(@PathVariable Long id) {
        log.debug("REST request to delete ProductDetails : {}", id);
        productDetailsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

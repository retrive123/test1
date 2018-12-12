package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ProductDetails;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProductDetails.
 */
public interface ProductDetailsService {

    /**
     * Save a productDetails.
     *
     * @param productDetails the entity to save
     * @return the persisted entity
     */
    ProductDetails save(ProductDetails productDetails);

    /**
     * Get all the productDetails.
     *
     * @return the list of entities
     */
    List<ProductDetails> findAll();


    /**
     * Get the "id" productDetails.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProductDetails> findOne(Long id);

    /**
     * Delete the "id" productDetails.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}

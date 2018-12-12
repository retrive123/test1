package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.SecretKey;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SecretKey.
 */
public interface SecretKeyService {

    /**
     * Save a secretKey.
     *
     * @param secretKey the entity to save
     * @return the persisted entity
     */
    SecretKey save(SecretKey secretKey);

    /**
     * Get all the secretKeys.
     *
     * @return the list of entities
     */
    List<SecretKey> findAll();


    /**
     * Get the "id" secretKey.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SecretKey> findOne(Long id);

    /**
     * Delete the "id" secretKey.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	

	Optional<SecretKey> findByUniqueKey(int uniqueId);
}

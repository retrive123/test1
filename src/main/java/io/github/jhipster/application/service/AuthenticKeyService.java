package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.AuthenticKey;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AuthenticKey.
 */
public interface AuthenticKeyService {

    /**
     * Save a authenticKey.
     *
     * @param authenticKey the entity to save
     * @return the persisted entity
     */
    AuthenticKey save(AuthenticKey authenticKey);

    /**
     * Get all the authenticKeys.
     *
     * @return the list of entities
     */
    List<AuthenticKey> findAll();


    /**
     * Get the "id" authenticKey.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AuthenticKey> findOne(Long id);

    /**
     * Delete the "id" authenticKey.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Optional<AuthenticKey> findByUniqueKey(int uniqueKey);
}

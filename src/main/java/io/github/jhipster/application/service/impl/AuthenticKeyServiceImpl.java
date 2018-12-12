package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.AuthenticKeyService;
import io.github.jhipster.application.domain.AuthenticKey;
import io.github.jhipster.application.repository.AuthenticKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing AuthenticKey.
 */
@Service
@Transactional
public class AuthenticKeyServiceImpl implements AuthenticKeyService {

    private final Logger log = LoggerFactory.getLogger(AuthenticKeyServiceImpl.class);

    private final AuthenticKeyRepository authenticKeyRepository;

    public AuthenticKeyServiceImpl(AuthenticKeyRepository authenticKeyRepository) {
        this.authenticKeyRepository = authenticKeyRepository;
    }

    /**
     * Save a authenticKey.
     *
     * @param authenticKey the entity to save
     * @return the persisted entity
     */
    @Override
    public AuthenticKey save(AuthenticKey authenticKey) {
        log.debug("Request to save AuthenticKey : {}", authenticKey);
        return authenticKeyRepository.save(authenticKey);
    }

    /**
     * Get all the authenticKeys.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AuthenticKey> findAll() {
        log.debug("Request to get all AuthenticKeys");
        return authenticKeyRepository.findAll();
    }


    /**
     * Get one authenticKey by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AuthenticKey> findOne(Long id) {
        log.debug("Request to get AuthenticKey : {}", id);
        return authenticKeyRepository.findById(id);
    }

    /**
     * Delete the authenticKey by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuthenticKey : {}", id);
        authenticKeyRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthenticKey> findByUniqueKey(int uniqueKey) {
        log.debug("Request to get AuthenticKey : {}", uniqueKey);
        return authenticKeyRepository.findByUniqueKey(uniqueKey);
    }
}

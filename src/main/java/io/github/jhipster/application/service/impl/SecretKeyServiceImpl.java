package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.SecretKeyService;

import io.github.jhipster.application.domain.SecretKey;
import io.github.jhipster.application.repository.SecretKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SecretKey.
 */
@Service
@Transactional
public class SecretKeyServiceImpl implements SecretKeyService {

    private final Logger log = LoggerFactory.getLogger(SecretKeyServiceImpl.class);

    private final SecretKeyRepository secretKeyRepository;
    

    public SecretKeyServiceImpl(SecretKeyRepository secretKeyRepository) {
        this.secretKeyRepository = secretKeyRepository;
    }

    /**
     * Save a secretKey.
     *
     * @param secretKey the entity to save
     * @return the persisted entity
     */
    @Override
    public SecretKey save(SecretKey secretKey) {
        log.debug("Request to save SecretKey : {}", secretKey);
        return secretKeyRepository.save(secretKey);
    }

    /**
     * Get all the secretKeys.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SecretKey> findAll() {
        log.debug("Request to get all SecretKeys");
        return secretKeyRepository.findAll();
    }


    /**
     * Get one secretKey by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SecretKey> findOne(Long id) {
        log.debug("Request to get SecretKey : {}", id);
        return secretKeyRepository.findById(id);
    }
    /**s
     * Get one secretKey by uniquekey.
     *
     * @param uniqueId the uniqueId of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SecretKey> findByUniqueKey(int uniqueId) {
        log.debug("Request to get SecretKey : {}", uniqueId);
        return secretKeyRepository.findOneByUniqueId(uniqueId);
    }

    /**
     * Delete the secretKey by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SecretKey : {}", id);
        secretKeyRepository.deleteById(id);
    }
}

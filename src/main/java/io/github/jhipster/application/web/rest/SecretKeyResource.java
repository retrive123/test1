package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.SecretKey;
import io.github.jhipster.application.service.SecretKeyService;
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
 * REST controller for managing SecretKey.
 */
@RestController
@RequestMapping("/api")
public class SecretKeyResource {

    private final Logger log = LoggerFactory.getLogger(SecretKeyResource.class);

    private static final String ENTITY_NAME = "secretKey";

    private final SecretKeyService secretKeyService;

    public SecretKeyResource(SecretKeyService secretKeyService) {
        this.secretKeyService = secretKeyService;
    }

    /**
     * POST  /secret-keys : Create a new secretKey.
     *
     * @param secretKey the secretKey to create
     * @return the ResponseEntity with status 201 (Created) and with body the new secretKey, or with status 400 (Bad Request) if the secretKey has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/secret-keys")
    @Timed
    public ResponseEntity<SecretKey> createSecretKey(@RequestBody SecretKey secretKey) throws URISyntaxException {
        log.debug("REST request to save SecretKey : {}", secretKey);
        if (secretKey.getId() != null) {
            throw new BadRequestAlertException("A new secretKey cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecretKey result = secretKeyService.save(secretKey);
        
        return ResponseEntity.created(new URI("/api/secret-keys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /secret-keys : Updates an existing secretKey.
     *
     * @param secretKey the secretKey to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated secretKey,
     * or with status 400 (Bad Request) if the secretKey is not valid,
     * or with status 500 (Internal Server Error) if the secretKey couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/secret-keys")
    @Timed
    public ResponseEntity<SecretKey> updateSecretKey(@RequestBody SecretKey secretKey) throws URISyntaxException {
        log.debug("REST request to update SecretKey : {}", secretKey);
        if (secretKey.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SecretKey result = secretKeyService.save(secretKey);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, secretKey.getId().toString()))
            .body(result);
    }

    /**
     * GET  /secret-keys : get all the secretKeys.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of secretKeys in body
     */
    @GetMapping("/secret-keys")
    @Timed
    public List<SecretKey> getAllSecretKeys() {
        log.debug("REST request to get all SecretKeys");
        return secretKeyService.findAll();
    }

    /**
     * GET  /secret-keys/:id : get the "id" secretKey.
     *
     * @param id the id of the secretKey to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the secretKey, or with status 404 (Not Found)
     */
    @GetMapping("/secret-keys/{id}")
    @Timed
    public ResponseEntity<SecretKey> getSecretKey(@PathVariable Long id) {
        log.debug("REST request to get SecretKey : {}", id);
        Optional<SecretKey> secretKey = secretKeyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(secretKey);
    }

    /**
     * DELETE  /secret-keys/:id : delete the "id" secretKey.
     *
     * @param id the id of the secretKey to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/secret-keys/{id}")
    @Timed
    public ResponseEntity<Void> deleteSecretKey(@PathVariable Long id) {
        log.debug("REST request to delete SecretKey : {}", id);
        secretKeyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

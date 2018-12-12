package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.AuthenticKey;
import io.github.jhipster.application.service.AuthenticKeyService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.github.jhipster.application.service.dto.ResponseDetails;
// import io.github.jhipster.application.service.UniqueKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AuthenticKey.
 */
@RestController
@RequestMapping("/api")
public class AuthenticKeyResource {

    private final Logger log = LoggerFactory.getLogger(AuthenticKeyResource.class);

    private static final String ENTITY_NAME = "authenticKey";

    private final AuthenticKeyService authenticKeyService;
    
    public AuthenticKeyResource(AuthenticKeyService authenticKeyService) {
        this.authenticKeyService = authenticKeyService;
    }

    /**
     * POST  /authentic-keys : Create a new authenticKey.
     *
     * @param authenticKey the authenticKey to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authenticKey, or with status 400 (Bad Request) if the authenticKey has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authentic-keys")
    @Timed
    public ResponseEntity<AuthenticKey> createAuthenticKey(@RequestBody AuthenticKey authenticKey) throws URISyntaxException {
        log.debug("REST request to save AuthenticKey : {}", authenticKey);
        authenticKey.setAssignmentStatus(true);
        authenticKey.setUniqueKey(UniqueKeyGenerator.getuniquekey());
        if (authenticKey.getId() != null) {
            throw new BadRequestAlertException("A new authenticKey cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthenticKey result = authenticKeyService.save(authenticKey);
        return ResponseEntity.created(new URI("/api/authentic-keys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authentic-keys : Updates an existing authenticKey.
     *
     * @param authenticKey the authenticKey to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authenticKey,
     * or with status 400 (Bad Request) if the authenticKey is not valid,
     * or with status 500 (Internal Server Error) if the authenticKey couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authentic-keys")
    @Timed
    public ResponseEntity<AuthenticKey> updateAuthenticKey(@RequestBody AuthenticKey authenticKey) throws URISyntaxException {
        log.debug("REST request to update AuthenticKey : {}", authenticKey);
        if (authenticKey.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthenticKey result = authenticKeyService.save(authenticKey);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authenticKey.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authentic-keys : get all the authenticKeys.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authenticKeys in body
     */
    @GetMapping("/authentic-keys")
    @Timed
    public List<AuthenticKey> getAllAuthenticKeys() {
        log.debug("REST request to get all AuthenticKeys");
        return authenticKeyService.findAll();
    }

    /**
     * GET  /authentic-keys/:id : get the "id" authenticKey.
     *
     * @param id the id of the authenticKey to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authenticKey, or with status 404 (Not Found)
     */
    @GetMapping("/authentic-keys/{id}")
    @Timed
    public ResponseEntity<AuthenticKey> getAuthenticKey(@PathVariable Long id) {
        log.debug("REST request to get AuthenticKey : {}", id);
        Optional<AuthenticKey> authenticKey = authenticKeyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authenticKey);
    }

    /**
     * DELETE  /authentic-keys/:id : delete the "id" authenticKey.
     *
     * @param id the id of the authenticKey to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authentic-keys/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuthenticKey(@PathVariable Long id) {
        log.debug("REST request to delete AuthenticKey : {}", id);
        authenticKeyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/authentic-check/{key}")
    @Timed
    public ResponseEntity<ResponseDetails> getAuthenticKeyDetails(@PathVariable int key) {
        log.debug("REST request to get AuthenticKey : {}", key);
        Optional<AuthenticKey> authenticKey = authenticKeyService.findByUniqueKey(key);
        if (authenticKey.isPresent()) {
        if(!authenticKey.get().isValidStatus()) {
            authenticKey.get().setValidStatus(true);
            authenticKeyService.save(authenticKey.get());
            return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authenticKey.get().getId().toString()))
            .body(new ResponseDetails(true,"Is Authenticated"));
        }
        else {
            return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authenticKey.get().getId().toString()))
            .body(new ResponseDetails(false,"Is Already Authenticated"));
        }
    } 
     else {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
     }

    }
}

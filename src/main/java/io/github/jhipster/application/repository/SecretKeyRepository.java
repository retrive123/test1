package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.SecretKey;
import io.github.jhipster.application.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SecretKey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecretKeyRepository extends JpaRepository<SecretKey, Long> {
	Optional<SecretKey> findOneByUniqueId(int uniqueId);

}

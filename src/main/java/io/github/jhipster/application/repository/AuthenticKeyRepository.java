package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AuthenticKey;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Spring Data  repository for the AuthenticKey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthenticKeyRepository extends JpaRepository<AuthenticKey, Long> {
    Optional<AuthenticKey> findByUniqueKey(int uniqueKey);
}

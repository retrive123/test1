package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ProductDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

}

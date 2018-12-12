package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ProductService;
import io.github.jhipster.application.domain.Product;
import io.github.jhipster.application.domain.SecretKey;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.repository.ProductRepository;
import io.github.jhipster.application.repository.SecretKeyRepository;
import io.github.jhipster.application.repository.UserRepository;
import io.github.jhipster.application.security.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SecretKeyRepository secretKeyRepository;

    public ProductServiceImpl(ProductRepository productRepository,UserRepository userRepository,SecretKeyRepository secretKeyRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.secretKeyRepository=secretKeyRepository;
    }

    /**
     * Save a product.
     *
     * @param product the entity to save
     * @return the persisted entity
     */
    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        String str = SecurityUtils.getCurrentUserLogin().toString();
        str = str.substring(str.indexOf("[") + 1);
	    str = str.substring(0, str.indexOf("]"));

	    System.out.println(str);
	    
	    Optional<User> user= userRepository.findOneByLogin(str);
	    int userid= Math.toIntExact (user.get().getId());
        product.setManuId(userid);
        product.setManuName(user.get().getFirstName());
       SecretKey asdf =product.getSecretKey();
       System.out.println(asdf.toString());
       asdf.assignmentStatus(true);
       asdf.setManuId(userid);
        
       secretKeyRepository.save(asdf);
        
        
        return productRepository.save(product);
        
        
    }

    /**
     * Get all the products.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        log.debug("Request to get all Products");
        return productRepository.findAll();
    }


    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}

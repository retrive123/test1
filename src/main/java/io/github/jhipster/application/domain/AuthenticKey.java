package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AuthenticKey.
 */
@Entity
@Table(name = "authentic_key")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AuthenticKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_key")
    private Integer uniqueKey;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "assignment_status")
    private Boolean assignmentStatus;

    @Column(name = "valid_status")
    private Boolean validStatus;

    @OneToOne    @JoinColumn(unique = true)
    private ProductDetails productDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniqueKey() {
        return uniqueKey;
    }

    public AuthenticKey uniqueKey(Integer uniqueKey) {
        this.uniqueKey = uniqueKey;
        return this;
    }

    public void setUniqueKey(Integer uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Integer getProductId() {
        return productId;
    }

    public AuthenticKey productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean isAssignmentStatus() {
        return assignmentStatus;
    }

    public AuthenticKey assignmentStatus(Boolean assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    public void setAssignmentStatus(Boolean assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Boolean isValidStatus() {
        return validStatus;
    }

    public AuthenticKey validStatus(Boolean validStatus) {
        this.validStatus = validStatus;
        return this;
    }

    public void setValidStatus(Boolean validStatus) {
        this.validStatus = validStatus;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public AuthenticKey productDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
        return this;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthenticKey authenticKey = (AuthenticKey) o;
        if (authenticKey.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authenticKey.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthenticKey{" +
            "id=" + getId() +
            ", uniqueKey=" + getUniqueKey() +
            ", productId=" + getProductId() +
            ", assignmentStatus='" + isAssignmentStatus() + "'" +
            ", validStatus='" + isValidStatus() + "'" +
            "}";
    }
}

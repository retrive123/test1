package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "manu_id")
    private Integer manuId;

    @Column(name = "manu_name")
    private String manuName;

    @Column(name = "product_id")
    private Integer productId;

    @OneToOne    @JoinColumn(unique = true)
    private SecretKey secretKey;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public Product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getManuId() {
        return manuId;
    }

    public Product manuId(Integer manuId) {
        this.manuId = manuId;
        return this;
    }

    public void setManuId(Integer manuId) {
        this.manuId = manuId;
    }

    public String getManuName() {
        return manuName;
    }

    public Product manuName(String manuName) {
        this.manuName = manuName;
        return this;
    }

    public void setManuName(String manuName) {
        this.manuName = manuName;
    }

    public Integer getProductId() {
        return productId;
    }

    public Product productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public Product secretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", manuId=" + getManuId() +
            ", manuName='" + getManuName() + "'" +
            ", productId=" + getProductId() +
            "}";
    }
}

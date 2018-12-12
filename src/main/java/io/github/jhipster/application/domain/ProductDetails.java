package io.github.jhipster.application.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Task entity.
 * @author The JHipster team.
 */
@ApiModel(description = "Task entity. @author The JHipster team.")
@Entity
@Table(name = "product_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductDetails implements Serializable {

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

    @Column(name = "product_manu_date")
    private LocalDate productManuDate;

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

    public ProductDetails productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getManuId() {
        return manuId;
    }

    public ProductDetails manuId(Integer manuId) {
        this.manuId = manuId;
        return this;
    }

    public void setManuId(Integer manuId) {
        this.manuId = manuId;
    }

    public String getManuName() {
        return manuName;
    }

    public ProductDetails manuName(String manuName) {
        this.manuName = manuName;
        return this;
    }

    public void setManuName(String manuName) {
        this.manuName = manuName;
    }

    public Integer getProductId() {
        return productId;
    }

    public ProductDetails productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDate getProductManuDate() {
        return productManuDate;
    }

    public ProductDetails productManuDate(LocalDate productManuDate) {
        this.productManuDate = productManuDate;
        return this;
    }

    public void setProductManuDate(LocalDate productManuDate) {
        this.productManuDate = productManuDate;
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
        ProductDetails productDetails = (ProductDetails) o;
        if (productDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", manuId=" + getManuId() +
            ", manuName='" + getManuName() + "'" +
            ", productId=" + getProductId() +
            ", productManuDate='" + getProductManuDate() + "'" +
            "}";
    }
}

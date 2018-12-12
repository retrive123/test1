package io.github.jhipster.application.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Task entity.
 * @author The JHipster team.
 */
@ApiModel(description = "Task entity. @author The JHipster team.")
@Entity
@Table(name = "secret_key")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SecretKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_id")
    private Integer uniqueId;

    @Column(name = "manu_id")
    private Integer manuId;

    @Column(name = "assignment_status")
    private Boolean assignmentStatus;

    @Column(name = "valid_status")
    private Boolean validStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public SecretKey uniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getManuId() {
        return manuId;
    }

    public SecretKey manuId(Integer manuId) {
        this.manuId = manuId;
        return this;
    }

    public void setManuId(Integer manuId) {
        this.manuId = manuId;
    }

    public Boolean isAssignmentStatus() {
        return assignmentStatus;
    }

    public SecretKey assignmentStatus(Boolean assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    public void setAssignmentStatus(Boolean assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Boolean isValidStatus() {
        return validStatus;
    }

    public SecretKey validStatus(Boolean validStatus) {
        this.validStatus = validStatus;
        return this;
    }

    public void setValidStatus(Boolean validStatus) {
        this.validStatus = validStatus;
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
        SecretKey secretKey = (SecretKey) o;
        if (secretKey.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), secretKey.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SecretKey{" +
            "id=" + getId() +
            ", uniqueId=" + getUniqueId() +
            ", manuId=" + getManuId() +
            ", assignmentStatus='" + isAssignmentStatus() + "'" +
            ", validStatus='" + isValidStatus() + "'" +
            "}";
    }
}

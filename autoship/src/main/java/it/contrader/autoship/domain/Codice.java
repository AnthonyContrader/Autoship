package it.contrader.autoship.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import it.contrader.autoship.domain.enumeration.CodiceStato;

/**
 * A Codice.
 */
@Entity
@Table(name = "codice")
public class Codice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "otp")
    private String otp;

    @Column(name = "userId")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private CodiceStato stato;

    @Column(name = "cancellato")
    private Boolean cancellato;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public Codice otp(String otp) {
        this.otp = otp;
        return this;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getUserId() {
        return userId;
    }

    public Codice user_id(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUser_id(Long userId) {
        this.userId = userId;
    }

    public CodiceStato getStato() {
        return stato;
    }

    public Codice stato(CodiceStato stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(CodiceStato stato) {
        this.stato = stato;
    }

    public Boolean isCancellato() {
        return cancellato;
    }

    public Codice cancellato(Boolean cancellato) {
        this.cancellato = cancellato;
        return this;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
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
        Codice codice = (Codice) o;
        if (codice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), codice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Codice{" +
            "id=" + getId() +
            ", otp='" + getOtp() + "'" +
            ", user_id=" + getUserId() +
            ", stato='" + getStato() + "'" +
            ", cancellato='" + isCancellato() + "'" +
            "}";
    }
}

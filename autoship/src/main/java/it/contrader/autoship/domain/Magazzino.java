package it.contrader.autoship.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Magazzino.
 */
@Entity
@Table(name = "magazzino")
public class Magazzino implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "capienza", nullable = false)
    private Integer capienza;

    @Column(name = "cancellato")
    private Boolean cancellato;

    @OneToOne
    @JoinColumn(unique = true)
    private Oggetto oggetto;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Codice codice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public Magazzino capienza(Integer capienza) {
        this.capienza = capienza;
        return this;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
    }

    public Boolean isCancellato() {
        return cancellato;
    }

    public Magazzino cancellato(Boolean cancellato) {
        this.cancellato = cancellato;
        return this;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public Magazzino oggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
        return this;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public Codice getCodice() {
        return codice;
    }

    public Magazzino codice(Codice codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(Codice codice) {
        this.codice = codice;
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
        Magazzino magazzino = (Magazzino) o;
        if (magazzino.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), magazzino.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Magazzino{" +
            "id=" + getId() +
            ", capienza=" + getCapienza() +
            ", cancellato='" + isCancellato() + "'" +
            "}";
    }
}

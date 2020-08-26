package it.contrader.autoship.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Carrello.
 */
@Entity
@Table(name = "carrello")
public class Carrello implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cancellato")
    private Boolean cancellato;

    @ManyToOne
    @JsonIgnoreProperties("")
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

    public Boolean isCancellato() {
        return cancellato;
    }

    public Carrello cancellato(Boolean cancellato) {
        this.cancellato = cancellato;
        return this;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public Carrello oggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
        return this;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public Codice getCodice() {
        return codice;
    }

    public Carrello codice(Codice codice) {
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
        Carrello carrello = (Carrello) o;
        if (carrello.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carrello.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Carrello{" +
            "id=" + getId() +
            ", cancellato='" + isCancellato() + "'" +
            "}";
    }
}

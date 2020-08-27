package it.contrader.autoship.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Oggetto.
 */
@Entity
@Table(name = "oggetto")
public class Oggetto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "dimensione", nullable = false)
    private Integer dimensione;

    @Column(name = "cancellato")
    private Boolean cancellato;

    @Column(name = "cella")
    private Boolean cella;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Oggetto nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDimensione() {
        return dimensione;
    }

    public Oggetto dimensione(Integer dimensione) {
        this.dimensione = dimensione;
        return this;
    }

    public void setDimensione(Integer dimensione) {
        this.dimensione = dimensione;
    }

    public Boolean isCancellato() {
        return cancellato;
    }

    public Oggetto cancellato(Boolean cancellato) {
        this.cancellato = cancellato;
        return this;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
    }

    public Boolean isCella() {
        return cella;
    }

    public Oggetto cella(Boolean cella) {
        this.cella = cella;
        return this;
    }

    public void setCella(Boolean cella) {
        this.cella = cella;
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
        Oggetto oggetto = (Oggetto) o;
        if (oggetto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oggetto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Oggetto{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dimensione=" + getDimensione() +
            ", cancellato='" + isCancellato() + "'" +
            ", cella='" + isCella() + "'" +
            "}";
    }
}

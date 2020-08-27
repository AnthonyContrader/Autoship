package it.contrader.autoship.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Oggetto entity.
 */
public class OggettoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Integer dimensione;

    private Boolean cancellato;

    private Boolean cella;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDimensione() {
        return dimensione;
    }

    public void setDimensione(Integer dimensione) {
        this.dimensione = dimensione;
    }

    public Boolean isCancellato() {
        return cancellato;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
    }

    public Boolean isCella() {
        return cella;
    }

    public void setCella(Boolean cella) {
        this.cella = cella;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OggettoDTO oggettoDTO = (OggettoDTO) o;
        if (oggettoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oggettoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OggettoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dimensione=" + getDimensione() +
            ", cancellato='" + isCancellato() + "'" +
            ", cella='" + isCella() + "'" +
            "}";
    }
}

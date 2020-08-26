package it.contrader.autoship.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Carrello entity.
 */
public class CarrelloDTO implements Serializable {

    private Long id;

    private Boolean cancellato;

    private Long oggettoId;

    private String oggettoNome;

    private Long codiceId;

    private String codiceOtp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isCancellato() {
        return cancellato;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
    }

    public Long getOggettoId() {
        return oggettoId;
    }

    public void setOggettoId(Long oggettoId) {
        this.oggettoId = oggettoId;
    }

    public String getOggettoNome() {
        return oggettoNome;
    }

    public void setOggettoNome(String oggettoNome) {
        this.oggettoNome = oggettoNome;
    }

    public Long getCodiceId() {
        return codiceId;
    }

    public void setCodiceId(Long codiceId) {
        this.codiceId = codiceId;
    }

    public String getCodiceOtp() {
        return codiceOtp;
    }

    public void setCodiceOtp(String codiceOtp) {
        this.codiceOtp = codiceOtp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarrelloDTO carrelloDTO = (CarrelloDTO) o;
        if (carrelloDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carrelloDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarrelloDTO{" +
            "id=" + getId() +
            ", cancellato='" + isCancellato() + "'" +
            ", oggetto=" + getOggettoId() +
            ", oggetto='" + getOggettoNome() + "'" +
            ", codice=" + getCodiceId() +
            ", codice='" + getCodiceOtp() + "'" +
            "}";
    }
}

package it.contrader.autoship.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Magazzino entity.
 */
public class MagazzinoDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer capienza;

    private Boolean cancellato;

    private Long oggettoId;

    private String oggettoNome;
    
    private String oggettoDimensione;

    private Long codiceId;

    private String codiceOtp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
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

        MagazzinoDTO magazzinoDTO = (MagazzinoDTO) o;
        if (magazzinoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), magazzinoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MagazzinoDTO{" +
            "id=" + getId() +
            ", capienza=" + getCapienza() +
            ", cancellato='" + isCancellato() + "'" +
            ", oggetto=" + getOggettoId() +
            ", oggetto='" + getOggettoNome() + "'" +
            ", codice=" + getCodiceId() +
            ", codice='" + getCodiceOtp() + "'" +
            "}";
    }
}

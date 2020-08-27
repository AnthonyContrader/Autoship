package it.contrader.autoship.service.dto;

import java.io.Serializable;
import java.util.Objects;
import it.contrader.autoship.domain.enumeration.CodiceStato;

/**
 * A DTO for the Codice entity.
 */
public class CodiceDTO implements Serializable {

    private Long id;

    private String otp;

    private Long userId;

    private CodiceStato stato;

    private Boolean cancellato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CodiceStato getStato() {
        return stato;
    }

    public void setStato(CodiceStato stato) {
        this.stato = stato;
    }

    public Boolean isCancellato() {
        return cancellato;
    }

    public void setCancellato(Boolean cancellato) {
        this.cancellato = cancellato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CodiceDTO codiceDTO = (CodiceDTO) o;
        if (codiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), codiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CodiceDTO{" +
            "id=" + getId() +
            ", otp='" + getOtp() + "'" +
            ", userId=" + getUserId() +
            ", stato='" + getStato() + "'" +
            ", cancellato='" + isCancellato() + "'" +
            "}";
    }
}

package it.contrader.autoship.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

	public static final String SUPERUTENTE = "ROLE_SUPERUTENTE";

    public static final String AMMINISTRATORE = "ROLE_AMMINISTRATORE";

    public static final String CORRIERE = "ROLE_CORRIERE";

    public static final String UTENTE = "ROLE_UTENTE";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}

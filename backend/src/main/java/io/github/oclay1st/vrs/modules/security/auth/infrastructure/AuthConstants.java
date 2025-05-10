package io.github.oclay1st.vrs.modules.security.auth.infrastructure;

public final class AuthConstants {

    public static final String BASE_PATH = "/auth";

    public static final String TOKEN_PATH = "/token";

    public static final String TOKEN_REFRESH_PATH = "/token-refresh";

    public static final String REGISTRATION_PATH = "/registration";

    private AuthConstants() {
        throw new IllegalStateException("Auth Constants");
    }

}

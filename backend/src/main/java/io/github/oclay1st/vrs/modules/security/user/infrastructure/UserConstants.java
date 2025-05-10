package io.github.oclay1st.vrs.modules.security.user.infrastructure;

public final class UserConstants {

    public static final String BASE_PATH = "/users";

    public static final String ID_PATH = "/{id}";

    private UserConstants() {
        throw new IllegalStateException("User Constants");
    }
}

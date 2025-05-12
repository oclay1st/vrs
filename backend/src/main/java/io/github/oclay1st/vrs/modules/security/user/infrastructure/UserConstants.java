package io.github.oclay1st.vrs.modules.security.user.infrastructure;

public final class UserConstants {

    public static final String BASE_PATH = "/users";

    public static final String ID_PATH = "/{id}";

    public static final String PROFILE_PATH = "/profile";

    private UserConstants() {
        throw new IllegalStateException("User Constants");
    }
}

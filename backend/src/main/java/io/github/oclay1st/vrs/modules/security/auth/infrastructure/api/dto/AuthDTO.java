package io.github.oclay1st.vrs.modules.security.auth.infrastructure.api.dto;

import jakarta.validation.constraints.NotEmpty;

public class AuthDTO {

    private final String username;

    private final String password;

    public AuthDTO(@NotEmpty String username, @NotEmpty String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

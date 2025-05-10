package io.github.oclay1st.vrs.modules.security.auth.infrastructure.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record RefreshTokenDTO(@NotEmpty String refreshToken) {

}

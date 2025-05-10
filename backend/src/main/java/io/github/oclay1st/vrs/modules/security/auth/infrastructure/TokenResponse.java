package io.github.oclay1st.vrs.modules.security.auth.infrastructure;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class TokenResponse {
    private final String accessToken;

    private final String refreshToken;

    private final Long tokenLifeTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime refreshTokenExpirationTime;

    public TokenResponse(String accessToken, String refreshToken, Long tokenLifeTime,
            LocalDateTime refreshTokenExpirationTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenLifeTime = tokenLifeTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getTokenLifeTime() {
        return tokenLifeTime;
    }

    public LocalDateTime getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }

}

package io.github.oclay1st.vrs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import io.github.oclay1st.vrs.modules.security.auth.infrastructure.TokenResponse;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.repository.UserSecurity;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.repository.UserSecurityRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public final class JwtTokenProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer ";

    @Value("${vrs.security.jwt.token.secret-key:my-weak-secret-key-is-not-safe-to-use-on-production}")
    private String secretKey;

    @Value("${vrs.security.jwt.token.expire-time:3600000}")
    private long accessTokenExpireTime = 60 * 60 * 1000L; // 1h

    @Value("${vrs.security.jwt.refresh-token.expire-time:86400000}")
    private long refreshTokenExpireTime = 24 * 60 * 60 * 1000L; // 1d

    private final UserSecurityRepository userSecurityRepository;

    public JwtTokenProvider(UserSecurityRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }

    public TokenResponse createUserToken(String identity) {
        String accessToken = generateAccessToken(identity);
        String refreshToken = generateRefreshToken(identity);
        LocalDateTime refreshTokenExpireDateTime = LocalDateTime.now().plusSeconds(refreshTokenExpireTime / 1000);
        return new TokenResponse(accessToken, refreshToken, accessTokenExpireTime, refreshTokenExpireDateTime);
    }

    private String generateAccessToken(String identity) {
        Map<String, String> claims = Map.of();

        Date now = new Date();
        Date expire = new Date(now.getTime() + accessTokenExpireTime);

        return Jwts.builder().setClaims(claims).setSubject(identity).setIssuedAt(now).setExpiration(expire)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    private String generateRefreshToken(String identity) {
        Map<String, String> claims = Map.of();

        Date now = new Date();
        Date expire = new Date(now.getTime() + refreshTokenExpireTime);

        return Jwts.builder().setClaims(claims).setSubject(identity).setIssuedAt(now).setExpiration(expire)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    public String getIdentity(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build();
        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, IllegalArgumentException {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build();
        parser.parseClaimsJws(token);
        return true;
    }

    public Authentication getAuthentication(String token) {
        UserSecurity userDetails = userSecurityRepository.findByUsername(getIdentity(token))
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials"));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}

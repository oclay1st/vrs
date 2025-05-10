package io.github.oclay1st.vrs.modules.security.auth.infrastructure.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.oclay1st.vrs.config.JwtTokenProvider;
import io.github.oclay1st.vrs.modules.common.exceptions.UnauthorizedException;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.AuthConstants;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.TokenResponse;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.api.dto.AuthDTO;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.api.dto.RefreshTokenDTO;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.repository.UserSecurity;
import io.github.oclay1st.vrs.modules.security.user.domain.service.UserService;
import io.github.oclay1st.vrs.modules.security.user.infrastructure.api.dto.RegisterUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth")
@RestController
@RequestMapping(path = AuthConstants.BASE_PATH)
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    public AuthController(UserService userService, AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(path = AuthConstants.TOKEN_PATH)
    @Operation(summary = "Auth user by token")
    @ApiResponse(description = " An user token config")
    public TokenResponse createToken(@Valid @RequestBody AuthDTO authDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
            UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
            return tokenProvider.createUserToken(userSecurity.getUsername());
        } catch (DisabledException e) {
            throw new UnauthorizedException("User disabled");
        } catch (Exception e) {
            throw new UnauthorizedException("Bad Credentials");
        }

    }

    @PostMapping(path = AuthConstants.TOKEN_REFRESH_PATH)
    @Operation(summary = "Refresh superuser token")
    @ApiResponse(description = "An superuser token config")
    public TokenResponse refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        throw new IllegalStateException("Not implemented yet");
    }

    @PostMapping(path = AuthConstants.REGISTRATION_PATH)
    @Operation(summary = "Register a new user")
    public void register(@Valid @RequestBody RegisterUserDTO input) {
        userService.register(input.toCommand());
    }

}

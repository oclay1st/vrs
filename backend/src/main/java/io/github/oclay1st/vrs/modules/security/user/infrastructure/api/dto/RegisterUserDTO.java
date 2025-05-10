package io.github.oclay1st.vrs.modules.security.user.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.security.user.domain.command.RegisterUserCommand;
import jakarta.validation.constraints.NotEmpty;

public record RegisterUserDTO(@NotEmpty String username, @NotEmpty String password, @NotEmpty String name,
        @NotEmpty String lastName) {

    public RegisterUserCommand toCommand() {
        return new RegisterUserCommand(password, username, name, lastName, true);
    }

}

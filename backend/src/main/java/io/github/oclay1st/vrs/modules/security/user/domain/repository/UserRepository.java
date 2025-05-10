package io.github.oclay1st.vrs.modules.security.user.domain.repository;

import io.github.oclay1st.vrs.modules.security.user.domain.command.RegisterUserCommand;

public interface UserRepository {

    void create(RegisterUserCommand command);

    boolean existByUsername(String username);

}

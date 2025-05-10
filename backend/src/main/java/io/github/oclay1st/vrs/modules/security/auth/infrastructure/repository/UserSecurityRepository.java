package io.github.oclay1st.vrs.modules.security.auth.infrastructure.repository;

import java.util.Optional;

public interface UserSecurityRepository {

    Optional<UserSecurity> findByUsername(String username);

}

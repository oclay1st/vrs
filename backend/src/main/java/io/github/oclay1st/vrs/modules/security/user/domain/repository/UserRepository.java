package io.github.oclay1st.vrs.modules.security.user.domain.repository;

import java.util.Optional;

import io.github.oclay1st.vrs.modules.common.Page;
import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.security.user.domain.command.RegisterUserCommand;
import io.github.oclay1st.vrs.modules.security.user.domain.criteria.UserCriteria;
import io.github.oclay1st.vrs.modules.security.user.domain.projection.UserView;

public interface UserRepository {

    void create(RegisterUserCommand command);

    boolean existByUsername(String username);

    Optional<UserView> findByUsername(String username);

    Page<UserView> findByCriteria(UserCriteria criteria, PageCriteria pageCriteria);
}

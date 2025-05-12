package io.github.oclay1st.vrs.modules.security.user.domain.projection;

import java.time.LocalDateTime;

public record UserDetailsView(Long id, String username, String name, String lastName, boolean active,
        LocalDateTime createdAt, LocalDateTime updatedAt) {

}

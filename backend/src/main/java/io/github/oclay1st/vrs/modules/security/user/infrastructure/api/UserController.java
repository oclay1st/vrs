package io.github.oclay1st.vrs.modules.security.user.infrastructure.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.oclay1st.vrs.modules.security.user.domain.service.UserService;
import io.github.oclay1st.vrs.modules.security.user.infrastructure.UserConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = UserConstants.BASE_PATH)
@Tag(name = "User")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    public final UserService uService;

    public UserController(UserService uService) {
        this.uService = uService;
    }

}

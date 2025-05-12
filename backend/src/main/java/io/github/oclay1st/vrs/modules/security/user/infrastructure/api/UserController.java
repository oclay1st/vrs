package io.github.oclay1st.vrs.modules.security.user.infrastructure.api;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.oclay1st.vrs.modules.common.Page;
import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.security.auth.infrastructure.repository.UserSecurity;
import io.github.oclay1st.vrs.modules.security.user.domain.criteria.UserCriteria;
import io.github.oclay1st.vrs.modules.security.user.domain.projection.UserView;
import io.github.oclay1st.vrs.modules.security.user.domain.service.UserService;
import io.github.oclay1st.vrs.modules.security.user.infrastructure.UserConstants;
import io.github.oclay1st.vrs.modules.security.user.infrastructure.api.dto.UserQueryParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = UserConstants.BASE_PATH)
@Tag(name = "User")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get the user list")
    public Page<UserView> findUsers(@ParameterObject UserQueryParams params) {
        UserCriteria userCriteria = params.toUserCriteria();
        PageCriteria pageCriteria = params.toPageCriteria();
        return userService.findAllByCriteria(userCriteria, pageCriteria);
    }

    @GetMapping(UserConstants.PROFILE_PATH)
    @Operation(summary = "Get the current user profile")
    public UserView getProfile(@AuthenticationPrincipal UserSecurity userSecurity) {
        return userService.findByUsername(userSecurity.getUsername());
    }

}

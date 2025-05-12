package io.github.oclay1st.vrs.modules.security.user.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.security.user.domain.criteria.UserCriteria;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserQueryParams {

    private String search;

    @Parameter(name = "page", schema = @Schema(defaultValue = "1"))
    private Integer pageNo = 1;

    @Parameter(name = "size", schema = @Schema(defaultValue = "20"))
    private Integer pageSize = 20;

    @JsonCreator
    public UserQueryParams(String search, Integer pageNo, Integer pageSize) {
        this.search = search;
        this.pageNo = Objects.requireNonNullElse(pageNo, this.pageNo);
        this.pageSize = Objects.requireNonNullElse(pageSize, this.pageSize);
    }

    public UserCriteria toUserCriteria() {
        return new UserCriteria(search);
    }

    public PageCriteria toPageCriteria() {
        return new PageCriteria(pageNo, pageSize);
    }

}

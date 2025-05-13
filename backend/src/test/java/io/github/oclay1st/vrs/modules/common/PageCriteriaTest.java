package io.github.oclay1st.vrs.modules.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PageCriteriaTest {

    @Test
    void validOffset() {
        var pageCriteria = new PageCriteria(3, 40);
        assertThat(pageCriteria.offset()).isEqualTo(2 * 40L);
    }

}

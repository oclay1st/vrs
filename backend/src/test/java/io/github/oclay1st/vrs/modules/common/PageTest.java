package io.github.oclay1st.vrs.modules.common;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;

class PageTest {

    @Test
    void numberOfElementsShouldEqualToTheItemSize() {
        var items = List.of("item1", "item1", "item1", "item1");
        var page = new Page<>(items, 1, 8, 20);
        assertThat(page.getItems()).isNotEmpty().hasSize(items.size());
    }

    @Test
    void itemListMustBeEmpty() {
        var page = new Page<>(null, 1, 9, 20);
        assertThat(page.getItems()).isEmpty();
    }

    @Test
    void whenItemListSizeIsGreaterThanTheTotalElementsThenThrowValidationException() {
        var items = List.of("item1", "item2", "item3");
        assertThatThrownBy(() -> new Page<>(items, 1, 2, 2))
                .isInstanceOf(ValidationException.class);
    }

}

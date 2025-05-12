package io.github.oclay1st.vrs.modules.common;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;

public class Page<T> {

    private final int page;

    private final int size;

    private final long totalElements;

    private final List<T> items;

    public Page(List<T> items, int page, int size, long totalElements) {
        this.items = Objects.nonNull(items) ? items : Collections.emptyList();
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        validate();
    }

    public <U> Page<U> map(Function<T, U> mapFunction) {
        var newItems = items.stream().map(mapFunction).toList();
        return new Page<>(newItems, page, size, totalElements);
    }

    public Stream<T> stream() {
        return items.stream();
    }

    private void validate() {
        if (items.size() > totalElements) {
            throw new ValidationException("Total elements must be greater than the page item size");
        }
    }

    public static <T> Page<T> empty(int page, int size) {
        return new Page<>(Collections.emptyList(), page, size, 0);
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getNumberOfElements() {
        return items.size();
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return size == 0 ? 1 : (int) Math.ceil(totalElements / (double) size);
    }

    public List<T> getItems() {
        return items;
    }
}

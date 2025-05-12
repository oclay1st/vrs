package io.github.oclay1st.vrs.modules.common;

public record PageCriteria(int page, int size) {

    private static final int MAX_PAGE_SIZE = 200;

    private static final int DEFAULT_PAGE_SIZE = 20;

    public PageCriteria {
        page = Math.max(page, 1);
        size = size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : Math.max(size, 0);
    }

    public PageCriteria() {
        this(1, DEFAULT_PAGE_SIZE);
    }

    public long offset() {
        return ((long) page - 1) * size;
    }

}

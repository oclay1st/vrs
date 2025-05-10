package io.github.oclay1st.vrs.modules.common.exceptions;

public abstract class BaseException extends RuntimeException {

    private final ErrorType errorType;

    protected BaseException(String message, Throwable throwable) {
        super(message, throwable);
        errorType = ErrorType.UNEXPECTED;
    }

    protected BaseException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}

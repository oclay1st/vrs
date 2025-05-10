package io.github.oclay1st.vrs.modules.common.exceptions;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(message, ErrorType.UNAUTHORIZED);
    }

}

package io.github.oclay1st.vrs.modules.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationException extends BaseException {

    private final Logger logger = LoggerFactory.getLogger(ValidationException.class);

    public ValidationException(String message) {
        super(message, ErrorType.INVALID_DATA);
    }

    public ValidationException(String message, Exception ex) {
        super(message, ErrorType.INVALID_DATA);
        logger.error("Invalid data", ex);
    }

}

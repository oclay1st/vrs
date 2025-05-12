package io.github.oclay1st.vrs.modules.common.exceptions;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message) {
        super(message, ErrorType.RESOURCE_NOT_FOUND);
    }

}

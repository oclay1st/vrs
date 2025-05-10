package io.github.oclay1st.vrs.modules.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    private HttpStatus resolveHttpStatus(ErrorType errorType) {
        return switch (errorType) {
            case UNSUPPORTED_TYPE -> HttpStatus.UNSUPPORTED_MEDIA_TYPE;
            case RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case PRECONDITION_FAILED -> HttpStatus.PRECONDITION_FAILED;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case INVALID_DATA -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private Map<String, Object> resolveBody(String message, ErrorType errorType) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        body.put("errorType", errorType);
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processApplicationErrors(BaseException exception) {
        HttpStatus status = resolveHttpStatus(exception.getErrorType());
        Map<String, Object> body = resolveBody(exception.getMessage(), exception.getErrorType());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, Object> body = resolveBody("Validation Error", ErrorType.INVALID_DATA);
        Map<String, List<String>> errors = exception.getBindingResult().getAllErrors().stream()
                .map(FieldError.class::cast).collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toList())));
        body.put("errors", errors);
        HttpStatus status = resolveHttpStatus(ErrorType.INVALID_DATA);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processAuthenticationErrors(AuthenticationException exception) {
        HttpStatus status = resolveHttpStatus(ErrorType.UNAUTHORIZED);
        Map<String, Object> body = resolveBody(exception.getMessage(), ErrorType.UNAUTHORIZED);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> processOtherErrors(Exception exception) {
        HttpStatus status = resolveHttpStatus(ErrorType.UNEXPECTED);
        Map<String, Object> body = resolveBody("Internal server error",
                ErrorType.UNEXPECTED);
        logger.error("Internal server exception: ", exception);
        return ResponseEntity.status(status).body(body);
    }

}

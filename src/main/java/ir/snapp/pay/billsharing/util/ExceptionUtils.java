package ir.snapp.pay.billsharing.util;

import ir.snapp.pay.billsharing.exception.ServiceException;
import ir.snapp.pay.billsharing.dto.response.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
public final class ExceptionUtils {

    private ExceptionUtils() {}

    public static String getRequestPathFromWebRequest(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    public static ExceptionResponseDto makeExceptionResponseFromBindException(BindException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> details.put(e.getField(), e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(e -> details.put(e.getObjectName(), e.getDefaultMessage()));
        exceptionResponse.setDetails(details);
        exceptionResponse.setPath(getRequestPathFromWebRequest(request));
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return exceptionResponse;
    }

    public static ExceptionResponseDto makeExceptionResponseFromConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        Map<String, String> details = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(e -> details.put(e.getPropertyPath().toString(), e.getMessage()));
        exceptionResponse.setDetails(details);
        exceptionResponse.setPath(getRequestPathFromWebRequest(request));
        exceptionResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return exceptionResponse;
    }

    public static ExceptionResponseDto makeExceptionResponse(String message, Map<String, String> errors, String path) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        exceptionResponse.setError(message);
        exceptionResponse.setDetails(errors);
        exceptionResponse.setPath(path);
        return exceptionResponse;
    }

    public static ExceptionResponseDto convertToExceptionResponse(ServiceException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        exceptionResponse.setError(ex.getMessage());
        exceptionResponse.setDetails(ex.getDetails());
        exceptionResponse.setPath(getRequestPathFromWebRequest(request));
        return exceptionResponse;
    }
}

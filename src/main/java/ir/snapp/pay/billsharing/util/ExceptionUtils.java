package ir.snapp.pay.billsharing.util;

import ir.snapp.pay.billsharing.exception.ServiceException;
import ir.snapp.pay.billsharing.dto.response.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Map;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
public final class ExceptionUtils {

    private ExceptionUtils() {}

    public static ExceptionResponseDto makeExceptionResponseFromBindException(BindException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> exceptionResponse.addError(e.getField(), e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(e -> exceptionResponse.addError(e.getObjectName(), e.getDefaultMessage()));
        exceptionResponse.setPath(request.getContextPath());
        exceptionResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return exceptionResponse;
    }

    public static ExceptionResponseDto makeExceptionResponseFromConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        ex.getConstraintViolations()
                .forEach(e -> exceptionResponse.addError(e.getPropertyPath().toString(), e.getMessage()));
        exceptionResponse.setPath(request.getContextPath());
        exceptionResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return exceptionResponse;
    }

    public static ExceptionResponseDto makeExceptionResponse(String message, Map<String, String> errors, String path) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        exceptionResponse.setMessage(message);
        exceptionResponse.setErrors(errors);
        exceptionResponse.setPath(path);
        return exceptionResponse;
    }

    public static ExceptionResponseDto convertToExceptionResponse(ServiceException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto();
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setErrors(ex.getDetails());
        exceptionResponse.setPath(request.getContextPath());
        return exceptionResponse;
    }
}

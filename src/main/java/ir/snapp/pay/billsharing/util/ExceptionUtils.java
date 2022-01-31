package ir.snapp.pay.billsharing.util;

import ir.snapp.pay.billsharing.exception.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
public final class ExceptionUtils {

    private ExceptionUtils() {}

    public static ExceptionResponseDTO makeExceptionResponseFromBindException(BindException ex, WebRequest request) {
        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> exceptionResponse.addError(e.getField(), e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors()
                .forEach(e -> exceptionResponse.addError(e.getObjectName(), e.getDefaultMessage()));
        exceptionResponse.setPath(request.getContextPath());
        exceptionResponse.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return exceptionResponse;
    }

    public static ExceptionResponseDTO makeExceptionResponse(String message, Map<String, String> errors, String path) {
        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO();
        exceptionResponse.setMessage(message);
        exceptionResponse.setErrors(errors);
        exceptionResponse.setPath(path);
        return exceptionResponse;
    }
}

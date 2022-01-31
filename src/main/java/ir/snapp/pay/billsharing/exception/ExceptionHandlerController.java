package ir.snapp.pay.billsharing.exception;

import ir.snapp.pay.billsharing.exception.dto.ExceptionResponseDTO;
import ir.snapp.pay.billsharing.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ExceptionUtils.makeExceptionResponseFromBindException(ex, request), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ExceptionUtils.makeExceptionResponseFromBindException(ex, request), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionUtils.makeExceptionResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        Map.of(Objects.requireNonNull(ex.getPropertyName()), "required type is: " + ex.getRequiredType()),
                        request.getContextPath()),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionUtils.makeExceptionResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        Map.of(Objects.requireNonNull(ex.getParameterName()), "parameter is missing"),
                        request.getContextPath()),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionUtils.makeExceptionResponse(
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        Map.of(ex.getHttpMethod() + " " + ex.getRequestURL(), "no handler found"),
                        request.getContextPath()),
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionUtils.makeExceptionResponse(
                        HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
                        Map.of(ex.getMethod(), "method is not supported"),
                        request.getContextPath()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mediaType = ex.getContentType() != null ? ex.getContentType().getType() : "NULL";
        return new ResponseEntity<>(
                ExceptionUtils.makeExceptionResponse(
                        HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
                        Map.of(mediaType, "media type is not supported"),
                        request.getContextPath()),
                HttpStatus.METHOD_NOT_ALLOWED);
    }


}

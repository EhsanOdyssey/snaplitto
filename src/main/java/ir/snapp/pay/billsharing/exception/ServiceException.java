package ir.snapp.pay.billsharing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus status;
    private Map<String, String> details;

    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ServiceException(String message, HttpStatus status, Map<String, String> details) {
        super(message);
        this.status = status;
        this.details = details;
    }
}

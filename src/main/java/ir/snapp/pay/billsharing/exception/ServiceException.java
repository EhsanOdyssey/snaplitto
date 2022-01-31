package ir.snapp.pay.billsharing.exception;

import org.springframework.http.HttpStatus;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus status;
}

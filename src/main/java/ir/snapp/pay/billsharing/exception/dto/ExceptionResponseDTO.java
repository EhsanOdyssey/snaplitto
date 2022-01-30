package ir.snapp.pay.billsharing.exception.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
@Getter
public class ExceptionResponseDTO implements Serializable {

    private final String path;
    private final String message;
    private final List<String> errors;

    public ExceptionResponseDTO(final String path, final String message, final List<String> errors) {
        this.path = path;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionResponseDTO(final String path, final String message, final String... errors) {
        this.path = path;
        this.message = message;
        this.errors = Arrays.asList(errors);
    }
}

package ir.snapp.pay.billsharing.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponseDto implements Serializable {

    private String path;
    private String message;
    private Map<String, String> errors;

    public ExceptionResponseDto(final String path, final String message, final Map<String, String> errors) {
        this.path = path;
        this.message = message;
        this.errors = errors;
    }

    public void addError(String name, String error) {
        if (errors == null) {
            this.errors = new HashMap<>();
        }
        this.errors.put(name, error);
    }
}

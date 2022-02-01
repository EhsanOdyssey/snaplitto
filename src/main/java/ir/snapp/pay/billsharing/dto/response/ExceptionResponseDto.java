package ir.snapp.pay.billsharing.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExceptionResponseDto implements Serializable {

    private Date timestamp;
    private String path;
    private String message;
    private Map<String, String> errors;

    public ExceptionResponseDto() {
        this.timestamp = new Date();
    }

    public void addError(String name, String error) {
        if (errors == null) {
            this.errors = new HashMap<>();
        }
        this.errors.put(name, error);
    }
}

package ir.snapp.pay.billsharing.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
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
    private String error;
    private Object details;

    public ExceptionResponseDto() {
        this.timestamp = new Date();
    }

//    public void addDetail(String name, String error) {
//        if (details == null) {
//            this.details = new HashMap<>();
//        }
//        this.details.put(name, error);
//    }
}

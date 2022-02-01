package ir.snapp.pay.billsharing.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Tue 01 Feb 2022
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto<T> implements Serializable {
    private String status;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> ResponseDto<T> ok() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.OK.getReasonPhrase());
        return response;
    }

    public static <T> ResponseDto<T> badRequest() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return response;
    }

    public static <T> ResponseDto<T> notFound() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
        return response;
    }

    public static <T> ResponseDto<T> unauthorized() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        return response;
    }

    public static <T> ResponseDto<T> internalError() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageMetadata {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int number;

        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }
}

package ir.snapp.pay.billsharing.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ir.snapp.pay.billsharing.validator.Phone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest implements Serializable {
    @NotEmpty
    private String id;
    @NotEmpty
    @Phone
    private String username;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}

package ir.snapp.pay.billsharing.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @NotEmpty(message = "{constrains.NotEmpty.message}")
    private String id;
    @NotEmpty(message = "{constrains.NotEmpty.message}")
    private String username;
    @NotEmpty(message = "{constrains.NotEmpty.message}")
    private String firstName;
    @NotEmpty(message = "{constrains.NotEmpty.message}")
    private String lastName;
}

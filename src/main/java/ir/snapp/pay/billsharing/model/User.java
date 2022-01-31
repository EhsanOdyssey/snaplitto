package ir.snapp.pay.billsharing.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user",
        indexes = @Index(
                name = "indx_user_username",
                columnList = "username",
                unique = true
        )
)
public class User {
    @Id
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @NotBlank
    private String username;
}

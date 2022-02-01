package ir.snapp.pay.billsharing.dto.mapper;

import ir.snapp.pay.billsharing.dto.model.UserDto;
import ir.snapp.pay.billsharing.model.User;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Tue 01 Feb 2022
 */
@Component
public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }
}

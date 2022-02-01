package ir.snapp.pay.billsharing.service;

import ir.snapp.pay.billsharing.dto.model.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Mon 31 Jan 2022
 */
public interface UserService {
    UserDto createUser(UserDto userDto);
    Page<UserDto> getUsers(Pageable pageable);
    UserDto findByUsername(String username);
    UserDto updateUser(UserDto userDto);
    void deleteUser(String username);
}

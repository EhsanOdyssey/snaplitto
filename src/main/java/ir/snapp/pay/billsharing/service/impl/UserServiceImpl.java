package ir.snapp.pay.billsharing.service.impl;

import ir.snapp.pay.billsharing.dto.mapper.UserMapper;
import ir.snapp.pay.billsharing.dto.model.UserDto;
import ir.snapp.pay.billsharing.exception.ExceptionThrower;
import ir.snapp.pay.billsharing.model.User;
import ir.snapp.pay.billsharing.repository.UserRepository;
import ir.snapp.pay.billsharing.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Tue 01 Feb 2022
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        List<UserDto> result = this.userRepository.findAll(pageable).stream().map(UserMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            user = new User()
                    .setId(userDto.getId())
                    .setUsername(userDto.getUsername())
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName());
            return UserMapper.toDto(this.userRepository.save(user));
        }
        throw exception(HttpStatus.CONFLICT, "user.duplicate", userDto.getUsername());
    }

    @Override
    public UserDto findByUsername(String username) {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByUsername(username));
        if (user.isPresent()) {
            return this.modelMapper.map(user.get(), UserDto.class);
        }
        throw exception(HttpStatus.NOT_FOUND, "user.not.found", username);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByUsername(userDto.getUsername()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName());
            return UserMapper.toDto(this.userRepository.save(userModel));
        }
        throw exception(HttpStatus.NOT_FOUND, "user.not.found", userDto.getUsername());
    }

    @Override
    public void deleteUser(String username) {
        Optional<User> user = Optional.ofNullable(this.userRepository.findByUsername(username));
        if (user.isEmpty()) {
            throw exception(HttpStatus.NOT_FOUND, "user.not.found", username);
        }
        this.userRepository.delete(user.get());
    }

    private RuntimeException exception(HttpStatus status, String messageKey, String... args) {
        return ExceptionThrower.throwServiceException(status, messageKey, args);
    }
}

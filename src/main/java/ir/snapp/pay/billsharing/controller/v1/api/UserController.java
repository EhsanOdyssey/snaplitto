package ir.snapp.pay.billsharing.controller.v1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.snapp.pay.billsharing.controller.v1.request.UpdateUserProfileRequest;
import ir.snapp.pay.billsharing.controller.v1.request.UserSignupRequest;
import ir.snapp.pay.billsharing.dto.model.UserDto;
import ir.snapp.pay.billsharing.dto.response.ResponseDto;
import ir.snapp.pay.billsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Fri 28 Jan 2022
 */
@Tag(name = "User APIs", description = "Operations as user management in snaplitto application. This part should be separated microservice.")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all user API with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful response"),
    })
    @GetMapping
    public ResponseDto<?> getUsers(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return ResponseDto.ok().setPayload(
                this.userService.getUsers(PageRequest.of(page, size))
        );
    }

    @Operation(summary = "Signup new user API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful response"),
            @ApiResponse(responseCode = "409", description = "duplicate user exception")
    })
    @PostMapping("signup")
    public ResponseDto<?> signup(@RequestBody @Valid UserSignupRequest signupRequest) {
        return ResponseDto.ok().setPayload(
                this.userService.createUser(signupRequestToDto(signupRequest))
        );
    }

    @Operation(summary = "Get user by username API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful response"),
            @ApiResponse(responseCode = "404", description = "user not found exception")
    })
    @GetMapping("{username}")
    public ResponseDto<?> getUserByUsername(@PathVariable("username") String username) {
        return ResponseDto.ok().setPayload(this.userService.findByUsername(username));
    }

    @Operation(summary = "Update user profile API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful response"),
            @ApiResponse(responseCode = "404", description = "user not found exception")
    })
    @PutMapping("{username}")
    public ResponseDto<?> updateProfile(@PathVariable("username") String username,
                                        @RequestBody @Valid UpdateUserProfileRequest updateProfile) {
        return ResponseDto.ok().setPayload(
                this.userService.updateUser(updateProfileToDto(username, updateProfile))
        );
    }

    private UserDto signupRequestToDto(UserSignupRequest signupRequest) {
        return new UserDto()
                .setId(signupRequest.getId())
                .setUsername(signupRequest.getUsername())
                .setFirstName(signupRequest.getFirstName())
                .setLastName(signupRequest.getLastName());
    }

    private UserDto updateProfileToDto(String username, UpdateUserProfileRequest updateProfile) {
        return new UserDto()
                .setUsername(username)
                .setFirstName(updateProfile.getFirstName())
                .setLastName(updateProfile.getLastName());
    }
}

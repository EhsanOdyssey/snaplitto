package ir.snapp.pay.billsharing.controller.v1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
                this.userService.getUsers(
                        PageRequest.of(page, size)
                )
        );
    }

    @Operation(summary = "Create user API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful response"),
            @ApiResponse(responseCode = "409", description = "duplicate user exception")
    })
    @PostMapping("signup")
    public ResponseDto<?> signup(@RequestBody @Valid UserSignupRequest signupRequest) {
        return ResponseDto.ok().setPayload(signupUser(signupRequest));
    }



    private UserDto signupUser(UserSignupRequest signupRequest) {
        UserDto userDto = new UserDto()
                .setId(signupRequest.getId())
                .setUsername(signupRequest.getUsername())
                .setFirstName(signupRequest.getFirstName())
                .setLastName(signupRequest.getLastName());
        return this.userService.createUser(userDto);
    }
}

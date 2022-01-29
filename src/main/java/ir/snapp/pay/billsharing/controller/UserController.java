package ir.snapp.pay.billsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:ehsan.odyssey@gmail.com">EhsanOdyssey</a>
 * @project snaplitto
 * @date Fri 28 Jan 2022
 */
@Tag(name = "User APIs", description = "The user operations as user management")
@RestController
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "Test API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful response")
    })
    @GetMapping("test")
    public Map<String, String> testUserEndpoint() {
        Map<String, String> result = new HashMap<>();
        result.put("username", "sample");
        result.put("active", Boolean.TRUE.toString());
        return result;
    }
}

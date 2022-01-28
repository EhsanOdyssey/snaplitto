package ir.snapp.pay.billsharing.controller;

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
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("test")
    public Map<String, String> testUserEndpoint() {
        Map<String, String> result = new HashMap<>();
        result.put("username", "sample");
        result.put("active", Boolean.TRUE.toString());
        return result;
    }
}

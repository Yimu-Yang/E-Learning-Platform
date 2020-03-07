package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Map;

//@Controller
@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public HttpEntity register(@RequestBody Map<String, String> payload) {
        if (!userService.register(payload)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public HttpEntity login(@RequestBody Map<String, String> payload) {
        if (!userService.login(payload)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/logout")
    public HttpEntity logout() {
        if (!userService.logout()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

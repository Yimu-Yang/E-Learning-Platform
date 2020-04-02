package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.DTO.ELearningUserDTO;
import cmpe295.hungwenli.elearning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Map;

import static cmpe295.hungwenli.elearning.security.SecurityConstants.HEADER_STRING;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/token", produces = "application/json")
    public HttpEntity<ELearningUserDTO> token(Authentication authentication) {
        System.out.println("In token service");
        System.out.println(authentication.getName());
        return new ResponseEntity<>(new ELearningUserDTO(authentication.getName(), ""), HttpStatus.OK);
    }

    @PostMapping(path = "/signup", produces = "application/json")
    public HttpEntity<Map<String, String>> signup(@RequestBody ELearningUserDTO user) {

        String username = user.getEmail();
        String password = user.getPassword();

        Map<String, String> token = userService.signup(username, password);

        if (token == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_STRING, token.get("token"));

        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(path = "/signin", produces = "application/json")
    public HttpEntity<Map<String, String>> signin(@RequestBody ELearningUserDTO user) {

        System.out.println("In signin service");

        String username = user.getEmail();
        String password = user.getPassword();

        Map<String, String> token = userService.signin(username, password);

        if (token == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_STRING, token.get("token"));

        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/signout", produces = "application/json")
    public HttpEntity<String> signout() {
        return new ResponseEntity<>("logout success.", HttpStatus.OK);
    }
}

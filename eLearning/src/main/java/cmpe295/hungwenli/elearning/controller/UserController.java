package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.DTO.ELearningUserDTO;
import cmpe295.hungwenli.elearning.model.Response;
import cmpe295.hungwenli.elearning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static cmpe295.hungwenli.elearning.security.SecurityConstants.HEADER_STRING;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

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

    @GetMapping(path = "/token", produces = "application/json")
    public HttpEntity<ELearningUserDTO> token(Authentication authentication) {
        System.out.println("In token service");
        System.out.println(authentication.getName());
        return new ResponseEntity<>(new ELearningUserDTO(authentication.getName(), ""), HttpStatus.OK);
    }

//    @PostMapping(path = "/register")
//    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        if (!userService.register(request)) {
//            response.sendRedirect("/");
//            return;
//        }
//        response.sendRedirect("/content");
//    }

//    @PostMapping(path = "/login")
//    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        userService.login(request, response);
//    }
//
//    @GetMapping(path = "/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        userService.logout(request, response);
//    }
//
//    @GetMapping(path = "/username")
//    public @ResponseBody
//    Response getUsername(HttpServletRequest request, HttpServletResponse response) {
//        return userService.getUsername(request, response);
//    }

}

package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.Response;
import cmpe295.hungwenli.elearning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!userService.register(request)) {
            response.sendRedirect("/");
            return;
        }
        response.sendRedirect("/content");
    }

    @PostMapping(path = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.login(request, response);
    }

    @GetMapping(path = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.logout(request, response);
    }

    @GetMapping(path = "/username")
    public @ResponseBody
    Response getUsername(HttpServletRequest request, HttpServletResponse response) {
        return userService.getUsername(request, response);
    }

}

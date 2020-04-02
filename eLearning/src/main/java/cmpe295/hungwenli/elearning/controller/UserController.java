package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.Response;
import cmpe295.hungwenli.elearning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    public String register(HttpServletRequest request, Model model) {
        if (!userService.register(request)) {
            model.addAttribute("error_message", "registration fail");
            return "error";
        }
        return "courseHome";
    }

    @PostMapping(path = "/login")
    public String login(HttpServletRequest request, Model model) {
        if (!userService.login(request)) {
            model.addAttribute("error_message", "login fail");
            return "error";
        }
        return "courseHome";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        if (!userService.logout(request)) {
            model.addAttribute("error_message", "logout fail");
            return "error";
        }
        return "index";
    }

    @GetMapping(path = "/username")
    public @ResponseBody Response getUsername(HttpServletRequest request) {
        return userService.getUsername(request);
    }

}

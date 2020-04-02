package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.Response;
import cmpe295.hungwenli.elearning.service.CourseService;
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

    @Autowired
    CourseService courseService;

    @PostMapping(path = "/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String res = userService.register(request);
        if (!res.equals("success")) {
            model.addAttribute("error_message", res);
            return "error";
        }
        response.sendRedirect("/home");
        return null;
    }

    @PostMapping(path = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String res = userService.login(request);
        if (!res.equals("success")) {
            model.addAttribute("error_message", res);
            return "error";
        }
        response.sendRedirect("/home");
        return null;
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String res = userService.logout(request);
        if (!res.equals("success")) {
            model.addAttribute("error_message", res);
            return "error";
        }
        response.sendRedirect("/");
        return null;
    }

    @GetMapping(path = "/username")
    public @ResponseBody Response getUsername(HttpServletRequest request) {
        return userService.getUsername(request);
    }

}

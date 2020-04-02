package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.util.Utility;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping
public class PageController {

    @GetMapping(path = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(path = "/home")
    public String courseHome(HttpServletRequest request, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        return "courseHome";
    }

    @GetMapping(path = "/chat")
    public String chat(HttpServletRequest request, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        return "chat";
    }

    @GetMapping(path = "/react")
    public String reactDemo(HttpServletRequest request, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        return "react";
    }

}

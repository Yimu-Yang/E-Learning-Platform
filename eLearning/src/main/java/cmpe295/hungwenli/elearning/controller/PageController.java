package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.util.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class PageController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/home")
    public String courseHome(HttpServletRequest request, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        return "home";
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

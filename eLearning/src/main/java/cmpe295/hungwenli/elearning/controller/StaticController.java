package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.util.Utility;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class StaticController {

    @GetMapping(path = "/", produces = "text/html")
    public @ResponseBody HttpEntity index() {
        return new ResponseEntity<>(Utility.readFile("login.html"), HttpStatus.OK);
    }

    @GetMapping(path = "/content", produces = "text/html")
    public @ResponseBody HttpEntity realContents(HttpServletRequest request) {
        if (Utility.checkLoggedIn(request)) {
            return new ResponseEntity<>(Utility.readFile("content.html"), HttpStatus.OK);
        }
        return new ResponseEntity<>(Utility.readFile("error.html"), HttpStatus.OK);
    }

}

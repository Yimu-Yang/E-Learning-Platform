package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.Course;
import cmpe295.hungwenli.elearning.service.CourseService;
import cmpe295.hungwenli.elearning.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(path = "/find", produces = "application/json")
    public @ResponseBody HttpEntity findAllCourses(@RequestBody Map<String, String> payload) {
        List<Course> courses = courseService.findCoursesByName(payload.get("course_name"));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping(path = "/course")
    public String getCourseInfo(HttpServletRequest request, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        String courseName = request.getParameter("course_name");
        model.addAttribute("course_name", courseName);
        return "individualCourse";
    }

}

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
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @PostMapping(path = "/result")
    public String searchResult(HttpServletRequest request, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        // wire into page rank algorithm
        String courseName = request.getParameter("course_name");
        List<Course> courses = courseService.pageRankCourseSearch(courseName);
        model.addAttribute("courses", courses);
        return "results";
    }

    @GetMapping(path = "/course")
    public String courseInfo(HttpServletRequest request, @RequestParam(name = "id") String courseId, Model model) {
        if (!Utility.checkLoggedIn(request)) {
            model.addAttribute("error_message", "Please login first!");
            return "error";
        }
        Optional<Course> courses = courseService.findCoursesById(Integer.parseInt(courseId));
        Course course = courses.get();
        model.addAttribute("course", course);
        return "courseInfo";
    }

}

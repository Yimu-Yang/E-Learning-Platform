package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.Course;
import cmpe295.hungwenli.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(path = "/find", produces = "application/json")
    public HttpEntity findAllCourses(@RequestBody Map<String, String> payload) {
        List<Course> courses = courseService.findCoursesByName(payload.get("course_name"));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}

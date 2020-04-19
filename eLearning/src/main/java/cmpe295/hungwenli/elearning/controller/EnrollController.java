package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.DTO.CourseDTO;
import cmpe295.hungwenli.elearning.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class EnrollController {

    @Autowired
    EnrollService enrollService;

    @GetMapping(path = "/enroll", produces = "application/json")
    public HttpEntity<String> enroll(@RequestParam Map<String, String> param) {

        String userName = String.valueOf(param.get("user_name"));
        Long courseId = Long.valueOf(param.get("course_id"));

        enrollService.enroll(userName, courseId);

        return new ResponseEntity<>("Enrolled successfully!", HttpStatus.OK);
    }

    @GetMapping(path = "/myEnrolls", produces = "application/json")
    public HttpEntity<List<CourseDTO>> myCourse(@RequestParam Map<String, String> param) {

        String userName = String.valueOf(param.get("user_name"));

        List<CourseDTO> res = enrollService.myCourse(userName);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

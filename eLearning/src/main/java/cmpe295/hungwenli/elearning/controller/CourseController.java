package cmpe295.hungwenli.elearning.controller;

import cmpe295.hungwenli.elearning.model.Course;
import cmpe295.hungwenli.elearning.model.DTO.CourseDTO;
import cmpe295.hungwenli.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(path = "/course/{id}", produces = "application/json")
    public HttpEntity<CourseDTO> findCourseById(@NotNull @PathVariable("id") String id) {
        Integer idNum = Integer.valueOf(id);
        CourseDTO course = courseService.findCourseById(idNum);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping(path = "/course", produces = "application/json")
    public HttpEntity<Map<String, Object>> paginate(@RequestParam Map<String, String> param) {

        Integer skipNum = Integer.valueOf(param.get("$skip"));
        Integer limitNum = Integer.valueOf(param.get("$limit"));

        System.out.println("skip: " + skipNum);
        System.out.println("limit: " + limitNum);

        Map<String, Object> result = courseService.findAllCourse(skipNum, limitNum);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/course/search", produces = "application/json")
    public HttpEntity<List<CourseDTO>> searchCourseByKeywords(@RequestParam String keywords) {
        List<CourseDTO> courses = courseService.searchCourseByKeywords(keywords);

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping(path = "/findCourseByName_notInUsedService", produces = "application/json")
    public HttpEntity findCourseByName(@RequestBody Map<String, String> payload) {
        List<Course> courses = courseService.findCoursesByName(payload.get("course_name"));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}

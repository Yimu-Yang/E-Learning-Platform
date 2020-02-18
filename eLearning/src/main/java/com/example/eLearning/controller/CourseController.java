package com.example.eLearning.controller;

import com.example.eLearning.modal.Course;
import com.example.eLearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(path = "/", produces = "application/json")
    public HttpEntity findAllCourses() {
        List<Course> allCourses = courseService.findAllCourses();

        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }
}

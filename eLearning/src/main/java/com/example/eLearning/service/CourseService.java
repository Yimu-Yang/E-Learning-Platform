package com.example.eLearning.service;

import com.example.eLearning.modal.Course;
import com.example.eLearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourses(){
        return courseRepository.findAllCourses();
    }
}

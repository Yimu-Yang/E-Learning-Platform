package com.example.eLearning.repository;

import com.example.eLearning.modal.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    List<Course> courses = new ArrayList<>();


    public CourseRepository() {
        Course courseOne = Course.builder()
                .cId(1)
                .cName("Java Basic")
                .teacher("John")
                .description("For people who learn java at first time.")
                .rating(3.5)
                .build();
        courses.add(courseOne);
    }

    public List<Course> findAllCourses() {
        return courses;
    }
}

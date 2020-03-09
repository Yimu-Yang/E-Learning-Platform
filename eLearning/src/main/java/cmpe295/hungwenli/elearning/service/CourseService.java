package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findCoursesByName(String courseName){
        List<Course> courses = courseRepository.findByCourseName(courseName);
        return courses;
    }

}

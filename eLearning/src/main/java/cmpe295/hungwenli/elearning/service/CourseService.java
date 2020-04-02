package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findCoursesByName(String courseName){
        List<Course> courses = courseRepository.findByCourseName(courseName);
        return courses;
    }

    // PageRank algorithm, needs modification.
    public List<Course> pageRankCourseSearch(String courseName){
        List<Course> courses = courseRepository.findByCourseName(courseName);
        // demo results, needs modification!
        courses.add(courseRepository.findByCourseName("Intermediate Photoshop CS6").get(0));
        courses.add(courseRepository.findByCourseName("International Affairs: Globalisation").get(0));
        courses.add(courseRepository.findByCourseName("International and Cross-Cultural Negotiation").get(0));
        courses.add(courseRepository.findByCourseName("International B2B (Business to Business) Marketing").get(0));
        return courses;
    }

    public Optional<Course> findCoursesById(Integer id){
        Optional<Course> courses = courseRepository.findById(id);
        return courses;
    }

}

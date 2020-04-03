package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.model.Course;
import cmpe295.hungwenli.elearning.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // dynamically get top 5 popular courses, needs modification.
    public List<Course> popularCourses(){
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int random = Utility.getRandomNumberInRange(783, 20956);
            // cause mysql connection problem
            Optional<Course> o = courseRepository.findById(random);
            if (!o.isEmpty()) {
                courses.add(o.get());
            }
        }
        return courses;
    }

    // PageRank algorithm, needs modification.
    public List<Course> pageRankCourseSearch(String courseName){
        List<Course> courses = courseRepository.findByCourseName(courseName);
        // demo results, needs modification!
        for (int i = 0; i < 4; i++) {
            int random = Utility.getRandomNumberInRange(783, 20956);
            Optional<Course> o = courseRepository.findById(random);
            if (!o.isEmpty()) {
                courses.add(o.get());
            }
        }
        return courses;
    }

    public Optional<Course> findCoursesById(Integer id){
        Optional<Course> courses = courseRepository.findById(id);
        return courses;
    }

}

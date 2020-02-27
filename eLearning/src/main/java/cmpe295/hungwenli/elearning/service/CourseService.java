package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Component
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourses(){
        Integer[] arr = new Integer[]{1000, 3000, 5000, 7000, 9000, 11000, 13000, 15000, 17000, 19000};
        List<Integer> ids = Arrays.asList(arr);
        return courseRepository.findAllById(ids);
    }
}

package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.DTO.CourseDTO;
import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TFIDFService tfidfService;

    public Map<String, Object> findAllCourse(int skip, int limit) {
        Map<String, Object> result = new HashMap<>();

//        List<Course> allCourse = courseRepository.findAll();
//        result.put("total", allCourse.size());
        result.put("total", 20718);
        result.put("limit", limit);
        result.put("skip", skip);

        int minID = courseRepository.min();
        int startID = minID + skip;

        List<CourseDTO> data = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            Course course = courseRepository.findCourseById(startID + i);
            CourseDTO courseDto = new CourseDTO(course.getId(),
                    course.getCourseName(),
                    course.getProvider(),
                    course.getPrice(),
                    course.getRating(),
                    course.getCourseDescription(),
                    course.getImageURL(),
                    course.getVideoURL(),
                    course.getCourseTalkURL(),
                    course.getCourseRedirectURL(),
                    course.getCourseActualURL());
            data.add(courseDto);
        }

        result.put("data", data);

        return result;
    }

    public CourseDTO findCourseById(Integer id) {
        Course course = courseRepository.findCourseById(id);
        CourseDTO courseDto = new CourseDTO(course.getId(),
                course.getCourseName(),
                course.getProvider(),
                course.getPrice(),
                course.getRating(),
                course.getCourseDescription(),
                course.getImageURL(),
                course.getVideoURL(),
                course.getCourseTalkURL(),
                course.getCourseRedirectURL(),
                course.getCourseActualURL());
        return courseDto;
    }

    public List<CourseDTO> searchCourseByKeywords(String keywords) {

        List<CourseDTO> res = new ArrayList<>();

        List<String> courseList = tfidfService.search(10, keywords);

        for (String name: courseList) {
            Course course = courseRepository.findCourseByCourseName(name);
            if (course == null) continue;
            CourseDTO courseDto = new CourseDTO(course.getId(),
                    course.getCourseName(),
                    course.getProvider(),
                    course.getPrice(),
                    course.getRating(),
                    course.getCourseDescription(),
                    course.getImageURL(),
                    course.getVideoURL(),
                    course.getCourseTalkURL(),
                    course.getCourseRedirectURL(),
                    course.getCourseActualURL());
            res.add(courseDto);
        }

        return res;
    }

}

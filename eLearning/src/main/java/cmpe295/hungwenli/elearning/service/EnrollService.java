package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.Course;
import cmpe295.hungwenli.elearning.model.DTO.CourseDTO;
import cmpe295.hungwenli.elearning.model.ELearningUser;
import cmpe295.hungwenli.elearning.model.Enroll;
import cmpe295.hungwenli.elearning.repository.CourseRepository;
import cmpe295.hungwenli.elearning.repository.EnrollRepository;
import cmpe295.hungwenli.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollRepository enrollRepository;

    public void enroll(String userName, Long courseId) {
        ELearningUser currentUser = userRepository.findByUserName(userName);
        Course currentCourse = courseRepository.findCourseById(courseId);

        enrollRepository.save(Enroll.builder().course(currentCourse).user(currentUser).build());
    }

    public List<CourseDTO> myCourse(String userName) {
        ELearningUser user = userRepository.findByUserName(userName);
        Iterable<Enroll> enrolls = enrollRepository.findAll();
        List<CourseDTO> courses = new ArrayList<>();

        for (Enroll enroll: enrolls) {
            if (enroll.getUser().equals(user)) {
                Course course = enroll.getCourse();
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
                courses.add(courseDto);
            }
        }

        return courses;
    }

}

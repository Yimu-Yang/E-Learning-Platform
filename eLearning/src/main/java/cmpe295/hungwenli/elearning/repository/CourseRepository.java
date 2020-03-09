package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findByCourseName(String courseName);

}

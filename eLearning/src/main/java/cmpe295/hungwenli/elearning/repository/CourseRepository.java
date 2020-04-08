package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findAll();
    Course findCourseById(Integer id);
    List<Course> findCourseByCourseName(String courseName);

    @Query(value = "SELECT min(id) FROM Course")
    Integer min();

}

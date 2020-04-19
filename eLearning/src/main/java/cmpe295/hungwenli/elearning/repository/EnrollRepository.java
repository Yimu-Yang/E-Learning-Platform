package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.model.Enroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends CrudRepository<Enroll, Long> {

}

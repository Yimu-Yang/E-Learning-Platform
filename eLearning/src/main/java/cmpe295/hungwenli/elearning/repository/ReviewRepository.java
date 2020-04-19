package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}

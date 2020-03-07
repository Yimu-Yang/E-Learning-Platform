package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}

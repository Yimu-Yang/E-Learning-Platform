package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    /*
     * Spring Data JPA Repository needs correct method signature name to build the query automatically.
     */
    List<User> findByUserName(String userName);

}

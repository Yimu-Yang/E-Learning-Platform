package cmpe295.hungwenli.elearning.repository;

import cmpe295.hungwenli.elearning.model.ELearningUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ELearningUser, String> {

    /*
     * Spring Data JPA Repository needs correct method signature name to build the query automatically.
     */
    ELearningUser findByUserName(String userName);

}

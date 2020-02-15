package cmpe295.hungwen.elearning.repository;

import cmpe295.hungwen.elearning.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    boolean existsByDevice_DeviceId(String deviceId);
    Client getByClientId(String clientId);
    boolean existsByClientId(String clientId);
}

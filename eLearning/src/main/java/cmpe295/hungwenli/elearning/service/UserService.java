package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.domain.User;
import cmpe295.hungwenli.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean register(Map<String, String> payload) {
        String userName = payload.get("user_name");
        String password = payload.get("password");
        User user = new User(userName, password);
        userRepository.save(user);
        return true;
    }

    public boolean login(Map<String, String> payload) {
        return true;
    }

    public boolean logout() {
        return true;
    }

}

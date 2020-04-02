package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.ELearningUser;
import cmpe295.hungwenli.elearning.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cmpe295.hungwenli.elearning.security.SecurityConstants.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Map<String, String> signup(String username, String password) {
        ELearningUser tmpUser = userRepository.findByUserName(username);
        if (tmpUser != null) {
            return null;
        }

        ELearningUser user = new ELearningUser(username, password);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = Jwts.builder()
                .setSubject(user.getUserName())
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("token", TOKEN_PREFIX + token);

        return result;
    }

    public Map<String, String> signin(String username, String password) {
        ELearningUser user = userRepository.findByUserName(username);
        if (user == null) {
            return null;
        }

        System.out.println("password: " + user.getPassword());
        System.out.println("encoded password: " + bCryptPasswordEncoder.encode(password));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        String token = Jwts.builder()
                .setSubject(user.getUserName())
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("token", TOKEN_PREFIX + token);

        return result;
    }
}

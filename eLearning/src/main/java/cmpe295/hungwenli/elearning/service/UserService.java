package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.ELearningUser;
import cmpe295.hungwenli.elearning.model.Response;
import cmpe295.hungwenli.elearning.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
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

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") != null) {
            return;
        }
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        ELearningUser user = userRepository.findByUserName(userName);
        if (user == null || !user.getPassword().equals(password)) {
            response.sendRedirect("/");
            return;
        }
        request.getSession().setAttribute("user", userName);
        response.sendRedirect("/content");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/");
    }

    public Response getUsername(HttpServletRequest request, HttpServletResponse response) {
        Response message = new Response((String) request.getSession().getAttribute("user"));
        return message;
    }

}

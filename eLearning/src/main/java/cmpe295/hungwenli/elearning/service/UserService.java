package cmpe295.hungwenli.elearning.service;

import cmpe295.hungwenli.elearning.model.Response;
import cmpe295.hungwenli.elearning.model.User;
import cmpe295.hungwenli.elearning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean register(HttpServletRequest request) {
        List<User> users = userRepository.findByUserName(request.getParameter("user_name"));
        if (users.size() != 0) {
            return false;
        }
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        User user = new User(userName, password);
        userRepository.save(user);
        request.getSession().setAttribute("user", userName);
        return true;
    }

    public boolean login(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return false;
        }
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        List<User> users = userRepository.findByUserName(userName);
        if (users.size() == 0 || !users.get(0).getPassword().equals(password)) {
            return false;
        }
        request.getSession().setAttribute("user", userName);
        return true;
    }

    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return false;
        }
        session.invalidate();
        return true;
    }

    public Response getUsername(HttpServletRequest request) {
        Response message = new Response((String) request.getSession().getAttribute("user"));
        return message;
    }

}
